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
import com.ruoyi.system.domain.TProduct;
import com.ruoyi.system.service.ITProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 钢球产品信息Controller
 * 
 * @author ruoyi
 * @date 2025-12-28
 */
@RestController
@RequestMapping("/system/product")
public class TProductController extends BaseController
{
    @Autowired
    private ITProductService tProductService;

    /**
     * 查询钢球产品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(TProduct tProduct)
    {
        startPage();
        List<TProduct> list = tProductService.selectTProductList(tProduct);
        return getDataTable(list);
    }

    /**
     * 导出钢球产品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:product:export')")
    @Log(title = "钢球产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TProduct tProduct)
    {
        List<TProduct> list = tProductService.selectTProductList(tProduct);
        ExcelUtil<TProduct> util = new ExcelUtil<TProduct>(TProduct.class);
        util.exportExcel(response, list, "钢球产品信息数据");
    }

    /**
     * 获取钢球产品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tProductService.selectTProductById(id));
    }

    /**
     * 新增钢球产品信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:add')")
    @Log(title = "钢球产品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TProduct tProduct)
    {
        return toAjax(tProductService.insertTProduct(tProduct));
    }

    /**
     * 修改钢球产品信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "钢球产品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TProduct tProduct)
    {
        return toAjax(tProductService.updateTProduct(tProduct));
    }

    /**
     * 删除钢球产品信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:remove')")
    @Log(title = "钢球产品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tProductService.deleteTProductByIds(ids));
    }
}
