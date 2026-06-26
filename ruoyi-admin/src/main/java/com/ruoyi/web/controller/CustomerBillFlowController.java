package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.CustomerBillFlow;
import com.ruoyi.system.service.ICustomerBillFlowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户账单流水Controller
 * 
 * @author ruoyi
 * @date 2026-01-06
 */
@RestController
@RequestMapping("/system/customer")
public class CustomerBillFlowController extends BaseController
{
    @Autowired
    private ICustomerBillFlowService customerBillFlowService;

    /**
     * 查询客户账单流水列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerBillFlow customerBillFlow)
    {
        startPage();
        List<CustomerBillFlow> list = customerBillFlowService.selectCustomerBillFlowList(customerBillFlow);
        return getDataTable(list);
    }

    /**
     * 导出客户账单流水列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:export')")
    @Log(title = "客户账单流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerBillFlow customerBillFlow)
    {
        List<CustomerBillFlow> list = customerBillFlowService.selectCustomerBillFlowList(customerBillFlow);
        ExcelUtil<CustomerBillFlow> util = new ExcelUtil<CustomerBillFlow>(CustomerBillFlow.class);
        util.exportExcel(response, list, "客户账单流水数据");
    }

    /**
     * 获取客户账单流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(customerBillFlowService.selectCustomerBillFlowById(id));
    }

    /**
     * 新增客户账单流水
     */
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @Log(title = "客户账单流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerBillFlow customerBillFlow)
    {
        return toAjax(customerBillFlowService.insertCustomerBillFlow(customerBillFlow));
    }

    /**
     * 修改客户账单流水
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "客户账单流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerBillFlow customerBillFlow)
    {
        return toAjax(customerBillFlowService.updateCustomerBillFlow(customerBillFlow));
    }

    /**
     * 删除客户账单流水
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "客户账单流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(customerBillFlowService.deleteCustomerBillFlowByIds(ids));
    }
}
