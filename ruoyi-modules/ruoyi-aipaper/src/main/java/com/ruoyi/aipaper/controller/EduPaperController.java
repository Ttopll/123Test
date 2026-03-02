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
import com.ruoyi.aipaper.domain.EduPaper;
import com.ruoyi.aipaper.service.IEduPaperService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 试卷Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/aipaper_paper")
public class EduPaperController extends BaseController
{
    @Autowired
    private IEduPaperService eduPaperService;

    /**
     * 查询试卷列表
     */
    @RequiresPermissions("aipaper:aipaper_paper:list")
    @GetMapping("/list")
    public TableDataInfo list(EduPaper eduPaper)
    {
        startPage();
        List<EduPaper> list = eduPaperService.selectEduPaperList(eduPaper);
        return getDataTable(list);
    }

    /**
     * 导出试卷列表
     */
    @RequiresPermissions("aipaper:aipaper_paper:export")
    @Log(title = "试卷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduPaper eduPaper)
    {
        List<EduPaper> list = eduPaperService.selectEduPaperList(eduPaper);
        ExcelUtil<EduPaper> util = new ExcelUtil<EduPaper>(EduPaper.class);
        util.exportExcel(response, list, "试卷数据");
    }

    /**
     * 获取试卷详细信息
     */
    @RequiresPermissions("aipaper:aipaper_paper:query")
    @GetMapping(value = "/{paperId}")
    public AjaxResult getInfo(@PathVariable("paperId") Long paperId)
    {
        return success(eduPaperService.selectEduPaperByPaperId(paperId));
    }

    /**
     * 新增试卷
     */
    @RequiresPermissions("aipaper:aipaper_paper:add")
    @Log(title = "试卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPaper eduPaper)
    {
        return toAjax(eduPaperService.insertEduPaper(eduPaper));
    }

    /**
     * 修改试卷
     */
    @RequiresPermissions("aipaper:aipaper_paper:edit")
    @Log(title = "试卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPaper eduPaper)
    {
        return toAjax(eduPaperService.updateEduPaper(eduPaper));
    }

    /**
     * 删除试卷
     */
    @RequiresPermissions("aipaper:aipaper_paper:remove")
    @Log(title = "试卷", businessType = BusinessType.DELETE)
	@DeleteMapping("/{paperIds}")
    public AjaxResult remove(@PathVariable Long[] paperIds)
    {
        return toAjax(eduPaperService.deleteEduPaperByPaperIds(paperIds));
    }
}
