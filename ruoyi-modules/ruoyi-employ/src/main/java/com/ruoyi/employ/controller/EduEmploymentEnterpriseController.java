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
import com.ruoyi.employ.domain.EduEmploymentEnterprise;
import com.ruoyi.employ.service.IEduEmploymentEnterpriseService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 就业合作企业Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/employ_enterprise")
public class EduEmploymentEnterpriseController extends BaseController
{
    @Autowired
    private IEduEmploymentEnterpriseService eduEmploymentEnterpriseService;

    /**
     * 查询就业合作企业列表
     */
    @RequiresPermissions("employ:employ_enterprise:list")
    @GetMapping("/list")
    public TableDataInfo list(EduEmploymentEnterprise eduEmploymentEnterprise)
    {
        startPage();
        List<EduEmploymentEnterprise> list = eduEmploymentEnterpriseService.selectEduEmploymentEnterpriseList(eduEmploymentEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出就业合作企业列表
     */
    @RequiresPermissions("employ:employ_enterprise:export")
    @Log(title = "就业合作企业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduEmploymentEnterprise eduEmploymentEnterprise)
    {
        List<EduEmploymentEnterprise> list = eduEmploymentEnterpriseService.selectEduEmploymentEnterpriseList(eduEmploymentEnterprise);
        ExcelUtil<EduEmploymentEnterprise> util = new ExcelUtil<EduEmploymentEnterprise>(EduEmploymentEnterprise.class);
        util.exportExcel(response, list, "就业合作企业数据");
    }

    /**
     * 获取就业合作企业详细信息
     */
    @RequiresPermissions("employ:employ_enterprise:query")
    @GetMapping(value = "/{enterpriseId}")
    public AjaxResult getInfo(@PathVariable("enterpriseId") String enterpriseId)
    {
        return success(eduEmploymentEnterpriseService.selectEduEmploymentEnterpriseByEnterpriseId(enterpriseId));
    }

    /**
     * 新增就业合作企业
     */
    @RequiresPermissions("employ:employ_enterprise:add")
    @Log(title = "就业合作企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduEmploymentEnterprise eduEmploymentEnterprise)
    {
        return toAjax(eduEmploymentEnterpriseService.insertEduEmploymentEnterprise(eduEmploymentEnterprise));
    }

    /**
     * 修改就业合作企业
     */
    @RequiresPermissions("employ:employ_enterprise:edit")
    @Log(title = "就业合作企业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduEmploymentEnterprise eduEmploymentEnterprise)
    {
        return toAjax(eduEmploymentEnterpriseService.updateEduEmploymentEnterprise(eduEmploymentEnterprise));
    }

    /**
     * 删除就业合作企业
     */
    @RequiresPermissions("employ:employ_enterprise:remove")
    @Log(title = "就业合作企业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{enterpriseIds}")
    public AjaxResult remove(@PathVariable String[] enterpriseIds)
    {
        return toAjax(eduEmploymentEnterpriseService.deleteEduEmploymentEnterpriseByEnterpriseIds(enterpriseIds));
    }
}
