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
import com.ruoyi.teachingmanagement.domain.EduStudentCourse;
import com.ruoyi.teachingmanagement.service.IEduStudentCourseService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 学生课程关联Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/student-course")
public class EduStudentCourseController extends BaseController
{
    @Autowired
    private IEduStudentCourseService eduStudentCourseService;

    /**
     * 查询学生课程关联列表
     */
    @RequiresPermissions("teachingmanagement:student-course:list")
    @GetMapping("/list")
    public TableDataInfo list(EduStudentCourse eduStudentCourse)
    {
        startPage();
        List<EduStudentCourse> list = eduStudentCourseService.selectEduStudentCourseList(eduStudentCourse);
        return getDataTable(list);
    }

    /**
     * 导出学生课程关联列表
     */
    @RequiresPermissions("teachingmanagement:student-course:export")
    @Log(title = "学生课程关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduStudentCourse eduStudentCourse)
    {
        List<EduStudentCourse> list = eduStudentCourseService.selectEduStudentCourseList(eduStudentCourse);
        ExcelUtil<EduStudentCourse> util = new ExcelUtil<EduStudentCourse>(EduStudentCourse.class);
        util.exportExcel(response, list, "学生课程关联数据");
    }

    /**
     * 获取学生课程关联详细信息
     */
    @RequiresPermissions("teachingmanagement:student-course:query")
    @GetMapping(value = "/{scId}")
    public AjaxResult getInfo(@PathVariable("scId") Long scId)
    {
        return success(eduStudentCourseService.selectEduStudentCourseByScId(scId));
    }

    /**
     * 新增学生课程关联
     */
    @RequiresPermissions("teachingmanagement:student-course:add")
    @Log(title = "学生课程关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduStudentCourse eduStudentCourse)
    {
        return toAjax(eduStudentCourseService.insertEduStudentCourse(eduStudentCourse));
    }

    /**
     * 修改学生课程关联
     */
    @RequiresPermissions("teachingmanagement:student-course:edit")
    @Log(title = "学生课程关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduStudentCourse eduStudentCourse)
    {
        return toAjax(eduStudentCourseService.updateEduStudentCourse(eduStudentCourse));
    }

    /**
     * 删除学生课程关联
     */
    @RequiresPermissions("teachingmanagement:student-course:remove")
    @Log(title = "学生课程关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scIds}")
    public AjaxResult remove(@PathVariable Long[] scIds)
    {
        return toAjax(eduStudentCourseService.deleteEduStudentCourseByScIds(scIds));
    }
}
