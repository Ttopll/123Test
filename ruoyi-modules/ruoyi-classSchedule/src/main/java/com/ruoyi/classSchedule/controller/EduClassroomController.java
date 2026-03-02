package com.ruoyi.classSchedule.controller;

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
import com.ruoyi.classSchedule.domain.EduClassroom;
import com.ruoyi.classSchedule.service.IEduClassroomService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 教室资源Controller
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/classSchedule_classroom")
public class EduClassroomController extends BaseController
{
    @Autowired
    private IEduClassroomService eduClassroomService;

    /**
     * 查询教室资源列表
     */
    @RequiresPermissions("classSchedule:classSchedule_classroom:list")
    @GetMapping("/list")
    public TableDataInfo list(EduClassroom eduClassroom)
    {
        startPage();
        List<EduClassroom> list = eduClassroomService.selectEduClassroomList(eduClassroom);
        return getDataTable(list);
    }

    /**
     * 导出教室资源列表
     */
    @RequiresPermissions("classSchedule:classSchedule_classroom:export")
    @Log(title = "教室资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduClassroom eduClassroom)
    {
        List<EduClassroom> list = eduClassroomService.selectEduClassroomList(eduClassroom);
        ExcelUtil<EduClassroom> util = new ExcelUtil<EduClassroom>(EduClassroom.class);
        util.exportExcel(response, list, "教室资源数据");
    }

    /**
     * 获取教室资源详细信息
     */
    @RequiresPermissions("classSchedule:classSchedule_classroom:query")
    @GetMapping(value = "/{classroomId}")
    public AjaxResult getInfo(@PathVariable("classroomId") Long classroomId)
    {
        return success(eduClassroomService.selectEduClassroomByClassroomId(classroomId));
    }

    /**
     * 新增教室资源
     */
    @RequiresPermissions("classSchedule:classSchedule_classroom:add")
    @Log(title = "教室资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduClassroom eduClassroom)
    {
        return toAjax(eduClassroomService.insertEduClassroom(eduClassroom));
    }

    /**
     * 修改教室资源
     */
    @RequiresPermissions("classSchedule:classSchedule_classroom:edit")
    @Log(title = "教室资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduClassroom eduClassroom)
    {
        return toAjax(eduClassroomService.updateEduClassroom(eduClassroom));
    }

    /**
     * 删除教室资源
     */
    @RequiresPermissions("classSchedule:classSchedule_classroom:remove")
    @Log(title = "教室资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classroomIds}")
    public AjaxResult remove(@PathVariable Long[] classroomIds)
    {
        return toAjax(eduClassroomService.deleteEduClassroomByClassroomIds(classroomIds));
    }
}
