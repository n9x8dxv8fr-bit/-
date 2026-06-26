package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SteelSaleMonthSummaryMapper;
import com.ruoyi.system.domain.SteelSaleMonthSummary;
import com.ruoyi.system.service.ISteelSaleMonthSummaryService;

/**
 * 钢球月度销售汇总（图专用）Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-10
 */
@Service
public class SteelSaleMonthSummaryServiceImpl implements ISteelSaleMonthSummaryService 
{
    @Autowired
    private SteelSaleMonthSummaryMapper steelSaleMonthSummaryMapper;

    /**
     * 查询钢球月度销售汇总（图专用）
     * 
     * @param id 钢球月度销售汇总（图专用）主键
     * @return 钢球月度销售汇总（图专用）
     */
    @Override
    public SteelSaleMonthSummary selectSteelSaleMonthSummaryById(Long id)
    {
        return steelSaleMonthSummaryMapper.selectSteelSaleMonthSummaryById(id);
    }

    /**
     * 查询钢球月度销售汇总（图专用）列表
     * 
     * @param steelSaleMonthSummary 钢球月度销售汇总（图专用）
     * @return 钢球月度销售汇总（图专用）
     */
    @Override
    public List<SteelSaleMonthSummary> selectSteelSaleMonthSummaryList(SteelSaleMonthSummary steelSaleMonthSummary)
    {
        return steelSaleMonthSummaryMapper.selectSteelSaleMonthSummaryList(steelSaleMonthSummary);
    }

    /**
     * 新增钢球月度销售汇总（图专用）
     * 
     * @param steelSaleMonthSummary 钢球月度销售汇总（图专用）
     * @return 结果
     */
    @Override
    public int insertSteelSaleMonthSummary(SteelSaleMonthSummary steelSaleMonthSummary)
    {
        steelSaleMonthSummary.setCreateTime(DateUtils.getNowDate());
        return steelSaleMonthSummaryMapper.insertSteelSaleMonthSummary(steelSaleMonthSummary);
    }

    /**
     * 修改钢球月度销售汇总（图专用）
     * 
     * @param steelSaleMonthSummary 钢球月度销售汇总（图专用）
     * @return 结果
     */
    @Override
    public int updateSteelSaleMonthSummary(SteelSaleMonthSummary steelSaleMonthSummary)
    {
        steelSaleMonthSummary.setUpdateTime(DateUtils.getNowDate());
        return steelSaleMonthSummaryMapper.updateSteelSaleMonthSummary(steelSaleMonthSummary);
    }

    /**
     * 批量删除钢球月度销售汇总（图专用）
     * 
     * @param ids 需要删除的钢球月度销售汇总（图专用）主键
     * @return 结果
     */
    @Override
    public int deleteSteelSaleMonthSummaryByIds(Long[] ids)
    {
        return steelSaleMonthSummaryMapper.deleteSteelSaleMonthSummaryByIds(ids);
    }

    /**
     * 删除钢球月度销售汇总（图专用）信息
     * 
     * @param id 钢球月度销售汇总（图专用）主键
     * @return 结果
     */
    @Override
    public int deleteSteelSaleMonthSummaryById(Long id)
    {
        return steelSaleMonthSummaryMapper.deleteSteelSaleMonthSummaryById(id);
    }
}
