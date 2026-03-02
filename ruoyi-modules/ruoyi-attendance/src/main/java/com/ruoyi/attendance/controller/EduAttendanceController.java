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
import com.ruoyi.attendance.domain.EduAttendance;
import com.ruoyi.attendance.service.IEduAttendanceService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 考勤管理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/attendance")
public class EduAttendanceController extends BaseController
{
    @Autowired
    private IEduAttendanceService eduAttendanceService;

    /**
     * 查询考勤管理列表
     */
    @RequiresPermissions("attendance:attendance:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAttendance eduAttendance)
    {
        startPage();
        List<EduAttendance> list = eduAttendanceService.selectEduAttendanceList(eduAttendance);
        return getDataTable(list);
    }

    /**
     * 导出考勤管理列表
     */
    @RequiresPermissions("attendance:attendance:export")
    @Log(title = "考勤管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAttendance eduAttendance)
    {
        List<EduAttendance> list = eduAttendanceService.selectEduAttendanceList(eduAttendance);
        ExcelUtil<EduAttendance> util = new ExcelUtil<EduAttendance>(EduAttendance.class);
        util.exportExcel(response, list, "考勤管理数据");
    }

    /**
     * 获取考勤管理详细信息
     */
    @RequiresPermissions("attendance:attendance:query")
    @GetMapping(value = "/{attendId}")
    public AjaxResult getInfo(@PathVariable("attendId") Long attendId)
    {
        return success(eduAttendanceService.selectEduAttendanceByAttendId(attendId));
    }

    /**
     * 新增考勤管理
     */
    @RequiresPermissions("attendance:attendance:add")
    @Log(title = "考勤管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAttendance eduAttendance)
    {
        return toAjax(eduAttendanceService.insertEduAttendance(eduAttendance));
    }

    /**
     * 修改考勤管理
     */
    @RequiresPermissions("attendance:attendance:edit")
    @Log(title = "考勤管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAttendance eduAttendance)
    {
        return toAjax(eduAttendanceService.updateEduAttendance(eduAttendance));
    }

    /**
     * 删除考勤管理
     */
    @RequiresPermissions("attendance:attendance:remove")
    @Log(title = "考勤管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attendIds}")
    public AjaxResult remove(@PathVariable Long[] attendIds)
    {
        return toAjax(eduAttendanceService.deleteEduAttendanceByAttendIds(attendIds));
    }
}
