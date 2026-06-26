package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class SysOrderController extends BaseController {

    @Autowired
    private ISysOrderService orderService;

    @Anonymous
    @PostMapping("/submit")
    public AjaxResult submitOrder(@RequestBody SysOrder order) {
        try {
            orderService.submitOrder(order);
            return AjaxResult.success("订单提交成功，等待管理员审核");
        } catch (Exception e) {
            return AjaxResult.error("订单提交失败：" + e.getMessage());
        }
    }

    @GetMapping("/audit/list")
    public TableDataInfo listForAudit(SysOrder order)
    {
        startPage();
        List<SysOrder> list = orderService.selectSysOrderList(order);
        return getDataTable(list);
    }

    @PostMapping("/audit/{id}/approve")
    public AjaxResult approve(@PathVariable("id") Long id, @RequestBody(required = false) SysOrder payload)
    {
        try
        {
            orderService.approveOrder(id, payload == null ? null : payload.getRemark());
            return AjaxResult.success("审核通过，已生成销售明细");
        }
        catch (Exception e)
        {
            return AjaxResult.error("审核失败：" + e.getMessage());
        }
    }

    @PostMapping("/audit/{id}/reject")
    public AjaxResult reject(@PathVariable("id") Long id, @RequestBody(required = false) SysOrder payload)
    {
        try
        {
            orderService.rejectOrder(id, payload == null ? null : payload.getRemark());
            return AjaxResult.success("已驳回");
        }
        catch (Exception e)
        {
            return AjaxResult.error("驳回失败：" + e.getMessage());
        }
    }

    @GetMapping("/countPending")
    public AjaxResult countPending()
    {
        return AjaxResult.success(orderService.countPendingAuditOrders());
    }

    @GetMapping("/overview")
    public AjaxResult overview()
    {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("yesterdaySales", orderService.getYesterdaySalesQuantity());
        data.put("pendingAuditOrders", orderService.countPendingAuditOrders());
        return AjaxResult.success(data);
    }
}

