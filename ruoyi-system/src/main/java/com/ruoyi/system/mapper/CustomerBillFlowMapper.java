package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CustomerBillFlow;

/**
 * 客户账单流水Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-06
 */
public interface CustomerBillFlowMapper 
{
    /**
     * 查询客户账单流水
     * 
     * @param id 客户账单流水主键
     * @return 客户账单流水
     */
    public CustomerBillFlow selectCustomerBillFlowById(String id);

    /**
     * 查询客户账单流水列表
     * 
     * @param customerBillFlow 客户账单流水
     * @return 客户账单流水集合
     */
    public List<CustomerBillFlow> selectCustomerBillFlowList(CustomerBillFlow customerBillFlow);

    /**
     * 新增客户账单流水
     * 
     * @param customerBillFlow 客户账单流水
     * @return 结果
     */
    public int insertCustomerBillFlow(CustomerBillFlow customerBillFlow);

    /**
     * 修改客户账单流水
     * 
     * @param customerBillFlow 客户账单流水
     * @return 结果
     */
    public int updateCustomerBillFlow(CustomerBillFlow customerBillFlow);

    /**
     * 删除客户账单流水
     * 
     * @param id 客户账单流水主键
     * @return 结果
     */
    public int deleteCustomerBillFlowById(String id);

    /**
     * 批量删除客户账单流水
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerBillFlowByIds(String[] ids);
}
