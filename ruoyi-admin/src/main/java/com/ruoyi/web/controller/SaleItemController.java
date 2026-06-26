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
import com.ruoyi.system.domain.SaleItem;
import com.ruoyi.system.service.ISaleItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售明细Controller
 * 
 * @author ruoyi
 * @date 2026-03-27
 */
@RestController
@RequestMapping("/system/item")
public class SaleItemController extends BaseController
{
    @Autowired
    private ISaleItemService saleItemService;

    /**
     * 查询销售明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(SaleItem saleItem)
    {
        startPage();
        List<SaleItem> list = saleItemService.selectSaleItemList(saleItem);
        return getDataTable(list);
    }

    /**
     * 导出销售明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:export')")
    @Log(title = "销售明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SaleItem saleItem)
    {
        List<SaleItem> list = saleItemService.selectSaleItemList(saleItem);
        ExcelUtil<SaleItem> util = new ExcelUtil<SaleItem>(SaleItem.class);
        util.exportExcel(response, list, "销售明细数据");
    }

    /**
     * 获取销售明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(saleItemService.selectSaleItemById(id));
    }

    /**
     * 新增销售明细
     */
    @PreAuthorize("@ss.hasPermi('system:item:add')")
    @Log(title = "销售明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SaleItem saleItem)
    {
        return toAjax(saleItemService.insertSaleItem(saleItem));
    }

    /**
     * 修改销售明细
     */
    @PreAuthorize("@ss.hasPermi('system:item:edit')")
    @Log(title = "销售明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SaleItem saleItem)
    {
        return toAjax(saleItemService.updateSaleItem(saleItem));
    }

    /**
     * 删除销售明细
     */
    @PreAuthorize("@ss.hasPermi('system:item:remove')")
    @Log(title = "销售明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(saleItemService.deleteSaleItemByIds(ids));
    }
}
