package com.ruoyi.system.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.ruoyi.system.domain.SteelBallInoutFlow;

/**
 * 钢球进出库流水（含统计字段）Service接口
 *
 * @author ruoyi
 * @date 2026-03-22
 */
public interface ISteelBallInoutFlowService
{
    /**
     * 查询钢球进出库流水（含统计字段）
     *
     * @param id 钢球进出库流水（含统计字段）主键
     * @return 钢球进出库流水（含统计字段）
     */
    public SteelBallInoutFlow selectSteelBallInoutFlowById(Long id);

    /**
     * 查询钢球进出库流水（含统计字段）列表（带分页）
     *
     * @param steelBallInoutFlow 钢球进出库流水（含统计字段）
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 钢球进出库流水（含统计字段）分页列表
     */
    public PageInfo<SteelBallInoutFlow> selectSteelBallInoutFlowList(SteelBallInoutFlow steelBallInoutFlow, Integer pageNum, Integer pageSize);

    /**
     * 查询钢球进出库流水（含统计字段）列表（无分页）
     *
     * @param steelBallInoutFlow 钢球进出库流水（含统计字段）
     * @return 钢球进出库流水（含统计字段）列表
     */
    public List<SteelBallInoutFlow> selectSteelBallInoutFlowList(SteelBallInoutFlow steelBallInoutFlow);

    /**
     * 新增钢球进出库流水（含统计字段）
     *
     * @param steelBallInoutFlow 钢球进出库流水（含统计字段）
     * @return 结果
     */
    public int insertSteelBallInoutFlow(SteelBallInoutFlow steelBallInoutFlow);

    /**
     * 修改钢球进出库流水（含统计字段）
     *
     * @param steelBallInoutFlow 钢球进出库流水（含统计字段）
     * @return 结果
     */
    public int updateSteelBallInoutFlow(SteelBallInoutFlow steelBallInoutFlow);

    /**
     * 批量删除钢球进出库流水（含统计字段）
     *
     * @param ids 需要删除的钢球进出库流水（含统计字段）主键
     * @return 结果
     */
    public int deleteSteelBallInoutFlowByIds(Long[] ids);

    /**
     * 删除钢球进出库流水（含统计字段）信息
     *
     * @param id 钢球进出库流水（含统计字段）主键
     * @return 结果
     */
    public int deleteSteelBallInoutFlowById(Long id);
}