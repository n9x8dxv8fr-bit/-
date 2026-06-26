package com.ruoyi.system.service;

import java.math.BigDecimal;

import com.ruoyi.system.domain.PurchaseOrder;

/**
 * 根据业务单据写入钢球进出库流水，并计算累计入出库、客户累计出库快照。
 */
public interface ISteelBallInoutFlowRecordService
{
    /**
     * 出库流水（与订购审核、销售出库一致：按 产品类型+规格 聚合计量）。
     */
    void recordOutbound(String productType, String productSpec, Long quantity, BigDecimal unitPrice,
            BigDecimal totalAmount, String customerName, String orderNo, String remark);

    /**
     * 采购入库流水（新增采购单时调用；仅新增单据写流水，修改采购单不重复写入）。
     */
    void recordInboundFromPurchase(PurchaseOrder order);
}
