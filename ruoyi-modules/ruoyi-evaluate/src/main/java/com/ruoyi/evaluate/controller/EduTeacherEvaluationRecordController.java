package com.ruoyi.evaluate.controller;

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
import com.ruoyi.evaluate.domain.EduTeacherEvaluationRecord;
import com.ruoyi.evaluate.service.IEduTeacherEvaluationRecordService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 教师评价记录Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/evaluate_record")
public class EduTeacherEvaluationRecordController extends BaseController
{
    @Autowired
    private IEduTeacherEvaluationRecordService eduTeacherEvaluationRecordService;

    /**
     * 查询教师评价记录列表
     */
    @RequiresPermissions("evaluate:evaluate_record:list")
    @GetMapping("/list")
    public TableDataInfo list(EduTeacherEvaluationRecord eduTeacherEvaluationRecord)
    {
        startPage();
        List<EduTeacherEvaluationRecord> list = eduTeacherEvaluationRecordService.selectEduTeacherEvaluationRecordList(eduTeacherEvaluationRecord);
        return getDataTable(list);
    }

    /**
     * 导出教师评价记录列表
     */
    @RequiresPermissions("evaluate:evaluate_record:export")
    @Log(title = "教师评价记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduTeacherEvaluationRecord eduTeacherEvaluationRecord)
    {
        List<EduTeacherEvaluationRecord> list = eduTeacherEvaluationRecordService.selectEduTeacherEvaluationRecordList(eduTeacherEvaluationRecord);
        ExcelUtil<EduTeacherEvaluationRecord> util = new ExcelUtil<EduTeacherEvaluationRecord>(EduTeacherEvaluationRecord.class);
        util.exportExcel(response, list, "教师评价记录数据");
    }

    /**
     * 获取教师评价记录详细信息
     */
    @RequiresPermissions("evaluate:evaluate_record:query")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") String recordId)
    {
        return success(eduTeacherEvaluationRecordService.selectEduTeacherEvaluationRecordByRecordId(recordId));
    }

    /**
     * 新增教师评价记录
     */
    @RequiresPermissions("evaluate:evaluate_record:add")
    @Log(title = "教师评价记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTeacherEvaluationRecord eduTeacherEvaluationRecord)
    {
        return toAjax(eduTeacherEvaluationRecordService.insertEduTeacherEvaluationRecord(eduTeacherEvaluationRecord));
    }

    /**
     * 修改教师评价记录
     */
    @RequiresPermissions("evaluate:evaluate_record:edit")
    @Log(title = "教师评价记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTeacherEvaluationRecord eduTeacherEvaluationRecord)
    {
        return toAjax(eduTeacherEvaluationRecordService.updateEduTeacherEvaluationRecord(eduTeacherEvaluationRecord));
    }

    /**
     * 删除教师评价记录
     */
    @RequiresPermissions("evaluate:evaluate_record:remove")
    @Log(title = "教师评价记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable String[] recordIds)
    {
        return toAjax(eduTeacherEvaluationRecordService.deleteEduTeacherEvaluationRecordByRecordIds(recordIds));
    }
}
