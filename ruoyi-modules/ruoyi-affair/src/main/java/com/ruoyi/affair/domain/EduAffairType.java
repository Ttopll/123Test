package com.ruoyi.affair.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 事务类型对象 edu_affair_type
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAffairType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型 ID */
    private Long affairTypeId;

    /** 类型名称（如：请假申请） */
    @Excel(name = "类型名称", readConverterExp = "如=：请假申请")
    private String typeName;

    /** 类型编码（如：LEAVE） */
    @Excel(name = "类型编码", readConverterExp = "如=：LEAVE")
    private String typeCode;

    /** 允许提交角色（STUDENT,TEACHER） */
    @Excel(name = "允许提交角色", readConverterExp = "S=STUDENT,TEACHER")
    private String applyRole;

    /** 负责部门（如：教务处） */
    @Excel(name = "负责部门", readConverterExp = "如=：教务处")
    private String handleDept;

    /** 负责角色（如：EDU_ADMIN） */
    @Excel(name = "负责角色", readConverterExp = "如=：EDU_ADMIN")
    private String handleRole;

    /** 处理时限（3 天） */
    @Excel(name = "处理时限", readConverterExp = "3=,天=")
    private Long handleLimit;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sort;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setAffairTypeId(Long affairTypeId) 
    {
        this.affairTypeId = affairTypeId;
    }

    public Long getAffairTypeId() 
    {
        return affairTypeId;
    }

    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }

    public void setTypeCode(String typeCode) 
    {
        this.typeCode = typeCode;
    }

    public String getTypeCode() 
    {
        return typeCode;
    }

    public void setApplyRole(String applyRole) 
    {
        this.applyRole = applyRole;
    }

    public String getApplyRole() 
    {
        return applyRole;
    }

    public void setHandleDept(String handleDept) 
    {
        this.handleDept = handleDept;
    }

    public String getHandleDept() 
    {
        return handleDept;
    }

    public void setHandleRole(String handleRole) 
    {
        this.handleRole = handleRole;
    }

    public String getHandleRole() 
    {
        return handleRole;
    }

    public void setHandleLimit(Long handleLimit) 
    {
        this.handleLimit = handleLimit;
    }

    public Long getHandleLimit() 
    {
        return handleLimit;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("affairTypeId", getAffairTypeId())
            .append("typeName", getTypeName())
            .append("typeCode", getTypeCode())
            .append("applyRole", getApplyRole())
            .append("handleDept", getHandleDept())
            .append("handleRole", getHandleRole())
            .append("handleLimit", getHandleLimit())
            .append("status", getStatus())
            .append("sort", getSort())
            .append("remark", getRemark())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
