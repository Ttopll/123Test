package com.ruoyi.teachingmanagement.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 院系管理对象 edu_department
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduDepartment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 院系 ID */
    private Long deptId;

    /** 院系名称（如：计算机学院） */
    @Excel(name = "院系名称", readConverterExp = "如=：计算机学院")
    private String deptName;

    /** 院系编码（如：CS） */
    @Excel(name = "院系编码", readConverterExp = "如=：CS")
    private String deptCode;

    /** 父院系 ID */
    @Excel(name = "父院系 ID")
    private Long parentId;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sort;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }

    public void setDeptCode(String deptCode) 
    {
        this.deptCode = deptCode;
    }

    public String getDeptCode() 
    {
        return deptCode;
    }

    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setUpdateUserId(String updateUserId) 
    {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserId() 
    {
        return updateUserId;
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
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("deptCode", getDeptCode())
            .append("parentId", getParentId())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
