package com.ruoyi.teachingmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ruoyi.teachingmanagement.domain.EduCourse;
import com.ruoyi.teachingmanagement.service.IEduCourseService;
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

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 课程管理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/course")
public class EduCourseController extends BaseController
{
    @Autowired
    private IEduCourseService eduCourseService;

    /**
     * 查询课程管理列表
     */
    @RequiresPermissions("system:course:list")
    @GetMapping("/list")
    public TableDataInfo list(EduCourse eduCourse)
    {
        startPage();
        List<EduCourse> list = eduCourseService.selectEduCourseList(eduCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程管理列表
     */
    @RequiresPermissions("system:course:export")
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCourse eduCourse)
    {
        List<EduCourse> list = eduCourseService.selectEduCourseList(eduCourse);
        ExcelUtil<EduCourse> util = new ExcelUtil<EduCourse>(EduCourse.class);
        util.exportExcel(response, list, "课程管理数据");
    }

    /**
     * 获取课程管理详细信息
     */
    @RequiresPermissions("system:course:query")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Long courseId)
    {
        return success(eduCourseService.selectEduCourseByCourseId(courseId));
    }

    /**
     * 新增课程管理
     */
    @RequiresPermissions("system:course:add")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCourse eduCourse)
    {
        return toAjax(eduCourseService.insertEduCourse(eduCourse));
    }

    /**
     * 修改课程管理
     */
    @RequiresPermissions("system:course:edit")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCourse eduCourse)
    {
        return toAjax(eduCourseService.updateEduCourse(eduCourse));
    }

    /**
     * 删除课程管理
     */
    @RequiresPermissions("system:course:remove")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Long[] courseIds)
    {
        return toAjax(eduCourseService.deleteEduCourseByCourseIds(courseIds));
    }
}
