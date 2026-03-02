package com.ruoyi.teachingmanagement.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.teachingmanagement.domain.EduCourseReplay;
import com.ruoyi.teachingmanagement.service.IEduCourseReplayService;
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

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 课程回放Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/replay")
public class EduCourseReplayController extends BaseController
{
    @Autowired
    private IEduCourseReplayService eduCourseReplayService;

    /**
     * 查询课程回放列表
     */
    @RequiresPermissions("system:replay:list")
    @GetMapping("/list")
    public TableDataInfo list(EduCourseReplay eduCourseReplay)
    {
        startPage();
        List<EduCourseReplay> list = eduCourseReplayService.selectEduCourseReplayList(eduCourseReplay);
        return getDataTable(list);
    }

    /**
     * 导出课程回放列表
     */
    @RequiresPermissions("system:replay:export")
    @Log(title = "课程回放", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCourseReplay eduCourseReplay)
    {
        List<EduCourseReplay> list = eduCourseReplayService.selectEduCourseReplayList(eduCourseReplay);
        ExcelUtil<EduCourseReplay> util = new ExcelUtil<EduCourseReplay>(EduCourseReplay.class);
        util.exportExcel(response, list, "课程回放数据");
    }

    /**
     * 获取课程回放详细信息
     */
    @RequiresPermissions("system:replay:query")
    @GetMapping(value = "/{replayId}")
    public AjaxResult getInfo(@PathVariable("replayId") Long replayId)
    {
        return success(eduCourseReplayService.selectEduCourseReplayByReplayId(replayId));
    }

    /**
     * 新增课程回放
     */
    @RequiresPermissions("system:replay:add")
    @Log(title = "课程回放", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCourseReplay eduCourseReplay)
    {
        return toAjax(eduCourseReplayService.insertEduCourseReplay(eduCourseReplay));
    }

    /**
     * 修改课程回放
     */
    @RequiresPermissions("system:replay:edit")
    @Log(title = "课程回放", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCourseReplay eduCourseReplay)
    {
        return toAjax(eduCourseReplayService.updateEduCourseReplay(eduCourseReplay));
    }

    /**
     * 删除课程回放
     */
    @RequiresPermissions("system:replay:remove")
    @Log(title = "课程回放", businessType = BusinessType.DELETE)
	@DeleteMapping("/{replayIds}")
    public AjaxResult remove(@PathVariable Long[] replayIds)
    {
        return toAjax(eduCourseReplayService.deleteEduCourseReplayByReplayIds(replayIds));
    }
}
