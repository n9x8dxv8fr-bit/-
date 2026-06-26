package com.ruoyi.system.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CustomerBillFlowMapper;
import com.ruoyi.system.domain.CustomerBillFlow;
import com.ruoyi.system.service.ICustomerBillFlowService;
// 新增：引入订单实体类
import com.ruoyi.system.domain.SysOrder;

/**
 * 客户账单流水Service业务层处理
 *
 * @author ruoyi
 * @date 2026-01-06
 */
@Service
public class CustomerBillFlowServiceImpl implements ICustomerBillFlowService
{
    @Autowired
    private CustomerBillFlowMapper customerBillFlowMapper;

    /**
     * 查询客户账单流水
     *
     * @param id 客户账单流水主键
     * @return 客户账单流水
     */
    @Override
    public CustomerBillFlow selectCustomerBillFlowById(String id)
    {
        return customerBillFlowMapper.selectCustomerBillFlowById(id);
    }

    /**
     * 查询客户账单流水列表
     *
     * @param customerBillFlow 客户账单流水
     * @return 客户账单流水
     */
    @Override
    public List<CustomerBillFlow> selectCustomerBillFlowList(CustomerBillFlow customerBillFlow)
    {
        return customerBillFlowMapper.selectCustomerBillFlowList(customerBillFlow);
    }

    /**
     * 新增客户账单流水
     *
     * @param customerBillFlow 客户账单流水
     * @return 结果
     */
    @Override
    public int insertCustomerBillFlow(CustomerBillFlow customerBillFlow)
    {
        customerBillFlow.setCreateTime(DateUtils.getNowDate());
        return customerBillFlowMapper.insertCustomerBillFlow(customerBillFlow);
    }

    /**
     * 修改客户账单流水
     *
     * @param customerBillFlow 客户账单流水
     * @return 结果
     */
    @Override
    public int updateCustomerBillFlow(CustomerBillFlow customerBillFlow)
    {
        customerBillFlow.setUpdateTime(DateUtils.getNowDate());
        return customerBillFlowMapper.updateCustomerBillFlow(customerBillFlow);
    }

    /**
     * 批量删除客户账单流水
     *
     * @param ids 需要删除的客户账单流水主键
     * @return 结果
     */
    @Override
    public int deleteCustomerBillFlowByIds(String[] ids)
    {
        return customerBillFlowMapper.deleteCustomerBillFlowByIds(ids);
    }

    /**
     * 删除客户账单流水信息
     *
     * @param id 客户账单流水主键
     * @return 结果
     */
    @Override
    public int deleteCustomerBillFlowById(String id)
    {
        return customerBillFlowMapper.deleteCustomerBillFlowById(id);
    }

    /**
     * 根据订单自动生成账单流水
     * @param order 订单对象
     */
    public void createBillFromOrder(SysOrder order) {
        // 1. 创建账单流水对象（核心：必须先定义）
        CustomerBillFlow bill = new CustomerBillFlow();

        // 2. 填充订单相关数据（严格匹配实体类字段/方法名）
        bill.setCustomerName(com.ruoyi.common.utils.StringUtils.isNotBlank(order.getCustomerName()) ? order.getCustomerName() : "未知客户");       // 客户姓名
        bill.setCustomerPhone(order.getCustomerPhone());     // 客户电话
        bill.setCustomerAddress(order.getCustomerAddress()); // 客户地址
        bill.setBillNo("BILL_" + System.currentTimeMillis());// 自动生成账单编号
        bill.setBillType("1");                               // 账单类型：1=收入
        bill.setAmount(order.getTotalPrice() == null ? BigDecimal.ZERO : order.getTotalPrice());               // 交易金额（订单总价）
        bill.setTradeDate(order.getCreateTime());            // 交易日期（订单创建时间）
        bill.setPaymentMethod("待支付");                      // 支付方式
        bill.setBillStatus("1");                             // 账单状态：1=已生效
        bill.setBusinessScene("钢球订单");                    // 业务场景
        bill.setRelatedOrderNo(order.getOrderNo());          // 关联订单编号
        bill.setCurrency("CNY");                             // 货币类型：人民币
        bill.setCreateTime(DateUtils.getNowDate());          // 账单创建时间

        // 3. 设置关联订单ID（Long类型，仅当ID存在时设置，消除null爆红）
        if (order.getId() != null) {
            bill.setOrderId(order.getId());
        }

        // 4. 保存账单到数据库
        this.insertCustomerBillFlow(bill);
    }}