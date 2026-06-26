package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 钢球产品信息对象 t_product
 * 
 * @author ruoyi
 * @date 2025-12-28
 */
public class TProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    private String id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 产品类型(关联steel_ball_type字典) */
    @Excel(name = "产品类型(关联steel_ball_type字典)")
    private String type;

    /** 规格/直径 */
    @Excel(name = "规格/直径")
    private String PS;

    /** 技术参数 */
    @Excel(name = "技术参数")
    private String parameter;

    /** 价格（元） */
    @Excel(name = "价格", readConverterExp = "元=")
    private BigDecimal price;

    /** 产品特性 */
    @Excel(name = "产品特性")
    private String characteristic;

    /** 应用领域 */
    @Excel(name = "应用领域")
    private String application;

    /** 删除标志 */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setPS(String PS) 
    {
        this.PS = PS;
    }

    public String getPS() 
    {
        return PS;
    }

    public void setParameter(String parameter) 
    {
        this.parameter = parameter;
    }

    public String getParameter() 
    {
        return parameter;
    }

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    public void setCharacteristic(String characteristic) 
    {
        this.characteristic = characteristic;
    }

    public String getCharacteristic() 
    {
        return characteristic;
    }

    public void setApplication(String application) 
    {
        this.application = application;
    }

    public String getApplication() 
    {
        return application;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("PS", getPS())
            .append("parameter", getParameter())
            .append("price", getPrice())
            .append("characteristic", getCharacteristic())
            .append("application", getApplication())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
