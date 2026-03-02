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
import com.ruoyi.academic.domain.EduGrowthRecord;
import com.ruoyi.academic.service.IEduGrowthRecordService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 学生成长记录Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/growth-record")
public class EduGrowthRecordController extends BaseController
{
    @Autowired
    private IEduGrowthRecordService eduGrowthRecordService;

    /**
     * 查询学生成长记录列表
     */
    @RequiresPermissions("academic:growth-record:list")
    @GetMapping("/list")
    public TableDataInfo list(EduGrowthRecord eduGrowthRecord)
    {
        startPage();
        List<EduGrowthRecord> list = eduGrowthRecordService.selectEduGrowthRecordList(eduGrowthRecord);
        return getDataTable(list);
    }

    /**
     * 导出学生成长记录列表
     */
    @RequiresPermissions("academic:growth-record:export")
    @Log(title = "学生成长记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduGrowthRecord eduGrowthRecord)
    {
        List<EduGrowthRecord> list = eduGrowthRecordService.selectEduGrowthRecordList(eduGrowthRecord);
        ExcelUtil<EduGrowthRecord> util = new ExcelUtil<EduGrowthRecord>(EduGrowthRecord.class);
        util.exportExcel(response, list, "学生成长记录数据");
    }

    /**
     * 获取学生成长记录详细信息
     */
    @RequiresPermissions("academic:growth-record:query")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(eduGrowthRecordService.selectEduGrowthRecordByRecordId(recordId));
    }

    /**
     * 新增学生成长记录
     */
    @RequiresPermissions("academic:growth-record:add")
    @Log(title = "学生成长记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduGrowthRecord eduGrowthRecord)
    {
        return toAjax(eduGrowthRecordService.insertEduGrowthRecord(eduGrowthRecord));
    }

    /**
     * 修改学生成长记录
     */
    @RequiresPermissions("academic:growth-record:edit")
    @Log(title = "学生成长记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduGrowthRecord eduGrowthRecord)
    {
        return toAjax(eduGrowthRecordService.updateEduGrowthRecord(eduGrowthRecord));
    }

    /**
     * 删除学生成长记录
     */
    @RequiresPermissions("academic:growth-record:remove")
    @Log(title = "学生成长记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(eduGrowthRecordService.deleteEduGrowthRecordByRecordIds(recordIds));
    }
}
