package com.ruoyi.practice.controller;

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
import com.ruoyi.practice.domain.EduPracticeEvaluation;
import com.ruoyi.practice.service.IEduPracticeEvaluationService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 实践活动评价Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/practice_evaluation")
public class EduPracticeEvaluationController extends BaseController
{
    @Autowired
    private IEduPracticeEvaluationService eduPracticeEvaluationService;

    /**
     * 查询实践活动评价列表
     */
    @RequiresPermissions("practice:practice_evaluation:list")
    @GetMapping("/list")
    public TableDataInfo list(EduPracticeEvaluation eduPracticeEvaluation)
    {
        startPage();
        List<EduPracticeEvaluation> list = eduPracticeEvaluationService.selectEduPracticeEvaluationList(eduPracticeEvaluation);
        return getDataTable(list);
    }

    /**
     * 导出实践活动评价列表
     */
    @RequiresPermissions("practice:practice_evaluation:export")
    @Log(title = "实践活动评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduPracticeEvaluation eduPracticeEvaluation)
    {
        List<EduPracticeEvaluation> list = eduPracticeEvaluationService.selectEduPracticeEvaluationList(eduPracticeEvaluation);
        ExcelUtil<EduPracticeEvaluation> util = new ExcelUtil<EduPracticeEvaluation>(EduPracticeEvaluation.class);
        util.exportExcel(response, list, "实践活动评价数据");
    }

    /**
     * 获取实践活动评价详细信息
     */
    @RequiresPermissions("practice:practice_evaluation:query")
    @GetMapping(value = "/{evaluationId}")
    public AjaxResult getInfo(@PathVariable("evaluationId") Long evaluationId)
    {
        return success(eduPracticeEvaluationService.selectEduPracticeEvaluationByEvaluationId(evaluationId));
    }

    /**
     * 新增实践活动评价
     */
    @RequiresPermissions("practice:practice_evaluation:add")
    @Log(title = "实践活动评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPracticeEvaluation eduPracticeEvaluation)
    {
        return toAjax(eduPracticeEvaluationService.insertEduPracticeEvaluation(eduPracticeEvaluation));
    }

    /**
     * 修改实践活动评价
     */
    @RequiresPermissions("practice:practice_evaluation:edit")
    @Log(title = "实践活动评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPracticeEvaluation eduPracticeEvaluation)
    {
        return toAjax(eduPracticeEvaluationService.updateEduPracticeEvaluation(eduPracticeEvaluation));
    }

    /**
     * 删除实践活动评价
     */
    @RequiresPermissions("practice:practice_evaluation:remove")
    @Log(title = "实践活动评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{evaluationIds}")
    public AjaxResult remove(@PathVariable Long[] evaluationIds)
    {
        return toAjax(eduPracticeEvaluationService.deleteEduPracticeEvaluationByEvaluationIds(evaluationIds));
    }
}
