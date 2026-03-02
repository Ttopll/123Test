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
import com.ruoyi.employ.domain.EduResumeDelivery;
import com.ruoyi.employ.service.IEduResumeDeliveryService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 简历投递记录Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/employ_delivery")
public class EduResumeDeliveryController extends BaseController
{
    @Autowired
    private IEduResumeDeliveryService eduResumeDeliveryService;

    /**
     * 查询简历投递记录列表
     */
    @RequiresPermissions("employ:employ_delivery:list")
    @GetMapping("/list")
    public TableDataInfo list(EduResumeDelivery eduResumeDelivery)
    {
        startPage();
        List<EduResumeDelivery> list = eduResumeDeliveryService.selectEduResumeDeliveryList(eduResumeDelivery);
        return getDataTable(list);
    }

    /**
     * 导出简历投递记录列表
     */
    @RequiresPermissions("employ:employ_delivery:export")
    @Log(title = "简历投递记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduResumeDelivery eduResumeDelivery)
    {
        List<EduResumeDelivery> list = eduResumeDeliveryService.selectEduResumeDeliveryList(eduResumeDelivery);
        ExcelUtil<EduResumeDelivery> util = new ExcelUtil<EduResumeDelivery>(EduResumeDelivery.class);
        util.exportExcel(response, list, "简历投递记录数据");
    }

    /**
     * 获取简历投递记录详细信息
     */
    @RequiresPermissions("employ:employ_delivery:query")
    @GetMapping(value = "/{deliveryId}")
    public AjaxResult getInfo(@PathVariable("deliveryId") Long deliveryId)
    {
        return success(eduResumeDeliveryService.selectEduResumeDeliveryByDeliveryId(deliveryId));
    }

    /**
     * 新增简历投递记录
     */
    @RequiresPermissions("employ:employ_delivery:add")
    @Log(title = "简历投递记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduResumeDelivery eduResumeDelivery)
    {
        return toAjax(eduResumeDeliveryService.insertEduResumeDelivery(eduResumeDelivery));
    }

    /**
     * 修改简历投递记录
     */
    @RequiresPermissions("employ:employ_delivery:edit")
    @Log(title = "简历投递记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduResumeDelivery eduResumeDelivery)
    {
        return toAjax(eduResumeDeliveryService.updateEduResumeDelivery(eduResumeDelivery));
    }

    /**
     * 删除简历投递记录
     */
    @RequiresPermissions("employ:employ_delivery:remove")
    @Log(title = "简历投递记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deliveryIds}")
    public AjaxResult remove(@PathVariable Long[] deliveryIds)
    {
        return toAjax(eduResumeDeliveryService.deleteEduResumeDeliveryByDeliveryIds(deliveryIds));
    }
}
