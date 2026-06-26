package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SupplySupplierMapper;
import com.ruoyi.system.domain.SupplySupplier;
import com.ruoyi.system.service.ISupplySupplierService;

/**
 * 供应商Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
@Service
public class SupplySupplierServiceImpl implements ISupplySupplierService 
{
    @Autowired
    private SupplySupplierMapper supplySupplierMapper;

    /**
     * 查询供应商
     * 
     * @param id 供应商主键
     * @return 供应商
     */
    @Override
    public SupplySupplier selectSupplySupplierById(Long id)
    {
        return supplySupplierMapper.selectSupplySupplierById(id);
    }

    /**
     * 查询供应商列表
     * 
     * @param supplySupplier 供应商
     * @return 供应商
     */
    @Override
    public List<SupplySupplier> selectSupplySupplierList(SupplySupplier supplySupplier)
    {
        return supplySupplierMapper.selectSupplySupplierList(supplySupplier);
    }

    /**
     * 新增供应商
     * 
     * @param supplySupplier 供应商
     * @return 结果
     */
    @Override
    public int insertSupplySupplier(SupplySupplier supplySupplier)
    {
        supplySupplier.setCreateTime(DateUtils.getNowDate());
        return supplySupplierMapper.insertSupplySupplier(supplySupplier);
    }

    /**
     * 修改供应商
     * 
     * @param supplySupplier 供应商
     * @return 结果
     */
    @Override
    public int updateSupplySupplier(SupplySupplier supplySupplier)
    {
        return supplySupplierMapper.updateSupplySupplier(supplySupplier);
    }

    /**
     * 批量删除供应商
     * 
     * @param ids 需要删除的供应商主键
     * @return 结果
     */
    @Override
    public int deleteSupplySupplierByIds(Long[] ids)
    {
        return supplySupplierMapper.deleteSupplySupplierByIds(ids);
    }

    /**
     * 删除供应商信息
     * 
     * @param id 供应商主键
     * @return 结果
     */
    @Override
    public int deleteSupplySupplierById(Long id)
    {
        return supplySupplierMapper.deleteSupplySupplierById(id);
    }
}
