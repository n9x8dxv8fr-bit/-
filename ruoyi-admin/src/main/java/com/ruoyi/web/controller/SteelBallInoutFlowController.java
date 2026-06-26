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
import com.ruoyi.system.domain.SteelBallInoutFlow;
import com.ruoyi.system.service.ISteelBallInoutFlowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 钢球进出库流水（含统计字段）Controller
 * 
 * @author ruoyi
 * @date 2026-03-22
 */
@RestController
@RequestMapping("/system/flow")
public class SteelBallInoutFlowController extends BaseController
{
    @Autowired
    private ISteelBallInoutFlowService steelBallInoutFlowService;

    /**
     * 查询钢球进出库流水（含统计字段）列表
     */
    @PreAuthorize("@ss.hasPermi('system:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(SteelBallInoutFlow steelBallInoutFlow)
    {
        startPage();
        List<SteelBallInoutFlow> list = steelBallInoutFlowService.selectSteelBallInoutFlowList(steelBallInoutFlow);
        return getDataTable(list);
    }

    /**
     * 导出钢球进出库流水（含统计字段）列表
     */
    @PreAuthorize("@ss.hasPermi('system:flow:export')")
    @Log(title = "钢球进出库流水（含统计字段）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SteelBallInoutFlow steelBallInoutFlow)
    {
        List<SteelBallInoutFlow> list = steelBallInoutFlowService.selectSteelBallInoutFlowList(steelBallInoutFlow);
        ExcelUtil<SteelBallInoutFlow> util = new ExcelUtil<SteelBallInoutFlow>(SteelBallInoutFlow.class);
        util.exportExcel(response, list, "钢球进出库流水（含统计字段）数据");
    }

    /**
     * 获取钢球进出库流水（含统计字段）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:flow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(steelBallInoutFlowService.selectSteelBallInoutFlowById(id));
    }

    /**
     * 新增钢球进出库流水（含统计字段）
     */
    @PreAuthorize("@ss.hasPermi('system:flow:add')")
    @Log(title = "钢球进出库流水（含统计字段）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SteelBallInoutFlow steelBallInoutFlow)
    {
        return toAjax(steelBallInoutFlowService.insertSteelBallInoutFlow(steelBallInoutFlow));
    }

    /**
     * 修改钢球进出库流水（含统计字段）
     */
    @PreAuthorize("@ss.hasPermi('system:flow:edit')")
    @Log(title = "钢球进出库流水（含统计字段）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SteelBallInoutFlow steelBallInoutFlow)
    {
        return toAjax(steelBallInoutFlowService.updateSteelBallInoutFlow(steelBallInoutFlow));
    }

    /**
     * 删除钢球进出库流水（含统计字段）
     */
    @PreAuthorize("@ss.hasPermi('system:flow:remove')")
    @Log(title = "钢球进出库流水（含统计字段）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(steelBallInoutFlowService.deleteSteelBallInoutFlowByIds(ids));
    }
}
