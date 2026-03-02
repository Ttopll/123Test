package com.ruoyi.affair.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.affair.domain.EduAffairUserCurrentStatus;
import com.ruoyi.affair.service.IEduAffairUserCurrentStatusService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 用户当前状态记录（全局可查）Controller
 * 
 * @author ruoyi
 * @date 2026-01-28
 */
@RestController
@RequestMapping("/affair_current_status")
public class EduAffairUserCurrentStatusController extends BaseController
{
    @Autowired
    private IEduAffairUserCurrentStatusService eduAffairUserCurrentStatusService;

    /**
     * 查询用户当前状态记录（全局可查）列表
     */
    @RequiresPermissions("affair:affair_current_status:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAffairUserCurrentStatus eduAffairUserCurrentStatus)
    {
        startPage();
        List<EduAffairUserCurrentStatus> list = eduAffairUserCurrentStatusService.selectEduAffairUserCurrentStatusList(eduAffairUserCurrentStatus);
        return getDataTable(list);
    }

    /**
     * 导出用户当前状态记录（全局可查）列表
     */
    @RequiresPermissions("affair:affair_current_status:export")
    @Log(title = "用户当前状态记录（全局可查）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAffairUserCurrentStatus eduAffairUserCurrentStatus)
    {
        List<EduAffairUserCurrentStatus> list = eduAffairUserCurrentStatusService.selectEduAffairUserCurrentStatusList(eduAffairUserCurrentStatus);
        ExcelUtil<EduAffairUserCurrentStatus> util = new ExcelUtil<EduAffairUserCurrentStatus>(EduAffairUserCurrentStatus.class);
        util.exportExcel(response, list, "用户当前状态记录（全局可查）数据");
    }

    /**
     * 获取用户当前状态记录（全局可查）详细信息
     */
    @RequiresPermissions("affair:affair_current_status:query")
    @GetMapping(value = "/{currentStatusId}")
    public AjaxResult getInfo(@PathVariable("currentStatusId") Long currentStatusId)
    {
        return success(eduAffairUserCurrentStatusService.selectEduAffairUserCurrentStatusByCurrentStatusId(currentStatusId));
    }

    @RequiresPermissions("affair.affair_current_status:query")
    @GetMapping
    public AjaxResult getInfoByUserId(@RequestParam Long userId){
        return success(eduAffairUserCurrentStatusService.selectEduAffairUserCurrentStatusByUserId(userId));
    }

    /**
     * 新增用户当前状态记录（全局可查）
     */
    @RequiresPermissions("affair:affair_current_status:add")
    @Log(title = "用户当前状态记录（全局可查）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAffairUserCurrentStatus eduAffairUserCurrentStatus)
    {
        return toAjax(eduAffairUserCurrentStatusService.insertEduAffairUserCurrentStatus(eduAffairUserCurrentStatus));
    }

    /**
     * 修改用户当前状态记录（全局可查）
     */
    @RequiresPermissions("affair:affair_current_status:edit")
    @Log(title = "用户当前状态记录（全局可查）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAffairUserCurrentStatus eduAffairUserCurrentStatus)
    {
        return toAjax(eduAffairUserCurrentStatusService.updateEduAffairUserCurrentStatus(eduAffairUserCurrentStatus));
    }

    /**
     * 删除用户当前状态记录（全局可查）
     */
    @RequiresPermissions("affair:affair_current_status:remove")
    @Log(title = "用户当前状态记录（全局可查）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{currentStatusIds}")
    public AjaxResult remove(@PathVariable Long[] currentStatusIds)
    {
        return toAjax(eduAffairUserCurrentStatusService.deleteEduAffairUserCurrentStatusByCurrentStatusIds(currentStatusIds));
    }
}
