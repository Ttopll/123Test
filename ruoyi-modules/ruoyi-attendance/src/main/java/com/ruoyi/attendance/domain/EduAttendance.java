package com.ruoyi.attendance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 考勤管理对象 edu_attendance
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAttendance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考勤 ID */
    private Long attendId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 课程 ID */
    @Excel(name = "课程 ID")
    private Long courseId;

    /** 考勤日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date attendDate;

    /** 考勤类型 */
    @Excel(name = "考勤类型")
    private Integer attendType;

    /** 签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 补签申请情况 */
    @Excel(name = "补签申请情况")
    private Integer supplementApply;

    /** 补签理由（如生病、请假等） */
    @Excel(name = "补签理由", readConverterExp = "如=生病、请假等")
    private String supplementReason;

    /** 补签处理情况 */
    @Excel(name = "补签处理情况")
    private Integer supplementResult;

    /** 审批教师 ID */
    @Excel(name = "审批教师 ID")
    private String approveUserId;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /** 学期（如：2024-2025-1） */
    @Excel(name = "学期", readConverterExp = "如=：2024-2025-1")
    private String semester;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setAttendId(Long attendId) 
    {
        this.attendId = attendId;
    }

    public Long getAttendId() 
    {
        return attendId;
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

    public void setAttendDate(Date attendDate) 
    {
        this.attendDate = attendDate;
    }

    public Date getAttendDate() 
    {
        return attendDate;
    }

    public void setAttendType(Integer attendType) 
    {
        this.attendType = attendType;
    }

    public Integer getAttendType() 
    {
        return attendType;
    }

    public void setSignTime(Date signTime) 
    {
        this.signTime = signTime;
    }

    public Date getSignTime() 
    {
        return signTime;
    }

    public void setSupplementApply(Integer supplementApply) 
    {
        this.supplementApply = supplementApply;
    }

    public Integer getSupplementApply() 
    {
        return supplementApply;
    }

    public void setSupplementReason(String supplementReason) 
    {
        this.supplementReason = supplementReason;
    }

    public String getSupplementReason() 
    {
        return supplementReason;
    }

    public void setSupplementResult(Integer supplementResult) 
    {
        this.supplementResult = supplementResult;
    }

    public Integer getSupplementResult() 
    {
        return supplementResult;
    }

    public void setApproveUserId(String approveUserId) 
    {
        this.approveUserId = approveUserId;
    }

    public String getApproveUserId() 
    {
        return approveUserId;
    }

    public void setApproveTime(Date approveTime) 
    {
        this.approveTime = approveTime;
    }

    public Date getApproveTime() 
    {
        return approveTime;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
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
            .append("attendId", getAttendId())
            .append("studentId", getStudentId())
            .append("courseId", getCourseId())
            .append("attendDate", getAttendDate())
            .append("attendType", getAttendType())
            .append("signTime", getSignTime())
            .append("supplementApply", getSupplementApply())
            .append("supplementReason", getSupplementReason())
            .append("supplementResult", getSupplementResult())
            .append("approveUserId", getApproveUserId())
            .append("approveTime", getApproveTime())
            .append("semester", getSemester())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
