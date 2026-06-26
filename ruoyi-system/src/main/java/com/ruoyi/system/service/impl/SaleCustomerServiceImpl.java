package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SaleCustomerMapper;
import com.ruoyi.system.domain.SaleCustomer;
import com.ruoyi.system.service.ISaleCustomerService;

/**
 * 客户Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
@Service
public class SaleCustomerServiceImpl implements ISaleCustomerService 
{
    @Autowired
    private SaleCustomerMapper saleCustomerMapper;

    /**
     * 查询客户
     * 
     * @param id 客户主键
     * @return 客户
     */
    @Override
    public SaleCustomer selectSaleCustomerById(Long id)
    {
        return saleCustomerMapper.selectSaleCustomerById(id);
    }

    /**
     * 查询客户列表
     * 
     * @param saleCustomer 客户
     * @return 客户
     */
    @Override
    public List<SaleCustomer> selectSaleCustomerList(SaleCustomer saleCustomer)
    {
        return saleCustomerMapper.selectSaleCustomerList(saleCustomer);
    }

    /**
     * 新增客户
     * 
     * @param saleCustomer 客户
     * @return 结果
     */
    @Override
    public int insertSaleCustomer(SaleCustomer saleCustomer)
    {
        saleCustomer.setCreateTime(DateUtils.getNowDate());
        return saleCustomerMapper.insertSaleCustomer(saleCustomer);
    }

    /**
     * 修改客户
     * 
     * @param saleCustomer 客户
     * @return 结果
     */
    @Override
    public int updateSaleCustomer(SaleCustomer saleCustomer)
    {
        return saleCustomerMapper.updateSaleCustomer(saleCustomer);
    }

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deleteSaleCustomerByIds(Long[] ids)
    {
        return saleCustomerMapper.deleteSaleCustomerByIds(ids);
    }

    /**
     * 删除客户信息
     * 
     * @param id 客户主键
     * @return 结果
     */
    @Override
    public int deleteSaleCustomerById(Long id)
    {
        return saleCustomerMapper.deleteSaleCustomerById(id);
    }
}
