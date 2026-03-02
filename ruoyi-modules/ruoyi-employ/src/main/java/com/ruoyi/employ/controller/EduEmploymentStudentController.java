package com.ruoyi.employ.controller;

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
import com.ruoyi.employ.domain.EduEmploymentStudent;
import com.ruoyi.employ.service.IEduEmploymentStudentService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 学生就业信息Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/employ_studentInfo")
public class EduEmploymentStudentController extends BaseController
{
    @Autowired
    private IEduEmploymentStudentService eduEmploymentStudentService;

    /**
     * 查询学生就业信息列表
     */
    @RequiresPermissions("employ:employ_studentInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(EduEmploymentStudent eduEmploymentStudent)
    {
        startPage();
        List<EduEmploymentStudent> list = eduEmploymentStudentService.selectEduEmploymentStudentList(eduEmploymentStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生就业信息列表
     */
    @RequiresPermissions("employ:employ_studentInfo:export")
    @Log(title = "学生就业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduEmploymentStudent eduEmploymentStudent)
    {
        List<EduEmploymentStudent> list = eduEmploymentStudentService.selectEduEmploymentStudentList(eduEmploymentStudent);
        ExcelUtil<EduEmploymentStudent> util = new ExcelUtil<EduEmploymentStudent>(EduEmploymentStudent.class);
        util.exportExcel(response, list, "学生就业信息数据");
    }

    /**
     * 获取学生就业信息详细信息
     */
    @RequiresPermissions("employ:employ_studentInfo:query")
    @GetMapping(value = "/{employmentId}")
    public AjaxResult getInfo(@PathVariable("employmentId") Long employmentId)
    {
        return success(eduEmploymentStudentService.selectEduEmploymentStudentByEmploymentId(employmentId));
    }

    /**
     * 新增学生就业信息
     */
    @RequiresPermissions("employ:employ_studentInfo:add")
    @Log(title = "学生就业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduEmploymentStudent eduEmploymentStudent)
    {
        return toAjax(eduEmploymentStudentService.insertEduEmploymentStudent(eduEmploymentStudent));
    }

    /**
     * 修改学生就业信息
     */
    @RequiresPermissions("employ:employ_studentInfo:edit")
    @Log(title = "学生就业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduEmploymentStudent eduEmploymentStudent)
    {
        return toAjax(eduEmploymentStudentService.updateEduEmploymentStudent(eduEmploymentStudent));
    }

    /**
     * 删除学生就业信息
     */
    @RequiresPermissions("employ:employ_studentInfo:remove")
    @Log(title = "学生就业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employmentIds}")
    public AjaxResult remove(@PathVariable Long[] employmentIds)
    {
        return toAjax(eduEmploymentStudentService.deleteEduEmploymentStudentByEmploymentIds(employmentIds));
    }
}
