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
import com.ruoyi.classSchedule.domain.EduTimetableTask;
import com.ruoyi.classSchedule.service.IEduTimetableTaskService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 排课任务Controller
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/classSchedule_task")
public class EduTimetableTaskController extends BaseController
{
    @Autowired
    private IEduTimetableTaskService eduTimetableTaskService;

    /**
     * 查询排课任务列表
     */
    @RequiresPermissions("classSchedule:classSchedule_task:list")
    @GetMapping("/list")
    public TableDataInfo list(EduTimetableTask eduTimetableTask)
    {
        startPage();
        List<EduTimetableTask> list = eduTimetableTaskService.selectEduTimetableTaskList(eduTimetableTask);
        return getDataTable(list);
    }

    /**
     * 导出排课任务列表
     */
    @RequiresPermissions("classSchedule:classSchedule_task:export")
    @Log(title = "排课任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduTimetableTask eduTimetableTask)
    {
        List<EduTimetableTask> list = eduTimetableTaskService.selectEduTimetableTaskList(eduTimetableTask);
        ExcelUtil<EduTimetableTask> util = new ExcelUtil<EduTimetableTask>(EduTimetableTask.class);
        util.exportExcel(response, list, "排课任务数据");
    }

    /**
     * 获取排课任务详细信息
     */
    @RequiresPermissions("classSchedule:classSchedule_task:query")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(eduTimetableTaskService.selectEduTimetableTaskByTaskId(taskId));
    }

    /**
     * 新增排课任务
     */
    @RequiresPermissions("classSchedule:classSchedule_task:add")
    @Log(title = "排课任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTimetableTask eduTimetableTask)
    {
        return toAjax(eduTimetableTaskService.insertEduTimetableTask(eduTimetableTask));
    }

    /**
     * 修改排课任务
     */
    @RequiresPermissions("classSchedule:classSchedule_task:edit")
    @Log(title = "排课任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTimetableTask eduTimetableTask)
    {
        return toAjax(eduTimetableTaskService.updateEduTimetableTask(eduTimetableTask));
    }

    /**
     * 删除排课任务
     */
    @RequiresPermissions("classSchedule:classSchedule_task:remove")
    @Log(title = "排课任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(eduTimetableTaskService.deleteEduTimetableTaskByTaskIds(taskIds));
    }
}
