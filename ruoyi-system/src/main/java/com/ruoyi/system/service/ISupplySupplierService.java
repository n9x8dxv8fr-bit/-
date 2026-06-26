package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SupplySupplier;

/**
 * 供应商Service接口
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
public interface ISupplySupplierService 
{
    /**
     * 查询供应商
     * 
     * @param id 供应商主键
     * @return 供应商
     */
    public SupplySupplier selectSupplySupplierById(Long id);

    /**
     * 查询供应商列表
     * 
     * @param supplySupplier 供应商
     * @return 供应商集合
     */
    public List<SupplySupplier> selectSupplySupplierList(SupplySupplier supplySupplier);

    /**
     * 新增供应商
     * 
     * @param supplySupplier 供应商
     * @return 结果
     */
    public int insertSupplySupplier(SupplySupplier supplySupplier);

    /**
     * 修改供应商
     * 
     * @param supplySupplier 供应商
     * @return 结果
     */
    public int updateSupplySupplier(SupplySupplier supplySupplier);

    /**
     * 批量删除供应商
     * 
     * @param ids 需要删除的供应商主键集合
     * @return 结果
     */
    public int deleteSupplySupplierByIds(Long[] ids);

    /**
     * 删除供应商信息
     * 
     * @param id 供应商主键
     * @return 结果
     */
    public int deleteSupplySupplierById(Long id);
}
