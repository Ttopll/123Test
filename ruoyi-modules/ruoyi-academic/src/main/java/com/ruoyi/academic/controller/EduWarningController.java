package com.ruoyi.academic.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.academic.domain.EduWarning;
import com.ruoyi.academic.service.IEduWarningService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 学业预警Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/warning")
public class EduWarningController extends BaseController
{
    @Autowired
    private IEduWarningService eduWarningService;

    /**
     * 查询学业预警列表
     */
    @RequiresPermissions("academic:warning:list")
    @GetMapping("/list")
    public TableDataInfo list(EduWarning eduWarning)
    {
        startPage();
        List<EduWarning> list = eduWarningService.selectEduWarningList(eduWarning);
        return getDataTable(list);
    }

    /**
     * 导出学业预警列表
     */
    @RequiresPermissions("academic:warning:export")
    @Log(title = "学业预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduWarning eduWarning)
    {
        List<EduWarning> list = eduWarningService.selectEduWarningList(eduWarning);
        ExcelUtil<EduWarning> util = new ExcelUtil<EduWarning>(EduWarning.class);
        util.exportExcel(response, list, "学业预警数据");
    }

    /**
     * 获取学业预警详细信息
     */
    @RequiresPermissions("academic:warning:query")
    @GetMapping(value = "/{warningId}")
    public AjaxResult getInfo(@PathVariable("warningId") Long warningId)
    {
        return success(eduWarningService.selectEduWarningByWarningId(warningId));
    }

    /**
     * 新增学业预警
     */
    @RequiresPermissions("academic:warning:add")
    @Log(title = "学业预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduWarning eduWarning)
    {
        return toAjax(eduWarningService.insertEduWarning(eduWarning));
    }

    /**
     * 修改学业预警
     */
    @RequiresPermissions("academic:warning:edit")
    @Log(title = "学业预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduWarning eduWarning)
    {
        return toAjax(eduWarningService.updateEduWarning(eduWarning));
    }

    /**
     * 删除学业预警
     */
    @RequiresPermissions("academic:warning:remove")
    @Log(title = "学业预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{warningIds}")
    public AjaxResult remove(@PathVariable Long[] warningIds)
    {
        return toAjax(eduWarningService.deleteEduWarningByWarningIds(warningIds));
    }
}
