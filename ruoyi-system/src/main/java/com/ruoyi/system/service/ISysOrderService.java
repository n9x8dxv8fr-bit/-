package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysOrder;
import java.util.List;

/**
 * 订单Service接口
 *
 * @author ruoyi
 */
public interface ISysOrderService {
    /**
     * 提交订单（自动生成账单）
     */
    void submitOrder(SysOrder order);

    /**
     * 查询订单（用于出库审核）
     */
    List<SysOrder> selectSysOrderList(SysOrder order);

    /**
     * 审核通过并生成销售明细
     */
    void approveOrder(Long id, String remark);

    /**
     * 审核驳回
     */
    void rejectOrder(Long id, String remark);

    /**
     * 统计待审核订单数
     */
    int countPendingAuditOrders();

    /**
     * 统计昨日销量（总数量）
     */
    long getYesterdaySalesQuantity();
}