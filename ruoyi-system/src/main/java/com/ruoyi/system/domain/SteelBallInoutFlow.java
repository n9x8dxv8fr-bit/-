package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 钢球进出库流水（含统计字段）对象 steel_ball_inout_flow
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
public class SteelBallInoutFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 流水编号（唯一） */
    @Excel(name = "流水编号", readConverterExp = "唯=一")
    private String flowNo;

    /** 产品名称（如：碳钢球φ10mm） */
    @Excel(name = "产品名称", readConverterExp = "如=：碳钢球φ10mm")
    private String productName;

    /** 产品类型（碳钢球/不锈钢球/铬钢球） */
    @Excel(name = "产品类型", readConverterExp = "碳=钢球/不锈钢球/铬钢球")
    private String productType;

    /** 产品规格（φ6mm/φ8mm等） */
    @Excel(name = "产品规格", readConverterExp = "φ=6mm/φ8mm等")
    private String productSpec;

    /** 进出库类型：1=入库，2=出库 */
    @Excel(name = "进出库类型：1=入库，2=出库")
    private Long inoutType;

    /** 数量（件） */
    @Excel(name = "数量", readConverterExp = "件=")
    private Long quantity;

    /** 单价（元/件） */
    @Excel(name = "单价", readConverterExp = "元=/件")
    private BigDecimal unitPrice;

    /** 单笔总价（元） */
    @Excel(name = "单笔总价", readConverterExp = "元=")
    private BigDecimal totalAmount;

    /** 该产品累计入库数量（件） */
    @Excel(name = "该产品累计入库数量", readConverterExp = "件=")
    private Long productTotalIn;

    /** 该产品累计入库金额（元） */
    @Excel(name = "该产品累计入库金额", readConverterExp = "元=")
    private BigDecimal productTotalInAmount;

    /** 该产品累计出库数量（件） */
    @Excel(name = "该产品累计出库数量", readConverterExp = "件=")
    private Long productTotalOut;

    /** 该产品累计出库金额（元） */
    @Excel(name = "该产品累计出库金额", readConverterExp = "元=")
    private BigDecimal productTotalOutAmount;

    /** 该客户累计出库数量（件） */
    @Excel(name = "该客户累计出库数量", readConverterExp = "件=")
    private Long customerTotalOut;

    /** 该客户累计出库金额（元） */
    @Excel(name = "该客户累计出库金额", readConverterExp = "元=")
    private BigDecimal customerTotalOutAmount;

    /** 年月（YYYY-MM，用于筛选） */
    @Excel(name = "年月", readConverterExp = "Y=YYY-MM，用于筛选")
    private String yearMonth;

    /** 关联客户名称（出库时必填） */
    @Excel(name = "关联客户名称", readConverterExp = "出=库时必填")
    private String customerName;

    /** 关联订单编号（出库时关联） */
    @Excel(name = "关联订单编号", readConverterExp = "出=库时关联")
    private String orderNo;

    /** 操作时间（进出库日期） */
    @Excel(name = "操作时间", readConverterExp = "进=出库日期")
    private Date operateTime;

    /** 操作员（当前登录用户） */
    @Excel(name = "操作员", readConverterExp = "当=前登录用户")
    private String operator;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setFlowNo(String flowNo) 
    {
        this.flowNo = flowNo;
    }

    public String getFlowNo() 
    {
        return flowNo;
    }

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }

    public void setProductType(String productType) 
    {
        this.productType = productType;
    }

    public String getProductType() 
    {
        return productType;
    }

    public void setProductSpec(String productSpec) 
    {
        this.productSpec = productSpec;
    }

    public String getProductSpec() 
    {
        return productSpec;
    }

    public void setInoutType(Long inoutType) 
    {
        this.inoutType = inoutType;
    }

    public Long getInoutType() 
    {
        return inoutType;
    }

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }

    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }

    public void setProductTotalIn(Long productTotalIn) 
    {
        this.productTotalIn = productTotalIn;
    }

    public Long getProductTotalIn() 
    {
        return productTotalIn;
    }

    public void setProductTotalInAmount(BigDecimal productTotalInAmount) 
    {
        this.productTotalInAmount = productTotalInAmount;
    }

    public BigDecimal getProductTotalInAmount() 
    {
        return productTotalInAmount;
    }

    public void setProductTotalOut(Long productTotalOut) 
    {
        this.productTotalOut = productTotalOut;
    }

    public Long getProductTotalOut() 
    {
        return productTotalOut;
    }

    public void setProductTotalOutAmount(BigDecimal productTotalOutAmount) 
    {
        this.productTotalOutAmount = productTotalOutAmount;
    }

    public BigDecimal getProductTotalOutAmount() 
    {
        return productTotalOutAmount;
    }

    public void setCustomerTotalOut(Long customerTotalOut) 
    {
        this.customerTotalOut = customerTotalOut;
    }

    public Long getCustomerTotalOut() 
    {
        return customerTotalOut;
    }

    public void setCustomerTotalOutAmount(BigDecimal customerTotalOutAmount) 
    {
        this.customerTotalOutAmount = customerTotalOutAmount;
    }

    public BigDecimal getCustomerTotalOutAmount() 
    {
        return customerTotalOutAmount;
    }

    public void setYearMonth(String yearMonth) 
    {
        this.yearMonth = yearMonth;
    }

    public String getYearMonth() 
    {
        return yearMonth;
    }

    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }

    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }

    public void setOperateTime(Date operateTime) 
    {
        this.operateTime = operateTime;
    }

    public Date getOperateTime() 
    {
        return operateTime;
    }

    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("flowNo", getFlowNo())
            .append("productName", getProductName())
            .append("productType", getProductType())
            .append("productSpec", getProductSpec())
            .append("inoutType", getInoutType())
            .append("quantity", getQuantity())
            .append("unitPrice", getUnitPrice())
            .append("totalAmount", getTotalAmount())
            .append("productTotalIn", getProductTotalIn())
            .append("productTotalInAmount", getProductTotalInAmount())
            .append("productTotalOut", getProductTotalOut())
            .append("productTotalOutAmount", getProductTotalOutAmount())
            .append("customerTotalOut", getCustomerTotalOut())
            .append("customerTotalOutAmount", getCustomerTotalOutAmount())
            .append("yearMonth", getYearMonth())
            .append("customerName", getCustomerName())
            .append("orderNo", getOrderNo())
            .append("operateTime", getOperateTime())
            .append("operator", getOperator())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
