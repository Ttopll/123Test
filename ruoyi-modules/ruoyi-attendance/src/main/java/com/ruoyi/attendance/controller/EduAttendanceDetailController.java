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
import com.ruoyi.attendance.domain.EduAttendanceDetail;
import com.ruoyi.attendance.service.IEduAttendanceDetailService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 考勤详情Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/attendance-detail")
public class EduAttendanceDetailController extends BaseController
{
    @Autowired
    private IEduAttendanceDetailService eduAttendanceDetailService;

    /**
     * 查询考勤详情列表
     */
    @RequiresPermissions("attendance:attendance-detail:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAttendanceDetail eduAttendanceDetail)
    {
        startPage();
        List<EduAttendanceDetail> list = eduAttendanceDetailService.selectEduAttendanceDetailList(eduAttendanceDetail);
        return getDataTable(list);
    }

    /**
     * 导出考勤详情列表
     */
    @RequiresPermissions("attendance:attendance-detail:export")
    @Log(title = "考勤详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAttendanceDetail eduAttendanceDetail)
    {
        List<EduAttendanceDetail> list = eduAttendanceDetailService.selectEduAttendanceDetailList(eduAttendanceDetail);
        ExcelUtil<EduAttendanceDetail> util = new ExcelUtil<EduAttendanceDetail>(EduAttendanceDetail.class);
        util.exportExcel(response, list, "考勤详情数据");
    }

    /**
     * 获取考勤详情详细信息
     */
    @RequiresPermissions("attendance:attendance-detail:query")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return success(eduAttendanceDetailService.selectEduAttendanceDetailByDetailId(detailId));
    }

    /**
     * 新增考勤详情
     */
    @RequiresPermissions("attendance:attendance-detail:add")
    @Log(title = "考勤详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAttendanceDetail eduAttendanceDetail)
    {
        return toAjax(eduAttendanceDetailService.insertEduAttendanceDetail(eduAttendanceDetail));
    }

    /**
     * 修改考勤详情
     */
    @RequiresPermissions("attendance:attendance-detail:edit")
    @Log(title = "考勤详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAttendanceDetail eduAttendanceDetail)
    {
        return toAjax(eduAttendanceDetailService.updateEduAttendanceDetail(eduAttendanceDetail));
    }

    /**
     * 删除考勤详情
     */
    @RequiresPermissions("attendance:attendance-detail:remove")
    @Log(title = "考勤详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long[] detailIds)
    {
        return toAjax(eduAttendanceDetailService.deleteEduAttendanceDetailByDetailIds(detailIds));
    }
}
