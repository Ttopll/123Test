package com.ruoyi.aipaper.controller;

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
import com.ruoyi.aipaper.domain.EduQuestion;
import com.ruoyi.aipaper.service.IEduQuestionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 试题Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/aipaper_question")
public class EduQuestionController extends BaseController
{
    @Autowired
    private IEduQuestionService eduQuestionService;

    /**
     * 查询试题列表
     */
    @RequiresPermissions("aipaper:aipaper_question:list")
    @GetMapping("/list")
    public TableDataInfo list(EduQuestion eduQuestion)
    {
        startPage();
        List<EduQuestion> list = eduQuestionService.selectEduQuestionList(eduQuestion);
        return getDataTable(list);
    }

    /**
     * 导出试题列表
     */
    @RequiresPermissions("aipaper:aipaper_question:export")
    @Log(title = "试题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduQuestion eduQuestion)
    {
        List<EduQuestion> list = eduQuestionService.selectEduQuestionList(eduQuestion);
        ExcelUtil<EduQuestion> util = new ExcelUtil<EduQuestion>(EduQuestion.class);
        util.exportExcel(response, list, "试题数据");
    }

    /**
     * 获取试题详细信息
     */
    @RequiresPermissions("aipaper:aipaper_question:query")
    @GetMapping(value = "/{questionId}")
    public AjaxResult getInfo(@PathVariable("questionId") Long questionId)
    {
        return success(eduQuestionService.selectEduQuestionByQuestionId(questionId));
    }

    /**
     * 新增试题
     */
    @RequiresPermissions("aipaper:aipaper_question:add")
    @Log(title = "试题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduQuestion eduQuestion)
    {
        return toAjax(eduQuestionService.insertEduQuestion(eduQuestion));
    }

    /**
     * 修改试题
     */
    @RequiresPermissions("aipaper:aipaper_question:edit")
    @Log(title = "试题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduQuestion eduQuestion)
    {
        return toAjax(eduQuestionService.updateEduQuestion(eduQuestion));
    }

    /**
     * 删除试题
     */
    @RequiresPermissions("aipaper:aipaper_question:remove")
    @Log(title = "试题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{questionIds}")
    public AjaxResult remove(@PathVariable Long[] questionIds)
    {
        return toAjax(eduQuestionService.deleteEduQuestionByQuestionIds(questionIds));
    }
}
