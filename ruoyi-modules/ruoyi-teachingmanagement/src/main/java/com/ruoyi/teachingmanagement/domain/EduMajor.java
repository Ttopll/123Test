package com.ruoyi.teachingmanagement.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 专业管理对象 edu_major
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduMajor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 专业 ID */
    private Long majorId;

    /** 专业名称（如：计算机科学与技术） */
    @Excel(name = "专业名称", readConverterExp = "如=：计算机科学与技术")
    private String majorName;

    /** 专业编码（如：080901） */
    @Excel(name = "专业编码", readConverterExp = "如=：080901")
    private String majorCode;

    /** 关联院系 ID */
    @Excel(name = "关联院系 ID")
    private Long deptId;

    /** 培养层次（本科 / 专科） */
    @Excel(name = "培养层次", readConverterExp = "本=科,/=,专=科")
    private String trainingLevel;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setMajorId(Long majorId) 
    {
        this.majorId = majorId;
    }

    public Long getMajorId() 
    {
        return majorId;
    }

    public void setMajorName(String majorName) 
    {
        this.majorName = majorName;
    }

    public String getMajorName() 
    {
        return majorName;
    }

    public void setMajorCode(String majorCode) 
    {
        this.majorCode = majorCode;
    }

    public String getMajorCode() 
    {
        return majorCode;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setTrainingLevel(String trainingLevel) 
    {
        this.trainingLevel = trainingLevel;
    }

    public String getTrainingLevel() 
    {
        return trainingLevel;
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
            .append("majorId", getMajorId())
            .append("majorName", getMajorName())
            .append("majorCode", getMajorCode())
            .append("deptId", getDeptId())
            .append("trainingLevel", getTrainingLevel())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
