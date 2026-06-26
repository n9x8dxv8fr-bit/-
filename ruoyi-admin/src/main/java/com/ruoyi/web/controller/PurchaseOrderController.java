package com.ruoyi.web.controller;

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
import com.ruoyi.system.domain.PurchaseOrder;
import com.ruoyi.system.service.IPurchaseOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购订单Controller
 *
 * @author ruoyi
 * @date 2026-03-27
 */
@RestController
@RequestMapping("/system/purchaseOrder")
public class PurchaseOrderController extends BaseController
{
    @Autowired
    private IPurchaseOrderService purchaseOrderService;

    // 查询列表
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:list')") // 👈 小写
    @GetMapping("/list")
    public TableDataInfo list(PurchaseOrder purchaseOrder) {
        startPage();
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderList(purchaseOrder);
        return getDataTable(list);
    }

    // 导出
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:export')") // 👈 小写
    @PostMapping("/export")
    public void export(HttpServletResponse response, PurchaseOrder purchaseOrder) {
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderList(purchaseOrder);
        ExcelUtil<PurchaseOrder> util = new ExcelUtil<PurchaseOrder>(PurchaseOrder.class);
        util.exportExcel(response, list, "采购订单数据");
    }

    // 详情
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:query')") // 👈 小写
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(purchaseOrderService.selectPurchaseOrderById(id));
    }

    // 新增
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:add')") // 👈 小写
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseOrder purchaseOrder) {
        return toAjax(purchaseOrderService.insertPurchaseOrder(purchaseOrder));
    }

    // 修改
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:edit')") // 👈 小写
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseOrder purchaseOrder) {
        return toAjax(purchaseOrderService.updatePurchaseOrder(purchaseOrder));
    }

    // 删除
    @PreAuthorize("@ss.hasPermi('system:purchaseOrder:remove')") // 👈 小写
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(purchaseOrderService.deletePurchaseOrderByIds(ids));
    }
}