package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.StockInfo;

/**
 * 库存信息Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
public interface StockInfoMapper 
{
    /**
     * 查询库存信息
     * 
     * @param id 库存信息主键
     * @return 库存信息
     */
    public StockInfo selectStockInfoById(Long id);

    /**
     * 查询库存信息列表
     * 
     * @param stockInfo 库存信息
     * @return 库存信息集合
     */
    public List<StockInfo> selectStockInfoList(StockInfo stockInfo);

    /**
     * 新增库存信息
     * 
     * @param stockInfo 库存信息
     * @return 结果
     */
    public int insertStockInfo(StockInfo stockInfo);

    /**
     * 修改库存信息
     * 
     * @param stockInfo 库存信息
     * @return 结果
     */
    public int updateStockInfo(StockInfo stockInfo);

    /**
     * 删除库存信息
     * 
     * @param id 库存信息主键
     * @return 结果
     */
    public int deleteStockInfoById(Long id);

    /**
     * 批量删除库存信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockInfoByIds(Long[] ids);
}
