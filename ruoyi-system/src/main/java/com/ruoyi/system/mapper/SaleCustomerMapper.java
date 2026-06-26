package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SaleCustomer;

/**
 * 客户Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
public interface SaleCustomerMapper 
{
    /**
     * 查询客户
     * 
     * @param id 客户主键
     * @return 客户
     */
    public SaleCustomer selectSaleCustomerById(Long id);

    /**
     * 查询客户列表
     * 
     * @param saleCustomer 客户
     * @return 客户集合
     */
    public List<SaleCustomer> selectSaleCustomerList(SaleCustomer saleCustomer);

    /**
     * 新增客户
     * 
     * @param saleCustomer 客户
     * @return 结果
     */
    public int insertSaleCustomer(SaleCustomer saleCustomer);

    /**
     * 修改客户
     * 
     * @param saleCustomer 客户
     * @return 结果
     */
    public int updateSaleCustomer(SaleCustomer saleCustomer);

    /**
     * 删除客户
     * 
     * @param id 客户主键
     * @return 结果
     */
    public int deleteSaleCustomerById(Long id);

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSaleCustomerByIds(Long[] ids);
}
