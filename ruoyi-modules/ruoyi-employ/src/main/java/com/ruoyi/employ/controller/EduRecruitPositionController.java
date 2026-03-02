package com.ruoyi.employ.controller;

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
import com.ruoyi.employ.domain.EduRecruitPosition;
import com.ruoyi.employ.service.IEduRecruitPositionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 招聘岗位Controller
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/employ_position")
public class EduRecruitPositionController extends BaseController
{
    @Autowired
    private IEduRecruitPositionService eduRecruitPositionService;

    /**
     * 查询招聘岗位列表
     */
    @RequiresPermissions("employ:employ_position:list")
    @GetMapping("/list")
    public TableDataInfo list(EduRecruitPosition eduRecruitPosition)
    {
        startPage();
        List<EduRecruitPosition> list = eduRecruitPositionService.selectEduRecruitPositionList(eduRecruitPosition);
        return getDataTable(list);
    }

    /**
     * 导出招聘岗位列表
     */
    @RequiresPermissions("employ:employ_position:export")
    @Log(title = "招聘岗位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduRecruitPosition eduRecruitPosition)
    {
        List<EduRecruitPosition> list = eduRecruitPositionService.selectEduRecruitPositionList(eduRecruitPosition);
        ExcelUtil<EduRecruitPosition> util = new ExcelUtil<EduRecruitPosition>(EduRecruitPosition.class);
        util.exportExcel(response, list, "招聘岗位数据");
    }

    /**
     * 获取招聘岗位详细信息
     */
    @RequiresPermissions("employ:employ_position:query")
    @GetMapping(value = "/{positionId}")
    public AjaxResult getInfo(@PathVariable("positionId") Long positionId)
    {
        return success(eduRecruitPositionService.selectEduRecruitPositionByPositionId(positionId));
    }

    /**
     * 新增招聘岗位
     */
    @RequiresPermissions("employ:employ_position:add")
    @Log(title = "招聘岗位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduRecruitPosition eduRecruitPosition)
    {
        return toAjax(eduRecruitPositionService.insertEduRecruitPosition(eduRecruitPosition));
    }

    /**
     * 修改招聘岗位
     */
    @RequiresPermissions("employ:employ_position:edit")
    @Log(title = "招聘岗位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduRecruitPosition eduRecruitPosition)
    {
        return toAjax(eduRecruitPositionService.updateEduRecruitPosition(eduRecruitPosition));
    }

    /**
     * 删除招聘岗位
     */
    @RequiresPermissions("employ:employ_position:remove")
    @Log(title = "招聘岗位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{positionIds}")
    public AjaxResult remove(@PathVariable Long[] positionIds)
    {
        return toAjax(eduRecruitPositionService.deleteEduRecruitPositionByPositionIds(positionIds));
    }
}
