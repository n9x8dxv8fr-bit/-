package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户账单流水对象 customer_bill_flow
 * 
 * @author ruoyi
 * @date 2026-01-06
 */
public class CustomerBillFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private Long orderId;
    /** 主键ID */
    private String id;

    /** 客户ID（关联客户表customer.id） */
    @Excel(name = "客户ID", readConverterExp = "关=联客户表customer.id")
    private String customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String customerPhone;

    /** 客户地址 */
    @Excel(name = "客户地址")
    private String customerAddress;

    /** 账单编号（唯一，如：BILL20260106001） */
    @Excel(name = "账单编号", readConverterExp = "唯=一，如：BILL20260106001")
    private String billNo;

    /** 账单类型（1=收入，2=支出） */
    @Excel(name = "账单类型", readConverterExp = "1==收入，2=支出")
    private String billType;

    /** 交易金额（元） */
    @Excel(name = "交易金额", readConverterExp = "元=")
    private BigDecimal amount;

    /** 货币类型（默认人民币：CNY） */
    @Excel(name = "货币类型", readConverterExp = "默=认人民币：CNY")
    private String currency;

    /** 交易日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tradeDate;

    /** 交易时间（精确到时分秒） */
    @Excel(name = "交易时间", readConverterExp = "精=确到时分秒")
    private Date tradeTime;

    /** 支付方式（如：微信、支付宝、银行卡、现金） */
    @Excel(name = "支付方式", readConverterExp = "如=：微信、支付宝、银行卡、现金")
    private String paymentMethod;

    /** 支付账号（如银行卡号、微信昵称） */
    @Excel(name = "支付账号", readConverterExp = "如=银行卡号、微信昵称")
    private String paymentAccount;

    /** 账单状态（0=未生效，1=已生效，2=已作废） */
    @Excel(name = "账单状态", readConverterExp = "0==未生效，1=已生效，2=已作废")
    private String billStatus;

    /** 业务场景（如：产品采购、服务费用、退款、预付款） */
    @Excel(name = "业务场景", readConverterExp = "如=：产品采购、服务费用、退款、预付款")
    private String businessScene;

    /** 关联订单号（如存在订单，关联订单表order_no） */
    @Excel(name = "关联订单号", readConverterExp = "如=存在订单，关联订单表order_no")
    private String relatedOrderNo;

    /** 详细说明（多行文本，用于记录完整交易内容、附言、业务详情等） */
    @Excel(name = "详细说明", readConverterExp = "多=行文本，用于记录完整交易内容、附言、业务详情等")
    private String detailDesc;

    /** 删除标志（0=正常，1=删除） */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public void setCustomerId(String customerId) 
    {
        this.customerId = customerId;
    }

    public String getCustomerId() 
    {
        return customerId;
    }

    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }

    public void setCustomerPhone(String customerPhone)
    {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPhone()
    {
        return customerPhone;
    }

    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public void setBillNo(String billNo) 
    {
        this.billNo = billNo;
    }

    public String getBillNo() 
    {
        return billNo;
    }

    public void setBillType(String billType) 
    {
        this.billType = billType;
    }

    public String getBillType() 
    {
        return billType;
    }

    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }

    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }

    public void setTradeDate(Date tradeDate) 
    {
        this.tradeDate = tradeDate;
    }

    public Date getTradeDate() 
    {
        return tradeDate;
    }

    public void setTradeTime(Date tradeTime) 
    {
        this.tradeTime = tradeTime;
    }

    public Date getTradeTime() 
    {
        return tradeTime;
    }

    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }

    public void setPaymentAccount(String paymentAccount) 
    {
        this.paymentAccount = paymentAccount;
    }

    public String getPaymentAccount() 
    {
        return paymentAccount;
    }

    public void setBillStatus(String billStatus) 
    {
        this.billStatus = billStatus;
    }

    public String getBillStatus() 
    {
        return billStatus;
    }

    public void setBusinessScene(String businessScene) 
    {
        this.businessScene = businessScene;
    }

    public String getBusinessScene() 
    {
        return businessScene;
    }

    public void setRelatedOrderNo(String relatedOrderNo) 
    {
        this.relatedOrderNo = relatedOrderNo;
    }

    public String getRelatedOrderNo() 
    {
        return relatedOrderNo;
    }

    public void setDetailDesc(String detailDesc) 
    {
        this.detailDesc = detailDesc;
    }

    public String getDetailDesc() 
    {
        return detailDesc;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    // 新增：orderId的getter/setter（必须加，否则setOrderId()爆红）
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    // 如果需要接收String类型的orderId，再新增一个重载方法（可选）
    public void setOrderId(String orderId) {
        if (orderId != null && !orderId.isEmpty()) {
            this.orderId = Long.parseLong(orderId);
        } else {
            this.orderId = null;
        }
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("customerPhone", getCustomerPhone())
            .append("customerAddress", getCustomerAddress())
            .append("billNo", getBillNo())
            .append("billType", getBillType())
            .append("amount", getAmount())
            .append("currency", getCurrency())
            .append("tradeDate", getTradeDate())
            .append("tradeTime", getTradeTime())
            .append("paymentMethod", getPaymentMethod())
            .append("paymentAccount", getPaymentAccount())
            .append("billStatus", getBillStatus())
            .append("businessScene", getBusinessScene())
            .append("relatedOrderNo", getRelatedOrderNo())
            .append("remark", getRemark())
            .append("detailDesc", getDetailDesc())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
