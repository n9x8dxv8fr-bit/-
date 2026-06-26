package com.ruoyi.system.service.impl;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.PurchaseItem;
import com.ruoyi.system.mapper.PurchaseItemMapper;
import com.ruoyi.system.mapper.PurchaseOrderMapper;
import com.ruoyi.system.domain.PurchaseOrder;
import com.ruoyi.system.service.IPurchaseOrderService;
import com.ruoyi.system.service.ISteelBallInoutFlowRecordService;

/**
 * 采购订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService 
{
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PurchaseItemMapper purchaseItemMapper;

    @Autowired
    private ISteelBallInoutFlowRecordService steelBallInoutFlowRecordService;

    /**
     * 查询采购订单
     * 
     * @param id 采购订单主键
     * @return 采购订单
     */
    @Override
    public PurchaseOrder selectPurchaseOrderById(Long id)
    {
        return purchaseOrderMapper.selectPurchaseOrderById(id);
    }

    /**
     * 查询采购订单列表
     * 
     * @param purchaseOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrder purchaseOrder)
    {
        return purchaseOrderMapper.selectPurchaseOrderList(purchaseOrder);
    }

    /**
     * 新增采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPurchaseOrder(PurchaseOrder purchaseOrder)
    {
        purchaseOrder.setCreateTime(DateUtils.getNowDate());
        if (purchaseOrder.getPurchaseTime() == null)
        {
            purchaseOrder.setPurchaseTime(DateUtils.getNowDate());
        }
        int rows = purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
        if (rows > 0 && purchaseOrder.getId() != null)
        {
            generatePurchaseItemFromOrder(purchaseOrder);
            steelBallInoutFlowRecordService.recordInboundFromPurchase(purchaseOrder);
        }
        return rows;
    }

    /**
     * 修改采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePurchaseOrder(PurchaseOrder purchaseOrder)
    {
        purchaseOrder.setUpdateTime(DateUtils.getNowDate());
        int rows = purchaseOrderMapper.updatePurchaseOrder(purchaseOrder);
        if (rows > 0 && purchaseOrder.getId() != null)
        {
            purchaseItemMapper.deletePurchaseItemByOrderId(purchaseOrder.getId());
            generatePurchaseItemFromOrder(purchaseOrder);
        }
        return rows;
    }

    /**
     * 批量删除采购订单
     * 
     * @param ids 需要删除的采购订单主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePurchaseOrderByIds(Long[] ids)
    {
        if (ids != null)
        {
            for (Long id : ids)
            {
                purchaseItemMapper.deletePurchaseItemByOrderId(id);
            }
        }
        return purchaseOrderMapper.deletePurchaseOrderByIds(ids);
    }

    /**
     * 删除采购订单信息
     * 
     * @param id 采购订单主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePurchaseOrderById(Long id)
    {
        purchaseItemMapper.deletePurchaseItemByOrderId(id);
        return purchaseOrderMapper.deletePurchaseOrderById(id);
    }

    private void generatePurchaseItemFromOrder(PurchaseOrder order)
    {
        String pt = StringUtils.isBlank(order.getProductType()) ? null : order.getProductType().trim();
        String ps = StringUtils.isBlank(order.getProductSpec()) ? null : order.getProductSpec().trim();
        if (pt == null && ps == null)
        {
            pt = "采购汇总";
            ps = StringUtils.isNotBlank(order.getOrderNo()) ? order.getOrderNo().trim() : String.valueOf(order.getId());
        }

        PurchaseItem item = new PurchaseItem();
        item.setOrderId(order.getId());
        item.setProductId("AUTO");
        item.setProductName(pt);
        item.setProductSpec(ps == null ? "" : ps);

        Long quantity = order.getTotalQuantity() == null ? 0L : order.getTotalQuantity();
        BigDecimal amount = order.getTotalAmount() == null ? BigDecimal.ZERO : order.getTotalAmount();
        BigDecimal price = BigDecimal.ZERO;
        if (quantity > 0)
        {
            price = amount.divide(BigDecimal.valueOf(quantity), 2, RoundingMode.HALF_UP);
        }

        item.setQuantity(quantity);
        item.setPrice(price);
        item.setAmount(amount);
        purchaseItemMapper.insertPurchaseItem(item);
    }
}
