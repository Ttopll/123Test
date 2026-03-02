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
import com.ruoyi.attendance.domain.EduAttendanceBiometric;
import com.ruoyi.attendance.service.IEduAttendanceBiometricService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 考勤人脸指纹记录Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/attendance-biometric")
public class EduAttendanceBiometricController extends BaseController
{
    @Autowired
    private IEduAttendanceBiometricService eduAttendanceBiometricService;

    /**
     * 查询考勤人脸指纹记录列表
     */
    @RequiresPermissions("attendance:attendance-biometric:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAttendanceBiometric eduAttendanceBiometric)
    {
        startPage();
        List<EduAttendanceBiometric> list = eduAttendanceBiometricService.selectEduAttendanceBiometricList(eduAttendanceBiometric);
        return getDataTable(list);
    }

    /**
     * 导出考勤人脸指纹记录列表
     */
    @RequiresPermissions("attendance:attendance-biometric:export")
    @Log(title = "考勤人脸指纹记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAttendanceBiometric eduAttendanceBiometric)
    {
        List<EduAttendanceBiometric> list = eduAttendanceBiometricService.selectEduAttendanceBiometricList(eduAttendanceBiometric);
        ExcelUtil<EduAttendanceBiometric> util = new ExcelUtil<EduAttendanceBiometric>(EduAttendanceBiometric.class);
        util.exportExcel(response, list, "考勤人脸指纹记录数据");
    }

    /**
     * 获取考勤人脸指纹记录详细信息
     */
    @RequiresPermissions("attendance:attendance-biometric:query")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return success(eduAttendanceBiometricService.selectEduAttendanceBiometricByInfoId(infoId));
    }

    /**
     * 新增考勤人脸指纹记录
     */
    @RequiresPermissions("attendance:attendance-biometric:add")
    @Log(title = "考勤人脸指纹记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAttendanceBiometric eduAttendanceBiometric)
    {
        return toAjax(eduAttendanceBiometricService.insertEduAttendanceBiometric(eduAttendanceBiometric));
    }

    /**
     * 修改考勤人脸指纹记录
     */
    @RequiresPermissions("attendance:attendance-biometric:edit")
    @Log(title = "考勤人脸指纹记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAttendanceBiometric eduAttendanceBiometric)
    {
        return toAjax(eduAttendanceBiometricService.updateEduAttendanceBiometric(eduAttendanceBiometric));
    }

    /**
     * 删除考勤人脸指纹记录
     */
    @RequiresPermissions("attendance:attendance-biometric:remove")
    @Log(title = "考勤人脸指纹记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(eduAttendanceBiometricService.deleteEduAttendanceBiometricByInfoIds(infoIds));
    }
}
