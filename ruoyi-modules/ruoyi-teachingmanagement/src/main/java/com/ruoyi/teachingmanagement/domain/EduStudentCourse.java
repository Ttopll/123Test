package com.ruoyi.teachingmanagement.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 学生课程关联对象 edu_student_course
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduStudentCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生课程关联记录 ID */
    private Long scId;

    /** 学生 ID（关联用户表中学生类型用户） */
    @Excel(name = "学生 ID", readConverterExp = "关=联用户表中学生类型用户")
    private String studentId;

    /** 课程 ID */
    @Excel(name = "课程 ID")
    private Long courseId;

    /** 授课教师 ID（关联用户表中教师类型用户） */
    @Excel(name = "授课教师 ID", readConverterExp = "关=联用户表中教师类型用户")
    private Long teacherId;

    /** 选课学期（如：2024-2025-1） */
    @Excel(name = "选课学期", readConverterExp = "如=：2024-2025-1")
    private String semester;

    /** 选课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "选课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date selectTime;

    /** 退课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dropTime;

    /** 选课状态 */
    @Excel(name = "选课状态")
    private Integer scStatus;

    /** 选课冲突类型 */
    @Excel(name = "选课冲突类型")
    private Integer conflictFlag;

    /** 选课冲突解决类型 */
    @Excel(name = "选课冲突解决类型")
    private Integer conflictResolveFlag;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setScId(Long scId) 
    {
        this.scId = scId;
    }

    public Long getScId() 
    {
        return scId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
    }

    public void setSelectTime(Date selectTime) 
    {
        this.selectTime = selectTime;
    }

    public Date getSelectTime() 
    {
        return selectTime;
    }

    public void setDropTime(Date dropTime) 
    {
        this.dropTime = dropTime;
    }

    public Date getDropTime() 
    {
        return dropTime;
    }

    public void setScStatus(Integer scStatus) 
    {
        this.scStatus = scStatus;
    }

    public Integer getScStatus() 
    {
        return scStatus;
    }

    public void setConflictFlag(Integer conflictFlag) 
    {
        this.conflictFlag = conflictFlag;
    }

    public Integer getConflictFlag() 
    {
        return conflictFlag;
    }

    public void setConflictResolveFlag(Integer conflictResolveFlag) 
    {
        this.conflictResolveFlag = conflictResolveFlag;
    }

    public Integer getConflictResolveFlag() 
    {
        return conflictResolveFlag;
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
            .append("scId", getScId())
            .append("studentId", getStudentId())
            .append("courseId", getCourseId())
            .append("teacherId", getTeacherId())
            .append("semester", getSemester())
            .append("selectTime", getSelectTime())
            .append("dropTime", getDropTime())
            .append("scStatus", getScStatus())
            .append("conflictFlag", getConflictFlag())
            .append("conflictResolveFlag", getConflictResolveFlag())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
