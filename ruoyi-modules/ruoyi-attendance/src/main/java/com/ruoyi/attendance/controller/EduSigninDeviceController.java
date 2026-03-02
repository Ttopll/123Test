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
import com.ruoyi.attendance.domain.EduSigninDevice;
import com.ruoyi.attendance.service.IEduSigninDeviceService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 签到设备Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/signin-device")
public class EduSigninDeviceController extends BaseController
{
    @Autowired
    private IEduSigninDeviceService eduSigninDeviceService;

    /**
     * 查询签到设备列表
     */
    @RequiresPermissions("attendance:signin-device:list")
    @GetMapping("/list")
    public TableDataInfo list(EduSigninDevice eduSigninDevice)
    {
        startPage();
        List<EduSigninDevice> list = eduSigninDeviceService.selectEduSigninDeviceList(eduSigninDevice);
        return getDataTable(list);
    }

    /**
     * 导出签到设备列表
     */
    @RequiresPermissions("attendance:signin-device:export")
    @Log(title = "签到设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduSigninDevice eduSigninDevice)
    {
        List<EduSigninDevice> list = eduSigninDeviceService.selectEduSigninDeviceList(eduSigninDevice);
        ExcelUtil<EduSigninDevice> util = new ExcelUtil<EduSigninDevice>(EduSigninDevice.class);
        util.exportExcel(response, list, "签到设备数据");
    }

    /**
     * 获取签到设备详细信息
     */
    @RequiresPermissions("attendance:signin-device:query")
    @GetMapping(value = "/{deviceId}")
    public AjaxResult getInfo(@PathVariable("deviceId") Long deviceId)
    {
        return success(eduSigninDeviceService.selectEduSigninDeviceByDeviceId(deviceId));
    }

    /**
     * 新增签到设备
     */
    @RequiresPermissions("attendance:signin-device:add")
    @Log(title = "签到设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduSigninDevice eduSigninDevice)
    {
        return toAjax(eduSigninDeviceService.insertEduSigninDevice(eduSigninDevice));
    }

    /**
     * 修改签到设备
     */
    @RequiresPermissions("attendance:signin-device:edit")
    @Log(title = "签到设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduSigninDevice eduSigninDevice)
    {
        return toAjax(eduSigninDeviceService.updateEduSigninDevice(eduSigninDevice));
    }

    /**
     * 删除签到设备
     */
    @RequiresPermissions("attendance:signin-device:remove")
    @Log(title = "签到设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceIds}")
    public AjaxResult remove(@PathVariable Long[] deviceIds)
    {
        return toAjax(eduSigninDeviceService.deleteEduSigninDeviceByDeviceIds(deviceIds));
    }
}
