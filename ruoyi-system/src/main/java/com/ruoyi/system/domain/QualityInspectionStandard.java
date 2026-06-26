package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class QualityInspectionStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private Long id;
    @Excel(name = "标准名称")
    private String standardName;
    @Excel(name = "尺寸和精度")
    private String sizePrecision;
    @Excel(name = "表面粗糙度")
    private String surfaceRoughness;
    @Excel(name = "硬度")
    private String hardness;
    @Excel(name = "材料")
    private String material;
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStandardName() { return standardName; }
    public void setStandardName(String standardName) { this.standardName = standardName; }
    public String getSizePrecision() { return sizePrecision; }
    public void setSizePrecision(String sizePrecision) { this.sizePrecision = sizePrecision; }
    public String getSurfaceRoughness() { return surfaceRoughness; }
    public void setSurfaceRoughness(String surfaceRoughness) { this.surfaceRoughness = surfaceRoughness; }
    public String getHardness() { return hardness; }
    public void setHardness(String hardness) { this.hardness = hardness; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId()).append("standardName", getStandardName())
            .append("sizePrecision", getSizePrecision()).append("surfaceRoughness", getSurfaceRoughness())
            .append("hardness", getHardness()).append("material", getMaterial())
            .append("status", getStatus()).append("remark", getRemark())
            .append("createBy", getCreateBy()).append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).toString();
    }
}
