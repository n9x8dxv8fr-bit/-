package com.ruoyi.system.service.impl;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.SaleItem;
import com.ruoyi.system.mapper.SaleItemMapper;
import com.ruoyi.system.mapper.SaleOrderMapper;
import com.ruoyi.system.domain.SaleOrder;
import com.ruoyi.system.service.ISaleOrderService;

/**
 * 销售订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
@Service
public class SaleOrderServiceImpl implements ISaleOrderService 
{
    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private SaleItemMapper saleItemMapper;

    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    @Override
    public SaleOrder selectSaleOrderById(Long id)
    {
        return saleOrderMapper.selectSaleOrderById(id);
    }

    /**
     * 查询销售订单列表
     * 
     * @param saleOrder 销售订单
     * @return 销售订单
     */
    @Override
    public List<SaleOrder> selectSaleOrderList(SaleOrder saleOrder)
    {
        return saleOrderMapper.selectSaleOrderList(saleOrder);
    }

    /**
     * 新增销售订单
     * 
     * @param saleOrder 销售订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSaleOrder(SaleOrder saleOrder)
    {
        saleOrder.setCreateTime(DateUtils.getNowDate());
        if (saleOrder.getSaleTime() == null)
        {
            saleOrder.setSaleTime(DateUtils.getNowDate());
        }
        int rows = saleOrderMapper.insertSaleOrder(saleOrder);
        if (rows > 0 && saleOrder.getId() != null)
        {
            generateSaleItemFromOrder(saleOrder);
        }
        return rows;
    }

    /**
     * 修改销售订单
     * 
     * @param saleOrder 销售订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSaleOrder(SaleOrder saleOrder)
    {
        saleOrder.setUpdateTime(DateUtils.getNowDate());
        int rows = saleOrderMapper.updateSaleOrder(saleOrder);
        if (rows > 0 && saleOrder.getId() != null)
        {
            saleItemMapper.deleteSaleItemByOrderId(saleOrder.getId());
            generateSaleItemFromOrder(saleOrder);
        }
        return rows;
    }

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的销售订单主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSaleOrderByIds(Long[] ids)
    {
        if (ids != null)
        {
            for (Long id : ids)
            {
                saleItemMapper.deleteSaleItemByOrderId(id);
            }
        }
        return saleOrderMapper.deleteSaleOrderByIds(ids);
    }

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSaleOrderById(Long id)
    {
        saleItemMapper.deleteSaleItemByOrderId(id);
        return saleOrderMapper.deleteSaleOrderById(id);
    }

    private void generateSaleItemFromOrder(SaleOrder order)
    {
        SaleItem item = new SaleItem();
        item.setOrderId(order.getId());
        item.setProductId("AUTO");
        item.setProductName("销售订单明细-" + order.getOrderNo());
        item.setProductSpec("-");

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
        saleItemMapper.insertSaleItem(item);
    }
}
