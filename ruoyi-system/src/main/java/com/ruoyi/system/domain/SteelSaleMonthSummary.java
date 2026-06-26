package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 钢球月度销售汇总（图专用）对象 steel_sale_month_summary
 * 
 * @author ruoyi
 * @date 2026-01-10
 */
public class SteelSaleMonthSummary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 汇总ID */
    private Long id;

    /** 年份（如 2025） */
    @Excel(name = "年份", readConverterExp = "如=,2=025")
    private Long year;

    /** 月份（1-12） */
    @Excel(name = "月份", readConverterExp = "1=-12")
    private Long month;

    /** 月度总销售量（个） */
    @Excel(name = "月度总销售量", readConverterExp = "个=")
    private Long totalSaleNum;

    /** 月度总营业额（元） */
    @Excel(name = "月度总营业额", readConverterExp = "元=")
    private BigDecimal totalSaleAmount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setYear(Long year) 
    {
        this.year = year;
    }

    public Long getYear() 
    {
        return year;
    }

    public void setMonth(Long month) 
    {
        this.month = month;
    }

    public Long getMonth() 
    {
        return month;
    }

    public void setTotalSaleNum(Long totalSaleNum) 
    {
        this.totalSaleNum = totalSaleNum;
    }

    public Long getTotalSaleNum() 
    {
        return totalSaleNum;
    }

    public void setTotalSaleAmount(BigDecimal totalSaleAmount) 
    {
        this.totalSaleAmount = totalSaleAmount;
    }

    public BigDecimal getTotalSaleAmount() 
    {
        return totalSaleAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("year", getYear())
            .append("month", getMonth())
            .append("totalSaleNum", getTotalSaleNum())
            .append("totalSaleAmount", getTotalSaleAmount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
