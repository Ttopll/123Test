package com.ruoyi.teachingmanagement.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.teachingmanagement.domain.EduMajor;
import com.ruoyi.teachingmanagement.service.IEduMajorService;
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
 * 专业管理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/major")
public class EduMajorController extends BaseController
{
    @Autowired
    private IEduMajorService eduMajorService;

    /**
     * 查询专业管理列表
     */
    @RequiresPermissions("system:major:list")
    @GetMapping("/list")
    public TableDataInfo list(EduMajor eduMajor)
    {
        startPage();
        List<EduMajor> list = eduMajorService.selectEduMajorList(eduMajor);
        return getDataTable(list);
    }

    /**
     * 导出专业管理列表
     */
    @RequiresPermissions("system:major:export")
    @Log(title = "专业管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduMajor eduMajor)
    {
        List<EduMajor> list = eduMajorService.selectEduMajorList(eduMajor);
        ExcelUtil<EduMajor> util = new ExcelUtil<EduMajor>(EduMajor.class);
        util.exportExcel(response, list, "专业管理数据");
    }

    /**
     * 获取专业管理详细信息
     */
    @RequiresPermissions("system:major:query")
    @GetMapping(value = "/{majorId}")
    public AjaxResult getInfo(@PathVariable("majorId") Long majorId)
    {
        return success(eduMajorService.selectEduMajorByMajorId(majorId));
    }

    /**
     * 新增专业管理
     */
    @RequiresPermissions("system:major:add")
    @Log(title = "专业管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduMajor eduMajor)
    {
        return toAjax(eduMajorService.insertEduMajor(eduMajor));
    }

    /**
     * 修改专业管理
     */
    @RequiresPermissions("system:major:edit")
    @Log(title = "专业管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduMajor eduMajor)
    {
        return toAjax(eduMajorService.updateEduMajor(eduMajor));
    }

    /**
     * 删除专业管理
     */
    @RequiresPermissions("system:major:remove")
    @Log(title = "专业管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{majorIds}")
    public AjaxResult remove(@PathVariable Long[] majorIds)
    {
        return toAjax(eduMajorService.deleteEduMajorByMajorIds(majorIds));
    }
}
