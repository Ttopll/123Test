package com.ruoyi.academic.controller;

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
import com.ruoyi.academic.domain.EduWarningHandle;
import com.ruoyi.academic.service.IEduWarningHandleService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 预警处理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/warning-handle")
public class EduWarningHandleController extends BaseController
{
    @Autowired
    private IEduWarningHandleService eduWarningHandleService;

    /**
     * 查询预警处理列表
     */
    @RequiresPermissions("academic:warning-handle:list")
    @GetMapping("/list")
    public TableDataInfo list(EduWarningHandle eduWarningHandle)
    {
        startPage();
        List<EduWarningHandle> list = eduWarningHandleService.selectEduWarningHandleList(eduWarningHandle);
        return getDataTable(list);
    }

    /**
     * 导出预警处理列表
     */
    @RequiresPermissions("academic:warning-handle:export")
    @Log(title = "预警处理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduWarningHandle eduWarningHandle)
    {
        List<EduWarningHandle> list = eduWarningHandleService.selectEduWarningHandleList(eduWarningHandle);
        ExcelUtil<EduWarningHandle> util = new ExcelUtil<EduWarningHandle>(EduWarningHandle.class);
        util.exportExcel(response, list, "预警处理数据");
    }

    /**
     * 获取预警处理详细信息
     */
    @RequiresPermissions("academic:warning-handle:query")
    @GetMapping(value = "/{handleId}")
    public AjaxResult getInfo(@PathVariable("handleId") Long handleId)
    {
        return success(eduWarningHandleService.selectEduWarningHandleByHandleId(handleId));
    }

    /**
     * 新增预警处理
     */
    @RequiresPermissions("academic:warning-handle:add")
    @Log(title = "预警处理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduWarningHandle eduWarningHandle)
    {
        return toAjax(eduWarningHandleService.insertEduWarningHandle(eduWarningHandle));
    }

    /**
     * 修改预警处理
     */
    @RequiresPermissions("academic:warning-handle:edit")
    @Log(title = "预警处理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduWarningHandle eduWarningHandle)
    {
        return toAjax(eduWarningHandleService.updateEduWarningHandle(eduWarningHandle));
    }

    /**
     * 删除预警处理
     */
    @RequiresPermissions("academic:warning-handle:remove")
    @Log(title = "预警处理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{handleIds}")
    public AjaxResult remove(@PathVariable Long[] handleIds)
    {
        return toAjax(eduWarningHandleService.deleteEduWarningHandleByHandleIds(handleIds));
    }
}
