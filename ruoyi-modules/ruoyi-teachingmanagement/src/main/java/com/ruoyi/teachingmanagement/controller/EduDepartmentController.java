package com.ruoyi.teachingmanagement.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.teachingmanagement.domain.EduDepartment;
import com.ruoyi.teachingmanagement.service.IEduDepartmentService;
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
 * 院系管理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/department")
public class EduDepartmentController extends BaseController
{
    @Autowired
    private IEduDepartmentService eduDepartmentService;

    /**
     * 查询院系管理列表
     */
    @RequiresPermissions("system:department:list")
    @GetMapping("/list")
    public TableDataInfo list(EduDepartment eduDepartment)
    {
        startPage();
        List<EduDepartment> list = eduDepartmentService.selectEduDepartmentList(eduDepartment);
        return getDataTable(list);
    }

    /**
     * 导出院系管理列表
     */
    @RequiresPermissions("system:department:export")
    @Log(title = "院系管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduDepartment eduDepartment)
    {
        List<EduDepartment> list = eduDepartmentService.selectEduDepartmentList(eduDepartment);
        ExcelUtil<EduDepartment> util = new ExcelUtil<EduDepartment>(EduDepartment.class);
        util.exportExcel(response, list, "院系管理数据");
    }

    /**
     * 获取院系管理详细信息
     */
    @RequiresPermissions("system:department:query")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return success(eduDepartmentService.selectEduDepartmentByDeptId(deptId));
    }

    /**
     * 新增院系管理
     */
    @RequiresPermissions("system:department:add")
    @Log(title = "院系管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduDepartment eduDepartment)
    {
        return toAjax(eduDepartmentService.insertEduDepartment(eduDepartment));
    }

    /**
     * 修改院系管理
     */
    @RequiresPermissions("system:department:edit")
    @Log(title = "院系管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduDepartment eduDepartment)
    {
        return toAjax(eduDepartmentService.updateEduDepartment(eduDepartment));
    }

    /**
     * 删除院系管理
     */
    @RequiresPermissions("system:department:remove")
    @Log(title = "院系管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(eduDepartmentService.deleteEduDepartmentByDeptIds(deptIds));
    }
}
