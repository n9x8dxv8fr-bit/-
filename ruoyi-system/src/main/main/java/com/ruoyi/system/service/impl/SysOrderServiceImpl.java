package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.mapper.SysOrderMapper;
import com.ruoyi.system.service.ISysOrderService;
import com.ruoyi.system.service.ICustomerBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOrderServiceImpl implements ISysOrderService {

    @Autowired
    private SysOrderMapper orderMapper;

    @Autowired
    private ICustomerBillService billService;

    @Override
    public void submitOrder(SysOrder order) {
        // 1. 设置订单默认状态
        order.setStatus("待审核");
        order.setCreateTime(DateUtils.getNowDate());
        order.setUpdateTime(DateUtils.getNowDate());
        // 2. 保存订单
        orderMapper.insertSysOrder(order);
        // 3. 自动生成账单（同步到管理员账单流水页）
        billService.createBillFromOrder(order);
    }
}