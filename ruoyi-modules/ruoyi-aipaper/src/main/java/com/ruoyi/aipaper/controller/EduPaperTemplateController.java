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
import com.ruoyi.aipaper.domain.EduPaperTemplate;
import com.ruoyi.aipaper.service.IEduPaperTemplateService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 组卷模板Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/aipaper_template")
public class EduPaperTemplateController extends BaseController
{
    @Autowired
    private IEduPaperTemplateService eduPaperTemplateService;

    /**
     * 查询组卷模板列表
     */
    @RequiresPermissions("aipaper:aipaper_template:list")
    @GetMapping("/list")
    public TableDataInfo list(EduPaperTemplate eduPaperTemplate)
    {
        startPage();
        List<EduPaperTemplate> list = eduPaperTemplateService.selectEduPaperTemplateList(eduPaperTemplate);
        return getDataTable(list);
    }

    /**
     * 导出组卷模板列表
     */
    @RequiresPermissions("aipaper:aipaper_template:export")
    @Log(title = "组卷模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduPaperTemplate eduPaperTemplate)
    {
        List<EduPaperTemplate> list = eduPaperTemplateService.selectEduPaperTemplateList(eduPaperTemplate);
        ExcelUtil<EduPaperTemplate> util = new ExcelUtil<EduPaperTemplate>(EduPaperTemplate.class);
        util.exportExcel(response, list, "组卷模板数据");
    }

    /**
     * 获取组卷模板详细信息
     */
    @RequiresPermissions("aipaper:aipaper_template:query")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") Long templateId)
    {
        return success(eduPaperTemplateService.selectEduPaperTemplateByTemplateId(templateId));
    }

    /**
     * 新增组卷模板
     */
    @RequiresPermissions("aipaper:aipaper_template:add")
    @Log(title = "组卷模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPaperTemplate eduPaperTemplate)
    {
        return toAjax(eduPaperTemplateService.insertEduPaperTemplate(eduPaperTemplate));
    }

    /**
     * 修改组卷模板
     */
    @RequiresPermissions("aipaper:aipaper_template:edit")
    @Log(title = "组卷模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPaperTemplate eduPaperTemplate)
    {
        return toAjax(eduPaperTemplateService.updateEduPaperTemplate(eduPaperTemplate));
    }

    /**
     * 删除组卷模板
     */
    @RequiresPermissions("aipaper:aipaper_template:remove")
    @Log(title = "组卷模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable Long[] templateIds)
    {
        return toAjax(eduPaperTemplateService.deleteEduPaperTemplateByTemplateIds(templateIds));
    }
}
