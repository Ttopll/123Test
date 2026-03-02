package com.ruoyi.attendance.controller;

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
import com.ruoyi.attendance.domain.EduAttendanceRule;
import com.ruoyi.attendance.service.IEduAttendanceRuleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 考勤规则Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/attendance-rule")
public class EduAttendanceRuleController extends BaseController
{
    @Autowired
    private IEduAttendanceRuleService eduAttendanceRuleService;

    /**
     * 查询考勤规则列表
     */
    @RequiresPermissions("attendance:attendance-rule:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAttendanceRule eduAttendanceRule)
    {
        startPage();
        List<EduAttendanceRule> list = eduAttendanceRuleService.selectEduAttendanceRuleList(eduAttendanceRule);
        return getDataTable(list);
    }

    /**
     * 导出考勤规则列表
     */
    @RequiresPermissions("attendance:attendance-rule:export")
    @Log(title = "考勤规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAttendanceRule eduAttendanceRule)
    {
        List<EduAttendanceRule> list = eduAttendanceRuleService.selectEduAttendanceRuleList(eduAttendanceRule);
        ExcelUtil<EduAttendanceRule> util = new ExcelUtil<EduAttendanceRule>(EduAttendanceRule.class);
        util.exportExcel(response, list, "考勤规则数据");
    }

    /**
     * 获取考勤规则详细信息
     */
    @RequiresPermissions("attendance:attendance-rule:query")
    @GetMapping(value = "/{ruleId}")
    public AjaxResult getInfo(@PathVariable("ruleId") Long ruleId)
    {
        return success(eduAttendanceRuleService.selectEduAttendanceRuleByRuleId(ruleId));
    }

    /**
     * 新增考勤规则
     */
    @RequiresPermissions("attendance:attendance-rule:add")
    @Log(title = "考勤规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAttendanceRule eduAttendanceRule)
    {
        return toAjax(eduAttendanceRuleService.insertEduAttendanceRule(eduAttendanceRule));
    }

    /**
     * 修改考勤规则
     */
    @RequiresPermissions("attendance:attendance-rule:edit")
    @Log(title = "考勤规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAttendanceRule eduAttendanceRule)
    {
        return toAjax(eduAttendanceRuleService.updateEduAttendanceRule(eduAttendanceRule));
    }

    /**
     * 删除考勤规则
     */
    @RequiresPermissions("attendance:attendance-rule:remove")
    @Log(title = "考勤规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ruleIds}")
    public AjaxResult remove(@PathVariable Long[] ruleIds)
    {
        return toAjax(eduAttendanceRuleService.deleteEduAttendanceRuleByRuleIds(ruleIds));
    }
}
