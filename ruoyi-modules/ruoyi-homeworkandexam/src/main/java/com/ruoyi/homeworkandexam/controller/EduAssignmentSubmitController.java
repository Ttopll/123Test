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
import com.ruoyi.homeworkandexam.domain.EduAssignmentSubmit;
import com.ruoyi.homeworkandexam.service.IEduAssignmentSubmitService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 作业提交/批改Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/assignment-submit")
public class EduAssignmentSubmitController extends BaseController
{
    @Autowired
    private IEduAssignmentSubmitService eduAssignmentSubmitService;

    /**
     * 查询作业提交/批改列表
     */
    @RequiresPermissions("homeworkandexam:assignment-submit:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAssignmentSubmit eduAssignmentSubmit)
    {
        startPage();
        List<EduAssignmentSubmit> list = eduAssignmentSubmitService.selectEduAssignmentSubmitList(eduAssignmentSubmit);
        return getDataTable(list);
    }

    /**
     * 导出作业提交/批改列表
     */
    @RequiresPermissions("homeworkandexam:assignment-submit:export")
    @Log(title = "作业提交/批改", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAssignmentSubmit eduAssignmentSubmit)
    {
        List<EduAssignmentSubmit> list = eduAssignmentSubmitService.selectEduAssignmentSubmitList(eduAssignmentSubmit);
        ExcelUtil<EduAssignmentSubmit> util = new ExcelUtil<EduAssignmentSubmit>(EduAssignmentSubmit.class);
        util.exportExcel(response, list, "作业提交/批改数据");
    }

    /**
     * 获取作业提交/批改详细信息
     */
    @RequiresPermissions("homeworkandexam:assignment-submit:query")
    @GetMapping(value = "/{submitId}")
    public AjaxResult getInfo(@PathVariable("submitId") Long submitId)
    {
        return success(eduAssignmentSubmitService.selectEduAssignmentSubmitBySubmitId(submitId));
    }

    /**
     * 新增作业提交/批改
     */
    @RequiresPermissions("homeworkandexam:assignment-submit:add")
    @Log(title = "作业提交/批改", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAssignmentSubmit eduAssignmentSubmit)
    {
        return toAjax(eduAssignmentSubmitService.insertEduAssignmentSubmit(eduAssignmentSubmit));
    }

    /**
     * 修改作业提交/批改
     */
    @RequiresPermissions("homeworkandexam:assignment-submit:edit")
    @Log(title = "作业提交/批改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAssignmentSubmit eduAssignmentSubmit)
    {
        return toAjax(eduAssignmentSubmitService.updateEduAssignmentSubmit(eduAssignmentSubmit));
    }

    /**
     * 删除作业提交/批改
     */
    @RequiresPermissions("homeworkandexam:assignment-submit:remove")
    @Log(title = "作业提交/批改", businessType = BusinessType.DELETE)
	@DeleteMapping("/{submitIds}")
    public AjaxResult remove(@PathVariable Long[] submitIds)
    {
        return toAjax(eduAssignmentSubmitService.deleteEduAssignmentSubmitBySubmitIds(submitIds));
    }
}
