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
import com.ruoyi.employ.domain.EduEmploymentResume;
import com.ruoyi.employ.service.IEduEmploymentResumeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 学生简历管理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/employ_resume")
public class EduEmploymentResumeController extends BaseController
{
    @Autowired
    private IEduEmploymentResumeService eduEmploymentResumeService;

    /**
     * 查询学生简历管理列表
     */
    @RequiresPermissions("employ:employ_resume:list")
    @GetMapping("/list")
    public TableDataInfo list(EduEmploymentResume eduEmploymentResume)
    {
        startPage();
        List<EduEmploymentResume> list = eduEmploymentResumeService.selectEduEmploymentResumeList(eduEmploymentResume);
        return getDataTable(list);
    }

    /**
     * 导出学生简历管理列表
     */
    @RequiresPermissions("employ:employ_resume:export")
    @Log(title = "学生简历管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduEmploymentResume eduEmploymentResume)
    {
        List<EduEmploymentResume> list = eduEmploymentResumeService.selectEduEmploymentResumeList(eduEmploymentResume);
        ExcelUtil<EduEmploymentResume> util = new ExcelUtil<EduEmploymentResume>(EduEmploymentResume.class);
        util.exportExcel(response, list, "学生简历管理数据");
    }

    /**
     * 获取学生简历管理详细信息
     */
    @RequiresPermissions("employ:employ_resume:query")
    @GetMapping(value = "/{resumeId}")
    public AjaxResult getInfo(@PathVariable("resumeId") Long resumeId)
    {
        return success(eduEmploymentResumeService.selectEduEmploymentResumeByResumeId(resumeId));
    }

    /**
     * 新增学生简历管理
     */
    @RequiresPermissions("employ:employ_resume:add")
    @Log(title = "学生简历管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduEmploymentResume eduEmploymentResume)
    {
        return toAjax(eduEmploymentResumeService.insertEduEmploymentResume(eduEmploymentResume));
    }

    /**
     * 修改学生简历管理
     */
    @RequiresPermissions("employ:employ_resume:edit")
    @Log(title = "学生简历管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduEmploymentResume eduEmploymentResume)
    {
        return toAjax(eduEmploymentResumeService.updateEduEmploymentResume(eduEmploymentResume));
    }

    /**
     * 删除学生简历管理
     */
    @RequiresPermissions("employ:employ_resume:remove")
    @Log(title = "学生简历管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resumeIds}")
    public AjaxResult remove(@PathVariable Long[] resumeIds)
    {
        return toAjax(eduEmploymentResumeService.deleteEduEmploymentResumeByResumeIds(resumeIds));
    }
}
