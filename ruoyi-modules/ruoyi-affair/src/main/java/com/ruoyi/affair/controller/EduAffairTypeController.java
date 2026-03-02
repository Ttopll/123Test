package com.ruoyi.affair.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.affair.domain.EduAffairType;
import com.ruoyi.affair.service.IEduAffairTypeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 事务类型Controller
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@RestController
@RequestMapping("/affair_type")
public class EduAffairTypeController extends BaseController
{
    @Autowired
    private IEduAffairTypeService eduAffairTypeService;

    /**
     * 查询事务类型列表
     */
    @RequiresPermissions("affair:affair_type:list")
    @GetMapping("/list")
    public TableDataInfo list(EduAffairType eduAffairType)
    {
        startPage();
        List<EduAffairType> list = eduAffairTypeService.selectEduAffairTypeList(eduAffairType);
        return getDataTable(list);
    }

    /**
     * 导出事务类型列表
     */
    @RequiresPermissions("affair:affair_type:export")
    @Log(title = "事务类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAffairType eduAffairType)
    {
        List<EduAffairType> list = eduAffairTypeService.selectEduAffairTypeList(eduAffairType);
        ExcelUtil<EduAffairType> util = new ExcelUtil<EduAffairType>(EduAffairType.class);
        util.exportExcel(response, list, "事务类型数据");
    }

    /**
     * 获取事务类型详细信息
     */
    @RequiresPermissions("affair:affair_type:query")
    @GetMapping(value = "/{affairTypeId}")
    public AjaxResult getInfo(@PathVariable("affairTypeId") Long affairTypeId)
    {
        return success(eduAffairTypeService.selectEduAffairTypeByAffairTypeId(affairTypeId));
    }

    /**
     * 新增事务类型
     */
    @RequiresPermissions("affair:affair_type:add")
    @Log(title = "事务类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAffairType eduAffairType)
    {
        return toAjax(eduAffairTypeService.insertEduAffairType(eduAffairType));
    }

    /**
     * 修改事务类型
     */
    @RequiresPermissions("affair:affair_type:edit")
    @Log(title = "事务类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAffairType eduAffairType)
    {
        return toAjax(eduAffairTypeService.updateEduAffairType(eduAffairType));
    }

    /**
     * 删除事务类型
     */
    @RequiresPermissions("affair:affair_type:remove")
    @Log(title = "事务类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{affairTypeIds}")
    public AjaxResult remove(@PathVariable Long[] affairTypeIds, @RequestParam String updateUserId)
    {
        return toAjax(eduAffairTypeService.deleteEduAffairTypeByAffairTypeIds(affairTypeIds, updateUserId));
    }
}
