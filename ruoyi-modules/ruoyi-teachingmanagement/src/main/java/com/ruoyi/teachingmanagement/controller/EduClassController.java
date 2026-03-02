package com.ruoyi.teachingmanagement.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.teachingmanagement.domain.EduClass;
import com.ruoyi.teachingmanagement.service.IEduClassService;
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
 * 班级管理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/class")
public class EduClassController extends BaseController
{
    @Autowired
    private IEduClassService eduClassService;

    /**
     * 查询班级管理列表
     */
    @RequiresPermissions("system:class:list")
    @GetMapping("/list")
    public TableDataInfo list(EduClass eduClass)
    {
        startPage();
        List<EduClass> list = eduClassService.selectEduClassList(eduClass);
        return getDataTable(list);
    }

    /**
     * 导出班级管理列表
     */
    @RequiresPermissions("system:class:export")
    @Log(title = "班级管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduClass eduClass)
    {
        List<EduClass> list = eduClassService.selectEduClassList(eduClass);
        ExcelUtil<EduClass> util = new ExcelUtil<EduClass>(EduClass.class);
        util.exportExcel(response, list, "班级管理数据");
    }

    /**
     * 获取班级管理详细信息
     */
    @RequiresPermissions("system:class:query")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId)
    {
        return success(eduClassService.selectEduClassByClassId(classId));
    }

    /**
     * 新增班级管理
     */
    @RequiresPermissions("system:class:add")
    @Log(title = "班级管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduClass eduClass)
    {
        return toAjax(eduClassService.insertEduClass(eduClass));
    }

    /**
     * 修改班级管理
     */
    @RequiresPermissions("system:class:edit")
    @Log(title = "班级管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduClass eduClass)
    {
        return toAjax(eduClassService.updateEduClass(eduClass));
    }

    /**
     * 删除班级管理
     */
    @RequiresPermissions("system:class:remove")
    @Log(title = "班级管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classIds}")
    public AjaxResult remove(@PathVariable Long[] classIds)
    {
        return toAjax(eduClassService.deleteEduClassByClassIds(classIds));
    }
}
