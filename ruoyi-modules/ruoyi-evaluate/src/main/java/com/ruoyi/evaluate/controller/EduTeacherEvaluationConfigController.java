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
import com.ruoyi.evaluate.domain.EduTeacherEvaluationConfig;
import com.ruoyi.evaluate.service.IEduTeacherEvaluationConfigService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 教师评价配置Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/evaluate_config")
public class EduTeacherEvaluationConfigController extends BaseController
{
    @Autowired
    private IEduTeacherEvaluationConfigService eduTeacherEvaluationConfigService;

    /**
     * 查询教师评价配置列表
     */
    @RequiresPermissions("evaluate:evaluate_config:list")
    @GetMapping("/list")
    public TableDataInfo list(EduTeacherEvaluationConfig eduTeacherEvaluationConfig)
    {
        startPage();
        List<EduTeacherEvaluationConfig> list = eduTeacherEvaluationConfigService.selectEduTeacherEvaluationConfigList(eduTeacherEvaluationConfig);
        return getDataTable(list);
    }

    /**
     * 导出教师评价配置列表
     */
    @RequiresPermissions("evaluate:evaluate_config:export")
    @Log(title = "教师评价配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduTeacherEvaluationConfig eduTeacherEvaluationConfig)
    {
        List<EduTeacherEvaluationConfig> list = eduTeacherEvaluationConfigService.selectEduTeacherEvaluationConfigList(eduTeacherEvaluationConfig);
        ExcelUtil<EduTeacherEvaluationConfig> util = new ExcelUtil<EduTeacherEvaluationConfig>(EduTeacherEvaluationConfig.class);
        util.exportExcel(response, list, "教师评价配置数据");
    }

    /**
     * 获取教师评价配置详细信息
     */
    @RequiresPermissions("evaluate:evaluate_config:query")
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable("configId") Long configId)
    {
        return success(eduTeacherEvaluationConfigService.selectEduTeacherEvaluationConfigByConfigId(configId));
    }

    /**
     * 新增教师评价配置
     */
    @RequiresPermissions("evaluate:evaluate_config:add")
    @Log(title = "教师评价配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTeacherEvaluationConfig eduTeacherEvaluationConfig)
    {
        return toAjax(eduTeacherEvaluationConfigService.insertEduTeacherEvaluationConfig(eduTeacherEvaluationConfig));
    }

    /**
     * 修改教师评价配置
     */
    @RequiresPermissions("evaluate:evaluate_config:edit")
    @Log(title = "教师评价配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTeacherEvaluationConfig eduTeacherEvaluationConfig)
    {
        return toAjax(eduTeacherEvaluationConfigService.updateEduTeacherEvaluationConfig(eduTeacherEvaluationConfig));
    }

    /**
     * 删除教师评价配置
     */
    @RequiresPermissions("evaluate:evaluate_config:remove")
    @Log(title = "教师评价配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds)
    {
        return toAjax(eduTeacherEvaluationConfigService.deleteEduTeacherEvaluationConfigByConfigIds(configIds));
    }
}
