package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SteelSaleMonthSummary;

/**
 * 钢球月度销售汇总（图专用）Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-10
 */
public interface SteelSaleMonthSummaryMapper 
{
    /**
     * 查询钢球月度销售汇总（图专用）
     * 
     * @param id 钢球月度销售汇总（图专用）主键
     * @return 钢球月度销售汇总（图专用）
     */
    public SteelSaleMonthSummary selectSteelSaleMonthSummaryById(Long id);

    /**
     * 查询钢球月度销售汇总（图专用）列表
     * 
     * @param steelSaleMonthSummary 钢球月度销售汇总（图专用）
     * @return 钢球月度销售汇总（图专用）集合
     */
    public List<SteelSaleMonthSummary> selectSteelSaleMonthSummaryList(SteelSaleMonthSummary steelSaleMonthSummary);

    /**
     * 新增钢球月度销售汇总（图专用）
     * 
     * @param steelSaleMonthSummary 钢球月度销售汇总（图专用）
     * @return 结果
     */
    public int insertSteelSaleMonthSummary(SteelSaleMonthSummary steelSaleMonthSummary);

    /**
     * 修改钢球月度销售汇总（图专用）
     * 
     * @param steelSaleMonthSummary 钢球月度销售汇总（图专用）
     * @return 结果
     */
    public int updateSteelSaleMonthSummary(SteelSaleMonthSummary steelSaleMonthSummary);

    /**
     * 删除钢球月度销售汇总（图专用）
     * 
     * @param id 钢球月度销售汇总（图专用）主键
     * @return 结果
     */
    public int deleteSteelSaleMonthSummaryById(Long id);

    /**
     * 批量删除钢球月度销售汇总（图专用）
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSteelSaleMonthSummaryByIds(Long[] ids);
}
