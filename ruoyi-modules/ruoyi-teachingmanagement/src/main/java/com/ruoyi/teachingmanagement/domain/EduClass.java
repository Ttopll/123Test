package com.ruoyi.teachingmanagement.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 班级管理对象 edu_class
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班级 ID */
    private Long classId;

    /** 班级名称（如：计科 2021-1 班） */
    @Excel(name = "班级名称", readConverterExp = "如=：计科,2=021-1,班=")
    private String className;

    /** 班级编码（如：CS202101） */
    @Excel(name = "班级编码", readConverterExp = "如=：CS202101")
    private String classCode;

    /** 关联专业 ID */
    @Excel(name = "关联专业 ID")
    private Long majorId;

    /** 年级（如：2021 级） */
    @Excel(name = "年级", readConverterExp = "如=：2021,级=")
    private String grade;

    /** 班主任 ID */
    @Excel(name = "班主任 ID")
    private String headTeacherId;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }

    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }

    public void setClassCode(String classCode) 
    {
        this.classCode = classCode;
    }

    public String getClassCode() 
    {
        return classCode;
    }

    public void setMajorId(Long majorId) 
    {
        this.majorId = majorId;
    }

    public Long getMajorId() 
    {
        return majorId;
    }

    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }

    public void setHeadTeacherId(String headTeacherId) 
    {
        this.headTeacherId = headTeacherId;
    }

    public String getHeadTeacherId() 
    {
        return headTeacherId;
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
            .append("classId", getClassId())
            .append("className", getClassName())
            .append("classCode", getClassCode())
            .append("majorId", getMajorId())
            .append("grade", getGrade())
            .append("headTeacherId", getHeadTeacherId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
