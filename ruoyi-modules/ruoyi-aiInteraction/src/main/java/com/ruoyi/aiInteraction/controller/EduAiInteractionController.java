package com.ruoyi.aiInteraction.controller;

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
import com.ruoyi.aiInteraction.domain.EduAiInteraction;
import com.ruoyi.aiInteraction.service.IEduAiInteractionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 智能交互记录Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/aiInteraction_interaction")
public class EduAiInteractionController extends BaseController
{
    @Autowired
    private IEduAiInteractionService eduAiInteractionService;

    /**
     * 查询智能交互记录列表
     */
    @RequiresPermissions("aiInteraction:aiInteraction_interaction:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAiInteraction eduAiInteraction)
    {
        startPage();
        List<EduAiInteraction> list = eduAiInteractionService.selectEduAiInteractionList(eduAiInteraction);
        return getDataTable(list);
    }

    /**
     * 导出智能交互记录列表
     */
    @RequiresPermissions("aiInteraction:aiInteraction_interaction:export")
    @Log(title = "智能交互记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAiInteraction eduAiInteraction)
    {
        List<EduAiInteraction> list = eduAiInteractionService.selectEduAiInteractionList(eduAiInteraction);
        ExcelUtil<EduAiInteraction> util = new ExcelUtil<EduAiInteraction>(EduAiInteraction.class);
        util.exportExcel(response, list, "智能交互记录数据");
    }

    /**
     * 获取智能交互记录详细信息
     */
    @RequiresPermissions("aiInteraction:aiInteraction_interaction:query")
    @GetMapping(value = "/{interactionId}")
    public AjaxResult getInfo(@PathVariable("interactionId") String interactionId)
    {
        return success(eduAiInteractionService.selectEduAiInteractionByInteractionId(interactionId));
    }

    /**
     * 新增智能交互记录
     */
    @RequiresPermissions("aiInteraction:aiInteraction_interaction:add")
    @Log(title = "智能交互记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAiInteraction eduAiInteraction)
    {
        return toAjax(eduAiInteractionService.insertEduAiInteraction(eduAiInteraction));
    }

    /**
     * 修改智能交互记录
     */
    @RequiresPermissions("aiInteraction:aiInteraction_interaction:edit")
    @Log(title = "智能交互记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAiInteraction eduAiInteraction)
    {
        return toAjax(eduAiInteractionService.updateEduAiInteraction(eduAiInteraction));
    }

    /**
     * 删除智能交互记录
     */
    @RequiresPermissions("aiInteraction:aiInteraction_interaction:remove")
    @Log(title = "智能交互记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{interactionIds}")
    public AjaxResult remove(@PathVariable String[] interactionIds)
    {
        return toAjax(eduAiInteractionService.deleteEduAiInteractionByInteractionIds(interactionIds));
    }
}
