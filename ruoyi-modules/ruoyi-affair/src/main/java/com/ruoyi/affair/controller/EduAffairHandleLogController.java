package com.ruoyi.affair.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.affair.domain.EduAffairHandleLog;
import com.ruoyi.affair.service.IEduAffairHandleLogService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 事务处理日志Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/affair_log")
public class EduAffairHandleLogController extends BaseController
{
    @Autowired
    private IEduAffairHandleLogService eduAffairHandleLogService;

    /**
     * 查询事务处理日志列表
     */
    @RequiresPermissions("affair:affair_log:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAffairHandleLog eduAffairHandleLog)
    {
        startPage();
        List<EduAffairHandleLog> list = eduAffairHandleLogService.selectEduAffairHandleLogList(eduAffairHandleLog);
        return getDataTable(list);
    }

    /**
     * 导出事务处理日志列表
     */
    @RequiresPermissions("affair:affair_log:export")
    @Log(title = "事务处理日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAffairHandleLog eduAffairHandleLog)
    {
        List<EduAffairHandleLog> list = eduAffairHandleLogService.selectEduAffairHandleLogList(eduAffairHandleLog);
        ExcelUtil<EduAffairHandleLog> util = new ExcelUtil<EduAffairHandleLog>(EduAffairHandleLog.class);
        util.exportExcel(response, list, "事务处理日志数据");
    }

    /**
     * 获取事务处理日志详细信息
     */
    @RequiresPermissions("affair:affair_log:query")
    @GetMapping(value = "/{logId}")
    public AjaxResult getInfo(@PathVariable("logId") Long logId)
    {
        return success(eduAffairHandleLogService.selectEduAffairHandleLogByLogId(logId));
    }

    /**
     * 新增事务处理日志
     */
    @RequiresPermissions("affair:affair_log:add")
    @Log(title = "事务处理日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAffairHandleLog eduAffairHandleLog)
    {
        Long applyId = eduAffairHandleLog.getApplyId();
        Assert.notNull(applyId, "事务申请Id不能为空");

        return toAjax(eduAffairHandleLogService.handleEduAffairApply(eduAffairHandleLog));
    }

    /**
     * 删除事务处理日志
     */
    @RequiresPermissions("affair:affair_log:remove")
    @Log(title = "事务处理日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{logIds}")
    public AjaxResult remove(@PathVariable Long[] logIds, @RequestParam String updateUserId)
    {
        return toAjax(eduAffairHandleLogService.deleteEduAffairHandleLogByLogIds(logIds, updateUserId));
    }
}
