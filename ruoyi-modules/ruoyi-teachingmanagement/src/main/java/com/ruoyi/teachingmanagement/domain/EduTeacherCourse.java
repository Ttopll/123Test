package com.ruoyi.teachingmanagement.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 教师课程关联对象 edu_teacher_course
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduTeacherCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联记录 ID */
    private Long tcId;

    /** 教师 ID（关联用户表教师类型） */
    @Excel(name = "教师 ID", readConverterExp = "关=联用户表教师类型")
    private String teacherId;

    /** 课程 ID */
    @Excel(name = "课程 ID")
    private Long courseId;

    /** 学期（如：2024-2025-1） */
    @Excel(name = "学期", readConverterExp = "如=：2024-2025-1")
    private String semester;

    /** 上课时间（如：周一 3-4 节） */
    @Excel(name = "上课时间", readConverterExp = "如=：周一,3=-4,节=")
    private String classTime;

    /** 上课教室 */
    @Excel(name = "上课教室")
    private String classRoom;

    /** 选课人数限制 */
    @Excel(name = "选课人数限制")
    private Long studentLimit;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setTcId(Long tcId) 
    {
        this.tcId = tcId;
    }

    public Long getTcId() 
    {
        return tcId;
    }

    public void setTeacherId(String teacherId) 
    {
        this.teacherId = teacherId;
    }

    public String getTeacherId() 
    {
        return teacherId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
    }

    public void setClassTime(String classTime) 
    {
        this.classTime = classTime;
    }

    public String getClassTime() 
    {
        return classTime;
    }

    public void setClassRoom(String classRoom) 
    {
        this.classRoom = classRoom;
    }

    public String getClassRoom() 
    {
        return classRoom;
    }

    public void setStudentLimit(Long studentLimit) 
    {
        this.studentLimit = studentLimit;
    }

    public Long getStudentLimit() 
    {
        return studentLimit;
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
            .append("tcId", getTcId())
            .append("teacherId", getTeacherId())
            .append("courseId", getCourseId())
            .append("semester", getSemester())
            .append("classTime", getClassTime())
            .append("classRoom", getClassRoom())
            .append("studentLimit", getStudentLimit())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
