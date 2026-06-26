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
import com.ruoyi.system.domain.QualityInspectionStandard;
import com.ruoyi.system.service.IQualityInspectionStandardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

@RestController
@RequestMapping("/system/qcStandard")
public class QualityInspectionStandardController extends BaseController
{
    @Autowired
    private IQualityInspectionStandardService qualityInspectionStandardService;

    @PreAuthorize("@ss.hasPermi('system:qcStandard:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityInspectionStandard qualityInspectionStandard)
    {
        startPage();
        List<QualityInspectionStandard> list = qualityInspectionStandardService.selectQualityInspectionStandardList(qualityInspectionStandard);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:qcStandard:export')")
    @Log(title = "\u8d28\u68c0\u6807\u51c6", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QualityInspectionStandard qualityInspectionStandard)
    {
        List<QualityInspectionStandard> list = qualityInspectionStandardService.selectQualityInspectionStandardList(qualityInspectionStandard);
        ExcelUtil<QualityInspectionStandard> util = new ExcelUtil<QualityInspectionStandard>(QualityInspectionStandard.class);
        util.exportExcel(response, list, "\u8d28\u68c0\u6807\u51c6\u6570\u636e");
    }

    @PreAuthorize("@ss.hasPermi('system:qcStandard:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qualityInspectionStandardService.selectQualityInspectionStandardById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:qcStandard:add')")
    @Log(title = "\u8d28\u68c0\u6807\u51c6", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityInspectionStandard qualityInspectionStandard)
    {
        return toAjax(qualityInspectionStandardService.insertQualityInspectionStandard(qualityInspectionStandard));
    }

    @PreAuthorize("@ss.hasPermi('system:qcStandard:edit')")
    @Log(title = "\u8d28\u68c0\u6807\u51c6", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityInspectionStandard qualityInspectionStandard)
    {
        return toAjax(qualityInspectionStandardService.updateQualityInspectionStandard(qualityInspectionStandard));
    }

    @PreAuthorize("@ss.hasPermi('system:qcStandard:remove')")
    @Log(title = "\u8d28\u68c0\u6807\u51c6", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qualityInspectionStandardService.deleteQualityInspectionStandardByIds(ids));
    }
}
