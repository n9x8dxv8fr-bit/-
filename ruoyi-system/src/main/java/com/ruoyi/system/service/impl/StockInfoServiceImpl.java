package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StockInfoMapper;
import com.ruoyi.system.domain.StockInfo;
import com.ruoyi.system.service.IStockInfoService;

/**
 * 库存信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
@Service
public class StockInfoServiceImpl implements IStockInfoService 
{
    @Autowired
    private StockInfoMapper stockInfoMapper;

    /**
     * 查询库存信息
     * 
     * @param id 库存信息主键
     * @return 库存信息
     */
    @Override
    public StockInfo selectStockInfoById(Long id)
    {
        return stockInfoMapper.selectStockInfoById(id);
    }

    /**
     * 查询库存信息列表
     * 
     * @param stockInfo 库存信息
     * @return 库存信息
     */
    @Override
    public List<StockInfo> selectStockInfoList(StockInfo stockInfo)
    {
        return stockInfoMapper.selectStockInfoList(stockInfo);
    }

    /**
     * 新增库存信息
     * 
     * @param stockInfo 库存信息
     * @return 结果
     */
    @Override
    public int insertStockInfo(StockInfo stockInfo)
    {
        return stockInfoMapper.insertStockInfo(stockInfo);
    }

    /**
     * 修改库存信息
     * 
     * @param stockInfo 库存信息
     * @return 结果
     */
    @Override
    public int updateStockInfo(StockInfo stockInfo)
    {
        stockInfo.setUpdateTime(DateUtils.getNowDate());
        return stockInfoMapper.updateStockInfo(stockInfo);
    }

    /**
     * 批量删除库存信息
     * 
     * @param ids 需要删除的库存信息主键
     * @return 结果
     */
    @Override
    public int deleteStockInfoByIds(Long[] ids)
    {
        return stockInfoMapper.deleteStockInfoByIds(ids);
    }

    /**
     * 删除库存信息信息
     * 
     * @param id 库存信息主键
     * @return 结果
     */
    @Override
    public int deleteStockInfoById(Long id)
    {
        return stockInfoMapper.deleteStockInfoById(id);
    }
}
