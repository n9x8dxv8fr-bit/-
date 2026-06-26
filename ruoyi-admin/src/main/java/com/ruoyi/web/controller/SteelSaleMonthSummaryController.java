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
import com.ruoyi.system.domain.SteelSaleMonthSummary;
import com.ruoyi.system.service.ISteelSaleMonthSummaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 钢球月度销售汇总（图专用）Controller
 * 
 * @author ruoyi
 * @date 2026-01-10
 */
@RestController
@RequestMapping("/system/summary")
public class SteelSaleMonthSummaryController extends BaseController
{
    @Autowired
    private ISteelSaleMonthSummaryService steelSaleMonthSummaryService;

    /**
     * 查询钢球月度销售汇总（图专用）列表
     */
    @PreAuthorize("@ss.hasPermi('system:summary:list')")
    @GetMapping("/list")
    public TableDataInfo list(SteelSaleMonthSummary steelSaleMonthSummary)
    {
        startPage();
        List<SteelSaleMonthSummary> list = steelSaleMonthSummaryService.selectSteelSaleMonthSummaryList(steelSaleMonthSummary);
        return getDataTable(list);
    }

    /**
     * 导出钢球月度销售汇总（图专用）列表
     */
    @PreAuthorize("@ss.hasPermi('system:summary:export')")
    @Log(title = "钢球月度销售汇总（图专用）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SteelSaleMonthSummary steelSaleMonthSummary)
    {
        List<SteelSaleMonthSummary> list = steelSaleMonthSummaryService.selectSteelSaleMonthSummaryList(steelSaleMonthSummary);
        ExcelUtil<SteelSaleMonthSummary> util = new ExcelUtil<SteelSaleMonthSummary>(SteelSaleMonthSummary.class);
        util.exportExcel(response, list, "钢球月度销售汇总（图专用）数据");
    }

    /**
     * 获取钢球月度销售汇总（图专用）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:summary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(steelSaleMonthSummaryService.selectSteelSaleMonthSummaryById(id));
    }

    /**
     * 新增钢球月度销售汇总（图专用）
     */
    @PreAuthorize("@ss.hasPermi('system:summary:add')")
    @Log(title = "钢球月度销售汇总（图专用）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SteelSaleMonthSummary steelSaleMonthSummary)
    {
        return toAjax(steelSaleMonthSummaryService.insertSteelSaleMonthSummary(steelSaleMonthSummary));
    }

    /**
     * 修改钢球月度销售汇总（图专用）
     */
    @PreAuthorize("@ss.hasPermi('system:summary:edit')")
    @Log(title = "钢球月度销售汇总（图专用）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SteelSaleMonthSummary steelSaleMonthSummary)
    {
        return toAjax(steelSaleMonthSummaryService.updateSteelSaleMonthSummary(steelSaleMonthSummary));
    }

    /**
     * 删除钢球月度销售汇总（图专用）
     */
    @PreAuthorize("@ss.hasPermi('system:summary:remove')")
    @Log(title = "钢球月度销售汇总（图专用）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(steelSaleMonthSummaryService.deleteSteelSaleMonthSummaryByIds(ids));
    }
}
