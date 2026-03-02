package com.ruoyi.classSchedule.controller;

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
import com.ruoyi.classSchedule.domain.EduTimetableResult;
import com.ruoyi.classSchedule.service.IEduTimetableResultService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 排课结果Controller
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/classSchedule_result")
public class EduTimetableResultController extends BaseController
{
    @Autowired
    private IEduTimetableResultService eduTimetableResultService;

    /**
     * 查询排课结果列表
     */
    @RequiresPermissions("classSchedule:classSchedule_result:list")
    @GetMapping("/list")
    public TableDataInfo list(EduTimetableResult eduTimetableResult)
    {
        startPage();
        List<EduTimetableResult> list = eduTimetableResultService.selectEduTimetableResultList(eduTimetableResult);
        return getDataTable(list);
    }

    /**
     * 导出排课结果列表
     */
    @RequiresPermissions("classSchedule:classSchedule_result:export")
    @Log(title = "排课结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduTimetableResult eduTimetableResult)
    {
        List<EduTimetableResult> list = eduTimetableResultService.selectEduTimetableResultList(eduTimetableResult);
        ExcelUtil<EduTimetableResult> util = new ExcelUtil<EduTimetableResult>(EduTimetableResult.class);
        util.exportExcel(response, list, "排课结果数据");
    }

    /**
     * 获取排课结果详细信息
     */
    @RequiresPermissions("classSchedule:classSchedule_result:query")
    @GetMapping(value = "/{resultId}")
    public AjaxResult getInfo(@PathVariable("resultId") Long resultId)
    {
        return success(eduTimetableResultService.selectEduTimetableResultByResultId(resultId));
    }

    /**
     * 新增排课结果
     */
    @RequiresPermissions("classSchedule:classSchedule_result:add")
    @Log(title = "排课结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTimetableResult eduTimetableResult)
    {
        return toAjax(eduTimetableResultService.insertEduTimetableResult(eduTimetableResult));
    }

    /**
     * 修改排课结果
     */
    @RequiresPermissions("classSchedule:classSchedule_result:edit")
    @Log(title = "排课结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTimetableResult eduTimetableResult)
    {
        return toAjax(eduTimetableResultService.updateEduTimetableResult(eduTimetableResult));
    }

    /**
     * 删除排课结果
     */
    @RequiresPermissions("classSchedule:classSchedule_result:remove")
    @Log(title = "排课结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resultIds}")
    public AjaxResult remove(@PathVariable Long[] resultIds)
    {
        return toAjax(eduTimetableResultService.deleteEduTimetableResultByResultIds(resultIds));
    }
}
