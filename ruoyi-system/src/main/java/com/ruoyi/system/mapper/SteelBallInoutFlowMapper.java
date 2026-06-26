package com.ruoyi.system.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.ruoyi.system.domain.SteelBallInoutFlow;

/**
 * 钢球进出库流水（含统计字段）Mapper接口
 *
 * @author ruoyi
 * @date 2026-03-22
 */
public interface SteelBallInoutFlowMapper
{
    /**
     * 查询钢球进出库流水（含统计字段）
     *
     * @param id 钢球进出库流水（含统计字段）主键
     * @return 钢球进出库流水（含统计字段）
     */
    public SteelBallInoutFlow selectSteelBallInoutFlowById(Long id);

    /**
     * 查询钢球进出库流水（含统计字段）列表
     *
     * @param steelBallInoutFlow 钢球进出库流水（含统计字段）
     * @return 钢球进出库流水（含统计字段）集合
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
     * 删除钢球进出库流水（含统计字段）
     *
     * @param id 钢球进出库流水（含统计字段）主键
     * @return 结果
     */
    public int deleteSteelBallInoutFlowById(Long id);

    /**
     * 批量删除钢球进出库流水（含统计字段）
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSteelBallInoutFlowByIds(Long[] ids);

    // ========== 核心修改：使用Map传参，彻底解决参数绑定 ==========
    /**
     * 查询某产品某类型的累计数量
     */
    Long selectSumQuantityByProduct(Map<String, Object> paramMap);

    /**
     * 查询某产品某类型的累计金额
     */
    BigDecimal selectSumAmountByProduct(Map<String, Object> paramMap);

    /**
     * 查询某客户某类型的累计数量
     */
    Long selectSumQuantityByCustomer(Map<String, Object> paramMap);

    /**
     * 查询某客户某类型的累计金额
     */
    BigDecimal selectSumAmountByCustomer(Map<String, Object> paramMap);
}