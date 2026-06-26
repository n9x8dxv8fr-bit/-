package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SteelBallInoutFlowMapper;
import com.ruoyi.system.domain.SteelBallInoutFlow;
import com.ruoyi.system.service.ISteelBallInoutFlowService;

/**
 * 钢球进出库流水（含统计字段）Service业务层处理
 *
 * @author ruoyi
 * @date 2026-03-22
 */
@Service
public class SteelBallInoutFlowServiceImpl implements ISteelBallInoutFlowService
{
    @Autowired
    private SteelBallInoutFlowMapper steelBallInoutFlowMapper;

    /**
     * 查询钢球进出库流水（含统计字段）
     *
     * @param id 钢球进出库流水（含统计字段）主键
     * @return 钢球进出库流水（含统计字段）
     */
    @Override
    public SteelBallInoutFlow selectSteelBallInoutFlowById(Long id)
    {
        return steelBallInoutFlowMapper.selectSteelBallInoutFlowById(id);
    }

    /**
     * 查询钢球进出库流水（含统计字段）列表（带分页）
     *
     * @param steelBallInoutFlow 钢球进出库流水（含统计字段）
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 钢球进出库流水（含统计字段）分页列表
     */
    @Override
    public PageInfo<SteelBallInoutFlow> selectSteelBallInoutFlowList(SteelBallInoutFlow steelBallInoutFlow, Integer pageNum, Integer pageSize)
    {
        // 启用PageHelper分页（核心：分页参数在这里处理，XML中无需写limit）
        PageHelper.startPage(pageNum, pageSize);
        List<SteelBallInoutFlow> list = steelBallInoutFlowMapper.selectSteelBallInoutFlowList(steelBallInoutFlow);
        return new PageInfo<>(list);
    }

    // 保留原无参列表查询方法（兼容旧调用）
    @Override
    public List<SteelBallInoutFlow> selectSteelBallInoutFlowList(SteelBallInoutFlow steelBallInoutFlow)
    {
        return steelBallInoutFlowMapper.selectSteelBallInoutFlowList(steelBallInoutFlow);
    }

    /**
     * 新增钢球进出库流水（含统计字段）
     *
     * @param steelBallInoutFlow 钢球进出库流水（含统计字段）
     * @return 结果
     */
    @Override
    public int insertSteelBallInoutFlow(SteelBallInoutFlow steelBallInoutFlow)
    {
        steelBallInoutFlow.setCreateTime(DateUtils.getNowDate());
        return steelBallInoutFlowMapper.insertSteelBallInoutFlow(steelBallInoutFlow);
    }

    /**
     * 修改钢球进出库流水（含统计字段）
     *
     * @param steelBallInoutFlow 钢球进出库流水（含统计字段）
     * @return 结果
     */
    @Override
    public int updateSteelBallInoutFlow(SteelBallInoutFlow steelBallInoutFlow)
    {
        steelBallInoutFlow.setUpdateTime(DateUtils.getNowDate());
        return steelBallInoutFlowMapper.updateSteelBallInoutFlow(steelBallInoutFlow);
    }

    /**
     * 批量删除钢球进出库流水（含统计字段）
     *
     * @param ids 需要删除的钢球进出库流水（含统计字段）主键
     * @return 结果
     */
    @Override
    public int deleteSteelBallInoutFlowByIds(Long[] ids)
    {
        return steelBallInoutFlowMapper.deleteSteelBallInoutFlowByIds(ids);
    }

    /**
     * 删除钢球进出库流水（含统计字段）信息
     *
     * @param id 钢球进出库流水（含统计字段）主键
     * @return 结果
     */
    @Override
    public int deleteSteelBallInoutFlowById(Long id)
    {
        return steelBallInoutFlowMapper.deleteSteelBallInoutFlowById(id);
    }
}