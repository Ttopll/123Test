package com.ruoyi.homeworkandexam.controller;

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
import com.ruoyi.homeworkandexam.domain.EduAssignment;
import com.ruoyi.homeworkandexam.service.IEduAssignmentService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 作业管理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/assignment")
public class EduAssignmentController extends BaseController
{
    @Autowired
    private IEduAssignmentService eduAssignmentService;

    /**
     * 查询作业管理列表
     */
    @RequiresPermissions("homeworkandexam:assignment:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAssignment eduAssignment)
    {
        startPage();
        List<EduAssignment> list = eduAssignmentService.selectEduAssignmentList(eduAssignment);
        return getDataTable(list);
    }

    /**
     * 导出作业管理列表
     */
    @RequiresPermissions("homeworkandexam:assignment:export")
    @Log(title = "作业管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAssignment eduAssignment)
    {
        List<EduAssignment> list = eduAssignmentService.selectEduAssignmentList(eduAssignment);
        ExcelUtil<EduAssignment> util = new ExcelUtil<EduAssignment>(EduAssignment.class);
        util.exportExcel(response, list, "作业管理数据");
    }

    /**
     * 获取作业管理详细信息
     */
    @RequiresPermissions("homeworkandexam:assignment:query")
    @GetMapping(value = "/{assignId}")
    public AjaxResult getInfo(@PathVariable("assignId") Long assignId)
    {
        return success(eduAssignmentService.selectEduAssignmentByAssignId(assignId));
    }

    /**
     * 新增作业管理
     */
    @RequiresPermissions("homeworkandexam:assignment:add")
    @Log(title = "作业管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAssignment eduAssignment)
    {
        return toAjax(eduAssignmentService.insertEduAssignment(eduAssignment));
    }

    /**
     * 修改作业管理
     */
    @RequiresPermissions("homeworkandexam:assignment:edit")
    @Log(title = "作业管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAssignment eduAssignment)
    {
        return toAjax(eduAssignmentService.updateEduAssignment(eduAssignment));
    }

    /**
     * 删除作业管理
     */
    @RequiresPermissions("homeworkandexam:assignment:remove")
    @Log(title = "作业管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{assignIds}")
    public AjaxResult remove(@PathVariable Long[] assignIds)
    {
        return toAjax(eduAssignmentService.deleteEduAssignmentByAssignIds(assignIds));
    }
}
