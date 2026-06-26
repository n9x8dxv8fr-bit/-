package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SaleItem;

/**
 * 销售明细Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
public interface SaleItemMapper 
{
    /**
     * 查询销售明细
     * 
     * @param id 销售明细主键
     * @return 销售明细
     */
    public SaleItem selectSaleItemById(Long id);

    /**
     * 查询销售明细列表
     * 
     * @param saleItem 销售明细
     * @return 销售明细集合
     */
    public List<SaleItem> selectSaleItemList(SaleItem saleItem);

    /**
     * 新增销售明细
     * 
     * @param saleItem 销售明细
     * @return 结果
     */
    public int insertSaleItem(SaleItem saleItem);

    /**
     * 修改销售明细
     * 
     * @param saleItem 销售明细
     * @return 结果
     */
    public int updateSaleItem(SaleItem saleItem);

    /**
     * 删除销售明细
     * 
     * @param id 销售明细主键
     * @return 结果
     */
    public int deleteSaleItemById(Long id);

    /**
     * 批量删除销售明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSaleItemByIds(Long[] ids);

    /**
     * 根据销售单ID删除销售明细
     *
     * @param orderId 销售单ID
     * @return 结果
     */
    public int deleteSaleItemByOrderId(Long orderId);
}
