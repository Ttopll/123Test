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
import com.ruoyi.practice.domain.EduPracticeActivity;
import com.ruoyi.practice.service.IEduPracticeActivityService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 实践活动Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/practice_activity")
public class EduPracticeActivityController extends BaseController
{
    @Autowired
    private IEduPracticeActivityService eduPracticeActivityService;

    /**
     * 查询实践活动列表
     */
    @RequiresPermissions("practice:practice_activity:list")
    @GetMapping("/list")
    public TableDataInfo list(EduPracticeActivity eduPracticeActivity)
    {
        startPage();
        List<EduPracticeActivity> list = eduPracticeActivityService.selectEduPracticeActivityList(eduPracticeActivity);
        return getDataTable(list);
    }

    /**
     * 导出实践活动列表
     */
    @RequiresPermissions("practice:practice_activity:export")
    @Log(title = "实践活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduPracticeActivity eduPracticeActivity)
    {
        List<EduPracticeActivity> list = eduPracticeActivityService.selectEduPracticeActivityList(eduPracticeActivity);
        ExcelUtil<EduPracticeActivity> util = new ExcelUtil<EduPracticeActivity>(EduPracticeActivity.class);
        util.exportExcel(response, list, "实践活动数据");
    }

    /**
     * 获取实践活动详细信息
     */
    @RequiresPermissions("practice:practice_activity:query")
    @GetMapping(value = "/{activityId}")
    public AjaxResult getInfo(@PathVariable("activityId") String activityId)
    {
        return success(eduPracticeActivityService.selectEduPracticeActivityByActivityId(activityId));
    }

    /**
     * 新增实践活动
     */
    @RequiresPermissions("practice:practice_activity:add")
    @Log(title = "实践活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPracticeActivity eduPracticeActivity)
    {
        return toAjax(eduPracticeActivityService.insertEduPracticeActivity(eduPracticeActivity));
    }

    /**
     * 修改实践活动
     */
    @RequiresPermissions("practice:practice_activity:edit")
    @Log(title = "实践活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPracticeActivity eduPracticeActivity)
    {
        return toAjax(eduPracticeActivityService.updateEduPracticeActivity(eduPracticeActivity));
    }

    /**
     * 删除实践活动
     */
    @RequiresPermissions("practice:practice_activity:remove")
    @Log(title = "实践活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable String[] activityIds)
    {
        return toAjax(eduPracticeActivityService.deleteEduPracticeActivityByActivityIds(activityIds));
    }
}
