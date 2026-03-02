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
import com.ruoyi.practice.domain.EduPracticeProcess;
import com.ruoyi.practice.service.IEduPracticeProcessService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 实践活动过程Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/practice_process")
public class EduPracticeProcessController extends BaseController
{
    @Autowired
    private IEduPracticeProcessService eduPracticeProcessService;

    /**
     * 查询实践活动过程列表
     */
    @RequiresPermissions("practice:practice_process:list")
    @GetMapping("/list")
    public TableDataInfo list(EduPracticeProcess eduPracticeProcess)
    {
        startPage();
        List<EduPracticeProcess> list = eduPracticeProcessService.selectEduPracticeProcessList(eduPracticeProcess);
        return getDataTable(list);
    }

    /**
     * 导出实践活动过程列表
     */
    @RequiresPermissions("practice:practice_process:export")
    @Log(title = "实践活动过程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduPracticeProcess eduPracticeProcess)
    {
        List<EduPracticeProcess> list = eduPracticeProcessService.selectEduPracticeProcessList(eduPracticeProcess);
        ExcelUtil<EduPracticeProcess> util = new ExcelUtil<EduPracticeProcess>(EduPracticeProcess.class);
        util.exportExcel(response, list, "实践活动过程数据");
    }

    /**
     * 获取实践活动过程详细信息
     */
    @RequiresPermissions("practice:practice_process:query")
    @GetMapping(value = "/{processId}")
    public AjaxResult getInfo(@PathVariable("processId") Long processId)
    {
        return success(eduPracticeProcessService.selectEduPracticeProcessByProcessId(processId));
    }

    /**
     * 新增实践活动过程
     */
    @RequiresPermissions("practice:practice_process:add")
    @Log(title = "实践活动过程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPracticeProcess eduPracticeProcess)
    {
        return toAjax(eduPracticeProcessService.insertEduPracticeProcess(eduPracticeProcess));
    }

    /**
     * 修改实践活动过程
     */
    @RequiresPermissions("practice:practice_process:edit")
    @Log(title = "实践活动过程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPracticeProcess eduPracticeProcess)
    {
        return toAjax(eduPracticeProcessService.updateEduPracticeProcess(eduPracticeProcess));
    }

    /**
     * 删除实践活动过程
     */
    @RequiresPermissions("practice:practice_process:remove")
    @Log(title = "实践活动过程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{processIds}")
    public AjaxResult remove(@PathVariable Long[] processIds)
    {
        return toAjax(eduPracticeProcessService.deleteEduPracticeProcessByProcessIds(processIds));
    }
}
