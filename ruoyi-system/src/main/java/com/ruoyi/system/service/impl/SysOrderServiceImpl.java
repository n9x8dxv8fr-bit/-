package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SaleItem;
import com.ruoyi.system.domain.SaleOrder;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.mapper.SaleItemMapper;
import com.ruoyi.system.mapper.SaleOrderMapper;
import com.ruoyi.system.mapper.SysOrderMapper;
import com.ruoyi.system.service.ISysOrderService;
import com.ruoyi.system.service.ICustomerBillFlowService;
import com.ruoyi.system.service.ISteelBallInoutFlowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SysOrderServiceImpl implements ISysOrderService
{
    @Autowired
    private SysOrderMapper orderMapper;

    @Autowired
    private ICustomerBillFlowService billFlowService;

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private SaleItemMapper saleItemMapper;

    @Autowired
    private ISteelBallInoutFlowRecordService steelBallInoutFlowRecordService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitOrder(SysOrder order)
    {
        try {
            order.setStatus("待审核");
            order.setCreateTime(DateUtils.getNowDate());
            order.setUpdateTime(DateUtils.getNowDate());

            orderMapper.insertSysOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("订单提交失败：" + e.getMessage());
        }
    }

    @Override
    public List<SysOrder> selectSysOrderList(SysOrder order)
    {
        return orderMapper.selectSysOrderList(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveOrder(Long id, String remark)
    {
        SysOrder order = orderMapper.selectSysOrderById(id);
        if (order == null)
        {
            throw new RuntimeException("订单不存在");
        }
        if (!"待审核".equals(order.getStatus()))
        {
            throw new RuntimeException("当前订单状态不可审核通过");
        }

        String mergedRemark = mergeRemark(order.getRemark(), remark);
        orderMapper.updateSysOrderStatus(id, "已审核", mergedRemark);
        order.setStatus("已审核");
        order.setRemark(mergedRemark);

        createSaleDataFromOrder(order);
        billFlowService.createBillFromOrder(order);
        createSteelBallOutFlow(order);
        orderMapper.updateSysOrderStatus(id, "已出库", mergedRemark);
    }

    @Override
    public void rejectOrder(Long id, String remark)
    {
        SysOrder order = orderMapper.selectSysOrderById(id);
        if (order == null)
        {
            throw new RuntimeException("订单不存在");
        }
        if (!"待审核".equals(order.getStatus()))
        {
            throw new RuntimeException("当前订单状态不可驳回");
        }
        orderMapper.updateSysOrderStatus(id, "已驳回", mergeRemark(order.getRemark(), remark));
    }

    @Override
    public int countPendingAuditOrders()
    {
        return orderMapper.countByStatus("待审核");
    }

    @Override
    public long getYesterdaySalesQuantity()
    {
        // 昨日 [00:00:00, 今日00:00:00)
        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate yesterday = today.minusDays(1);
        String start = yesterday.toString() + " 00:00:00";
        String end = today.toString() + " 00:00:00";
        Long qty = saleOrderMapper.selectYesterdayTotalQuantity(start, end);
        return qty == null ? 0L : qty;
    }

    private String mergeRemark(String oldRemark, String auditRemark)
    {
        if (StringUtils.isBlank(auditRemark))
        {
            return oldRemark;
        }
        if (StringUtils.isBlank(oldRemark))
        {
            return "审核备注：" + auditRemark;
        }
        return oldRemark + "；审核备注：" + auditRemark;
    }

    private void createSaleDataFromOrder(SysOrder order)
    {
        SaleOrder saleOrder = new SaleOrder();
        String saleOrderNo = StringUtils.isNotBlank(order.getOrderNo())
                ? order.getOrderNo()
                : "SO" + System.currentTimeMillis();
        saleOrder.setOrderNo(saleOrderNo);
        saleOrder.setCustomerId(order.getCustomerName());
        saleOrder.setTotalQuantity(order.getProductNum() == null ? 0L : Long.valueOf(order.getProductNum()));
        saleOrder.setTotalAmount(order.getTotalPrice() == null ? BigDecimal.ZERO : order.getTotalPrice());
        saleOrder.setStatus("2");
        saleOrder.setSaleTime(DateUtils.getNowDate());
        saleOrder.setOperator("system");
        saleOrder.setRemark("系统根据订购审核自动生成");
        saleOrder.setCreateTime(DateUtils.getNowDate());
        saleOrderMapper.insertSaleOrder(saleOrder);

        SaleItem saleItem = new SaleItem();
        saleItem.setOrderId(saleOrder.getId());
        saleItem.setProductId("AUTO");
        saleItem.setProductName(StringUtils.isNotBlank(order.getProductType()) ? order.getProductType() : "未命名产品");
        saleItem.setProductSpec(StringUtils.isNotBlank(order.getProductSpec()) ? order.getProductSpec() : "-");
        saleItem.setQuantity(order.getProductNum() == null ? 0L : Long.valueOf(order.getProductNum()));
        saleItem.setPrice(order.getUnitPrice() == null ? BigDecimal.ZERO : order.getUnitPrice());
        saleItem.setAmount(order.getTotalPrice() == null ? BigDecimal.ZERO : order.getTotalPrice());
        saleItemMapper.insertSaleItem(saleItem);
    }

    private void createSteelBallOutFlow(SysOrder order)
    {
        Long quantity = order.getProductNum() == null ? 0L : Long.valueOf(order.getProductNum());
        BigDecimal unitPrice = order.getUnitPrice() == null ? BigDecimal.ZERO : order.getUnitPrice();
        BigDecimal totalAmount = order.getTotalPrice() == null ? BigDecimal.ZERO : order.getTotalPrice();
        String remark = "订单出库：" + StringUtils.defaultString(order.getOrderNo());
        steelBallInoutFlowRecordService.recordOutbound(
                order.getProductType(),
                order.getProductSpec(),
                quantity,
                unitPrice,
                totalAmount,
                order.getCustomerName(),
                order.getOrderNo(),
                remark);
    }
}