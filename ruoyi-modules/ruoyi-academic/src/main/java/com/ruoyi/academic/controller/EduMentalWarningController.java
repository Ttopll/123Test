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
import com.ruoyi.academic.domain.EduMentalWarning;
import com.ruoyi.academic.service.IEduMentalWarningService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 心理健康预警Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/mental-warning")
public class EduMentalWarningController extends BaseController
{
    @Autowired
    private IEduMentalWarningService eduMentalWarningService;

    /**
     * 查询心理健康预警列表
     */
    @RequiresPermissions("academic:mental-warning:list")
    @GetMapping("/list")
    public TableDataInfo list(EduMentalWarning eduMentalWarning)
    {
        startPage();
        List<EduMentalWarning> list = eduMentalWarningService.selectEduMentalWarningList(eduMentalWarning);
        return getDataTable(list);
    }

    /**
     * 导出心理健康预警列表
     */
    @RequiresPermissions("academic:mental-warning:export")
    @Log(title = "心理健康预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduMentalWarning eduMentalWarning)
    {
        List<EduMentalWarning> list = eduMentalWarningService.selectEduMentalWarningList(eduMentalWarning);
        ExcelUtil<EduMentalWarning> util = new ExcelUtil<EduMentalWarning>(EduMentalWarning.class);
        util.exportExcel(response, list, "心理健康预警数据");
    }

    /**
     * 获取心理健康预警详细信息
     */
    @RequiresPermissions("academic:mental-warning:query")
    @GetMapping(value = "/{warningId}")
    public AjaxResult getInfo(@PathVariable("warningId") Long warningId)
    {
        return success(eduMentalWarningService.selectEduMentalWarningByWarningId(warningId));
    }

    /**
     * 新增心理健康预警
     */
    @RequiresPermissions("academic:mental-warning:add")
    @Log(title = "心理健康预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduMentalWarning eduMentalWarning)
    {
        return toAjax(eduMentalWarningService.insertEduMentalWarning(eduMentalWarning));
    }

    /**
     * 修改心理健康预警
     */
    @RequiresPermissions("academic:mental-warning:edit")
    @Log(title = "心理健康预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduMentalWarning eduMentalWarning)
    {
        return toAjax(eduMentalWarningService.updateEduMentalWarning(eduMentalWarning));
    }

    /**
     * 删除心理健康预警
     */
    @RequiresPermissions("academic:mental-warning:remove")
    @Log(title = "心理健康预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{warningIds}")
    public AjaxResult remove(@PathVariable Long[] warningIds)
    {
        return toAjax(eduMentalWarningService.deleteEduMentalWarningByWarningIds(warningIds));
    }
}
