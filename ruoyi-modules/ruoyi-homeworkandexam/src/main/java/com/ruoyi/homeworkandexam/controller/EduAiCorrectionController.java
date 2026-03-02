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
import com.ruoyi.homeworkandexam.domain.EduAiCorrection;
import com.ruoyi.homeworkandexam.service.IEduAiCorrectionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * AI 批改记录Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/ai-correction")
public class EduAiCorrectionController extends BaseController
{
    @Autowired
    private IEduAiCorrectionService eduAiCorrectionService;

    /**
     * 查询AI 批改记录列表
     */
    @RequiresPermissions("homeworkandexam:ai-correction:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAiCorrection eduAiCorrection)
    {
        startPage();
        List<EduAiCorrection> list = eduAiCorrectionService.selectEduAiCorrectionList(eduAiCorrection);
        return getDataTable(list);
    }

    /**
     * 导出AI 批改记录列表
     */
    @RequiresPermissions("homeworkandexam:ai-correction:export")
    @Log(title = "AI 批改记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAiCorrection eduAiCorrection)
    {
        List<EduAiCorrection> list = eduAiCorrectionService.selectEduAiCorrectionList(eduAiCorrection);
        ExcelUtil<EduAiCorrection> util = new ExcelUtil<EduAiCorrection>(EduAiCorrection.class);
        util.exportExcel(response, list, "AI 批改记录数据");
    }

    /**
     * 获取AI 批改记录详细信息
     */
    @RequiresPermissions("homeworkandexam:ai-correction:query")
    @GetMapping(value = "/{correctionId}")
    public AjaxResult getInfo(@PathVariable("correctionId") Long correctionId)
    {
        return success(eduAiCorrectionService.selectEduAiCorrectionByCorrectionId(correctionId));
    }

    /**
     * 新增AI 批改记录
     */
    @RequiresPermissions("homeworkandexam:ai-correction:add")
    @Log(title = "AI 批改记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAiCorrection eduAiCorrection)
    {
        return toAjax(eduAiCorrectionService.insertEduAiCorrection(eduAiCorrection));
    }

    /**
     * 修改AI 批改记录
     */
    @RequiresPermissions("homeworkandexam:ai-correction:edit")
    @Log(title = "AI 批改记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAiCorrection eduAiCorrection)
    {
        return toAjax(eduAiCorrectionService.updateEduAiCorrection(eduAiCorrection));
    }

    /**
     * 删除AI 批改记录
     */
    @RequiresPermissions("homeworkandexam:ai-correction:remove")
    @Log(title = "AI 批改记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{correctionIds}")
    public AjaxResult remove(@PathVariable Long[] correctionIds)
    {
        return toAjax(eduAiCorrectionService.deleteEduAiCorrectionByCorrectionIds(correctionIds));
    }
}
