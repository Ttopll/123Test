package com.ruoyi.teachingmanagement.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 课程管理对象 edu_course
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程 ID */
    private Long courseId;

    /** 课程名称（如：数据库原理） */
    @Excel(name = "课程名称", readConverterExp = "如=：数据库原理")
    private String courseName;

    /** 课程编码（如：CS101） */
    @Excel(name = "课程编码", readConverterExp = "如=：CS101")
    private String courseCode;

    /** 学分 */
    @Excel(name = "学分")
    private BigDecimal credit;

    /** 关联院系 ID */
    @Excel(name = "关联院系 ID")
    private Long deptId;

    /** 课程类型 */
    @Excel(name = "课程类型")
    private Integer courseType;

    /** 总学时 */
    @Excel(name = "总学时")
    private Long totalHours;

    /** 理论学时 */
    @Excel(name = "理论学时")
    private Long theoryHours;

    /** 实践学时 */
    @Excel(name = "实践学时")
    private Long practiceHours;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public void setCourseCode(String courseCode) 
    {
        this.courseCode = courseCode;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }

    public void setCredit(BigDecimal credit) 
    {
        this.credit = credit;
    }

    public BigDecimal getCredit() 
    {
        return credit;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setCourseType(Integer courseType) 
    {
        this.courseType = courseType;
    }

    public Integer getCourseType() 
    {
        return courseType;
    }

    public void setTotalHours(Long totalHours) 
    {
        this.totalHours = totalHours;
    }

    public Long getTotalHours() 
    {
        return totalHours;
    }

    public void setTheoryHours(Long theoryHours) 
    {
        this.theoryHours = theoryHours;
    }

    public Long getTheoryHours() 
    {
        return theoryHours;
    }

    public void setPracticeHours(Long practiceHours) 
    {
        this.practiceHours = practiceHours;
    }

    public Long getPracticeHours() 
    {
        return practiceHours;
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
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("courseCode", getCourseCode())
            .append("credit", getCredit())
            .append("deptId", getDeptId())
            .append("courseType", getCourseType())
            .append("totalHours", getTotalHours())
            .append("theoryHours", getTheoryHours())
            .append("practiceHours", getPracticeHours())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
