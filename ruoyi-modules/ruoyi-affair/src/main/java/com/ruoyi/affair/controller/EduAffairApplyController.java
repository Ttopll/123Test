package com.ruoyi.affair.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.affair.domain.EduAffairApply;
import com.ruoyi.affair.service.IEduAffairApplyService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 事务申请Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/affair_apply")
public class EduAffairApplyController extends BaseController
{
    @Autowired
    private IEduAffairApplyService eduAffairApplyService;

    /**
     * 查询事务申请列表
     */
    @RequiresPermissions("affair:affair_apply:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAffairApply eduAffairApply)
    {
        startPage();
        List<EduAffairApply> list = eduAffairApplyService.selectEduAffairApplyList(eduAffairApply);
        return getDataTable(list);
    }

    @RequiresPermissions("affair:affair_apply:user_list")
    @GetMapping("/userList")
    public TableDataInfo userList(EduAffairApply eduAffairApply){
        Long userId = SecurityUtils.getUserId();
        Assert.notNull(userId, "登录失效，请重新登录");
        eduAffairApply.setApplyUserId(userId.toString());

        startPage();
        List<EduAffairApply> list = eduAffairApplyService.selectEduAffairApplyList(eduAffairApply);
        return getDataTable(list);
    }

    /**
     * 导出事务申请列表
     */
    @RequiresPermissions("affair:affair_apply:export")
    @Log(title = "事务申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAffairApply eduAffairApply)
    {
        List<EduAffairApply> list = eduAffairApplyService.selectEduAffairApplyList(eduAffairApply);
        ExcelUtil<EduAffairApply> util = new ExcelUtil<EduAffairApply>(EduAffairApply.class);
        util.exportExcel(response, list, "事务申请数据");
    }

    /**
     * 导出用户事务申请列表
     */
    @RequiresPermissions("affair:affair_apply:user_export")
    @Log(title = "事务申请", businessType = BusinessType.EXPORT)
    @PostMapping("/userExport")
    public void userExport(HttpServletResponse response, EduAffairApply eduAffairApply)
    {
        Long userId = SecurityUtils.getUserId();
        Assert.notNull(userId, "登录失效，请重新登录");
        eduAffairApply.setApplyUserId(userId.toString());

        List<EduAffairApply> list = eduAffairApplyService.selectEduAffairApplyList(eduAffairApply);
        ExcelUtil<EduAffairApply> util = new ExcelUtil<EduAffairApply>(EduAffairApply.class);
        util.exportExcel(response, list, "用户事务申请数据");
    }

    /**
     * 获取事务申请详细信息
     */
    @RequiresPermissions("affair:affair_apply:query")
    @GetMapping(value = "/{applyId}")
    public AjaxResult getInfo(@PathVariable("applyId") String applyId)
    {
        return success(eduAffairApplyService.selectEduAffairApplyByApplyId(applyId));
    }

    /**
     * 新增事务申请
     */
    @RequiresPermissions("affair:affair_apply:add")
    @Log(title = "事务申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAffairApply eduAffairApply)
    {
        Long userId = SecurityUtils.getUserId();
        Assert.notNull(userId, "登录失效，请重新登录");

        Assert.notNull(eduAffairApply.getContent(), "事务内容不能为空");
        Assert.notNull(eduAffairApply.getApplyUserId(), "申请人Id不能为空");

        eduAffairApply.setApplyUserId(userId.toString());
        eduAffairApply.setUpdateUserId(userId.toString());

        return toAjax(eduAffairApplyService.insertEduAffairApply(eduAffairApply));
    }

    /**
     * 修改事务申请
     */
    @RequiresPermissions("affair:affair_apply:edit")
    @Log(title = "事务申请", businessType = BusinessType.UPDATE)
    @PutMapping("/user/edit")
    public AjaxResult edit(@RequestBody EduAffairApply eduAffairApply)
    {
        Long userId = SecurityUtils.getUserId();
        Assert.notNull(userId, "登录失效，请重新登录"); // 如果已处理就不能修改

        if (eduAffairApply.getCurrentStatus() != 0) {
           throw new ServiceException("已处理，无法修改");
        }

        Assert.notNull(eduAffairApply.getApplyId(), "申请Id不能为空");
        Assert.notNull(eduAffairApply.getContent(), "事务内容不能为空");

        eduAffairApply.setApplyUserId(userId.toString());
        eduAffairApply.setUpdateUserId(userId.toString());

        return toAjax(eduAffairApplyService.updateEduAffairApply(eduAffairApply));
    }

    /**
     * 删除事务申请
     */
    @RequiresPermissions("affair:affair_apply:remove")
    @Log(title = "事务申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applyIds}")
    public AjaxResult remove(@PathVariable String[] applyIds, @RequestParam String updateUserId)
    {
        return toAjax(eduAffairApplyService.deleteEduAffairApplyByApplyIds(applyIds, updateUserId));
    }
}
