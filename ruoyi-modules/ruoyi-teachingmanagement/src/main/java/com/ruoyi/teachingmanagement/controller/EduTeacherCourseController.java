package com.ruoyi.teachingmanagement.controller;

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
import com.ruoyi.teachingmanagement.domain.EduTeacherCourse;
import com.ruoyi.teachingmanagement.service.IEduTeacherCourseService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 教师课程关联Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/teacher-course")
public class EduTeacherCourseController extends BaseController
{
    @Autowired
    private IEduTeacherCourseService eduTeacherCourseService;

    /**
     * 查询教师课程关联列表
     */
    @RequiresPermissions("teachingmanagement:teacher-course:list")
    @GetMapping("/list")
    public TableDataInfo list(EduTeacherCourse eduTeacherCourse)
    {
        startPage();
        List<EduTeacherCourse> list = eduTeacherCourseService.selectEduTeacherCourseList(eduTeacherCourse);
        return getDataTable(list);
    }

    /**
     * 导出教师课程关联列表
     */
    @RequiresPermissions("teachingmanagement:teacher-course:export")
    @Log(title = "教师课程关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduTeacherCourse eduTeacherCourse)
    {
        List<EduTeacherCourse> list = eduTeacherCourseService.selectEduTeacherCourseList(eduTeacherCourse);
        ExcelUtil<EduTeacherCourse> util = new ExcelUtil<EduTeacherCourse>(EduTeacherCourse.class);
        util.exportExcel(response, list, "教师课程关联数据");
    }

    /**
     * 获取教师课程关联详细信息
     */
    @RequiresPermissions("teachingmanagement:teacher-course:query")
    @GetMapping(value = "/{tcId}")
    public AjaxResult getInfo(@PathVariable("tcId") Long tcId)
    {
        return success(eduTeacherCourseService.selectEduTeacherCourseByTcId(tcId));
    }

    /**
     * 新增教师课程关联
     */
    @RequiresPermissions("teachingmanagement:teacher-course:add")
    @Log(title = "教师课程关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTeacherCourse eduTeacherCourse)
    {
        return toAjax(eduTeacherCourseService.insertEduTeacherCourse(eduTeacherCourse));
    }

    /**
     * 修改教师课程关联
     */
    @RequiresPermissions("teachingmanagement:teacher-course:edit")
    @Log(title = "教师课程关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTeacherCourse eduTeacherCourse)
    {
        return toAjax(eduTeacherCourseService.updateEduTeacherCourse(eduTeacherCourse));
    }

    /**
     * 删除教师课程关联
     */
    @RequiresPermissions("teachingmanagement:teacher-course:remove")
    @Log(title = "教师课程关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tcIds}")
    public AjaxResult remove(@PathVariable Long[] tcIds)
    {
        return toAjax(eduTeacherCourseService.deleteEduTeacherCourseByTcIds(tcIds));
    }
}
