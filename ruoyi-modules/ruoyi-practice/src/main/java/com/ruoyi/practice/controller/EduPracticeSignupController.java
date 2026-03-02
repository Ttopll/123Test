package com.ruoyi.practice.controller;

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
import com.ruoyi.practice.domain.EduPracticeSignup;
import com.ruoyi.practice.service.IEduPracticeSignupService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 实践活动报名Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/practice_signup")
public class EduPracticeSignupController extends BaseController
{
    @Autowired
    private IEduPracticeSignupService eduPracticeSignupService;

    /**
     * 查询实践活动报名列表
     */
    @RequiresPermissions("practice:practice_signup:list")
    @GetMapping("/list")
    public TableDataInfo list(EduPracticeSignup eduPracticeSignup)
    {
        startPage();
        List<EduPracticeSignup> list = eduPracticeSignupService.selectEduPracticeSignupList(eduPracticeSignup);
        return getDataTable(list);
    }

    /**
     * 导出实践活动报名列表
     */
    @RequiresPermissions("practice:practice_signup:export")
    @Log(title = "实践活动报名", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduPracticeSignup eduPracticeSignup)
    {
        List<EduPracticeSignup> list = eduPracticeSignupService.selectEduPracticeSignupList(eduPracticeSignup);
        ExcelUtil<EduPracticeSignup> util = new ExcelUtil<EduPracticeSignup>(EduPracticeSignup.class);
        util.exportExcel(response, list, "实践活动报名数据");
    }

    /**
     * 获取实践活动报名详细信息
     */
    @RequiresPermissions("practice:practice_signup:query")
    @GetMapping(value = "/{signupId}")
    public AjaxResult getInfo(@PathVariable("signupId") Long signupId)
    {
        return success(eduPracticeSignupService.selectEduPracticeSignupBySignupId(signupId));
    }

    /**
     * 新增实践活动报名
     */
    @RequiresPermissions("practice:practice_signup:add")
    @Log(title = "实践活动报名", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPracticeSignup eduPracticeSignup)
    {
        return toAjax(eduPracticeSignupService.insertEduPracticeSignup(eduPracticeSignup));
    }

    /**
     * 修改实践活动报名
     */
    @RequiresPermissions("practice:practice_signup:edit")
    @Log(title = "实践活动报名", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPracticeSignup eduPracticeSignup)
    {
        return toAjax(eduPracticeSignupService.updateEduPracticeSignup(eduPracticeSignup));
    }

    /**
     * 删除实践活动报名
     */
    @RequiresPermissions("practice:practice_signup:remove")
    @Log(title = "实践活动报名", businessType = BusinessType.DELETE)
	@DeleteMapping("/{signupIds}")
    public AjaxResult remove(@PathVariable Long[] signupIds)
    {
        return toAjax(eduPracticeSignupService.deleteEduPracticeSignupBySignupIds(signupIds));
    }
}
