package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SaleItemMapper;
import com.ruoyi.system.domain.SaleItem;
import com.ruoyi.system.service.ISaleItemService;

/**
 * 销售明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
@Service
public class SaleItemServiceImpl implements ISaleItemService 
{
    @Autowired
    private SaleItemMapper saleItemMapper;

    /**
     * 查询销售明细
     * 
     * @param id 销售明细主键
     * @return 销售明细
     */
    @Override
    public SaleItem selectSaleItemById(Long id)
    {
        return saleItemMapper.selectSaleItemById(id);
    }

    /**
     * 查询销售明细列表
     * 
     * @param saleItem 销售明细
     * @return 销售明细
     */
    @Override
    public List<SaleItem> selectSaleItemList(SaleItem saleItem)
    {
        return saleItemMapper.selectSaleItemList(saleItem);
    }

    /**
     * 新增销售明细
     * 
     * @param saleItem 销售明细
     * @return 结果
     */
    @Override
    public int insertSaleItem(SaleItem saleItem)
    {
        return saleItemMapper.insertSaleItem(saleItem);
    }

    /**
     * 修改销售明细
     * 
     * @param saleItem 销售明细
     * @return 结果
     */
    @Override
    public int updateSaleItem(SaleItem saleItem)
    {
        return saleItemMapper.updateSaleItem(saleItem);
    }

    /**
     * 批量删除销售明细
     * 
     * @param ids 需要删除的销售明细主键
     * @return 结果
     */
    @Override
    public int deleteSaleItemByIds(Long[] ids)
    {
        return saleItemMapper.deleteSaleItemByIds(ids);
    }

    /**
     * 删除销售明细信息
     * 
     * @param id 销售明细主键
     * @return 结果
     */
    @Override
    public int deleteSaleItemById(Long id)
    {
        return saleItemMapper.deleteSaleItemById(id);
    }
}
