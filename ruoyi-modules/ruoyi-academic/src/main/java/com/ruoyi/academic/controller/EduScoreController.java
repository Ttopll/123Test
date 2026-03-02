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
import com.ruoyi.academic.domain.EduScore;
import com.ruoyi.academic.service.IEduScoreService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 成绩管理Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/score")
public class EduScoreController extends BaseController
{
    @Autowired
    private IEduScoreService eduScoreService;

    /**
     * 查询成绩管理列表
     */
    @RequiresPermissions("academic:score:list")
    @GetMapping("/list")
    public TableDataInfo list(EduScore eduScore)
    {
        startPage();
        List<EduScore> list = eduScoreService.selectEduScoreList(eduScore);
        return getDataTable(list);
    }

    /**
     * 导出成绩管理列表
     */
    @RequiresPermissions("academic:score:export")
    @Log(title = "成绩管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduScore eduScore)
    {
        List<EduScore> list = eduScoreService.selectEduScoreList(eduScore);
        ExcelUtil<EduScore> util = new ExcelUtil<EduScore>(EduScore.class);
        util.exportExcel(response, list, "成绩管理数据");
    }

    /**
     * 获取成绩管理详细信息
     */
    @RequiresPermissions("academic:score:query")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId)
    {
        return success(eduScoreService.selectEduScoreByScoreId(scoreId));
    }

    /**
     * 新增成绩管理
     */
    @RequiresPermissions("academic:score:add")
    @Log(title = "成绩管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduScore eduScore)
    {
        return toAjax(eduScoreService.insertEduScore(eduScore));
    }

    /**
     * 修改成绩管理
     */
    @RequiresPermissions("academic:score:edit")
    @Log(title = "成绩管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduScore eduScore)
    {
        return toAjax(eduScoreService.updateEduScore(eduScore));
    }

    /**
     * 删除成绩管理
     */
    @RequiresPermissions("academic:score:remove")
    @Log(title = "成绩管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(eduScoreService.deleteEduScoreByScoreIds(scoreIds));
    }
}
