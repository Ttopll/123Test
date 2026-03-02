package com.ruoyi.classSchedule.controller;

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
import com.ruoyi.classSchedule.domain.EduTimetableRule;
import com.ruoyi.classSchedule.service.IEduTimetableRuleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 排课规则Controller
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/classSchedule_rule")
public class EduTimetableRuleController extends BaseController
{
    @Autowired
    private IEduTimetableRuleService eduTimetableRuleService;

    /**
     * 查询排课规则列表
     */
    @RequiresPermissions("classSchedule:classSchedule_rule:list")
    @GetMapping("/list")
    public TableDataInfo list(EduTimetableRule eduTimetableRule)
    {
        startPage();
        List<EduTimetableRule> list = eduTimetableRuleService.selectEduTimetableRuleList(eduTimetableRule);
        return getDataTable(list);
    }

    /**
     * 导出排课规则列表
     */
    @RequiresPermissions("classSchedule:classSchedule_rule:export")
    @Log(title = "排课规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduTimetableRule eduTimetableRule)
    {
        List<EduTimetableRule> list = eduTimetableRuleService.selectEduTimetableRuleList(eduTimetableRule);
        ExcelUtil<EduTimetableRule> util = new ExcelUtil<EduTimetableRule>(EduTimetableRule.class);
        util.exportExcel(response, list, "排课规则数据");
    }

    /**
     * 获取排课规则详细信息
     */
    @RequiresPermissions("classSchedule:classSchedule_rule:query")
    @GetMapping(value = "/{ruleId}")
    public AjaxResult getInfo(@PathVariable("ruleId") Long ruleId)
    {
        return success(eduTimetableRuleService.selectEduTimetableRuleByRuleId(ruleId));
    }

    /**
     * 新增排课规则
     */
    @RequiresPermissions("classSchedule:classSchedule_rule:add")
    @Log(title = "排课规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTimetableRule eduTimetableRule)
    {
        return toAjax(eduTimetableRuleService.insertEduTimetableRule(eduTimetableRule));
    }

    /**
     * 修改排课规则
     */
    @RequiresPermissions("classSchedule:classSchedule_rule:edit")
    @Log(title = "排课规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTimetableRule eduTimetableRule)
    {
        return toAjax(eduTimetableRuleService.updateEduTimetableRule(eduTimetableRule));
    }

    /**
     * 删除排课规则
     */
    @RequiresPermissions("classSchedule:classSchedule_rule:remove")
    @Log(title = "排课规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ruleIds}")
    public AjaxResult remove(@PathVariable Long[] ruleIds)
    {
        return toAjax(eduTimetableRuleService.deleteEduTimetableRuleByRuleIds(ruleIds));
    }
}
