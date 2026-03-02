package com.ruoyi.classSchedule.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 排课结果对象 edu_timetable_result
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public class EduTimetableResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 结果 ID */
    private Long resultId;

    /** 任务 ID */
    @Excel(name = "任务 ID")
    private String taskId;

    /** 教师课程 ID */
    @Excel(name = "教师课程 ID")
    private Long teacherCourseId;

    /** 教室 ID */
    @Excel(name = "教室 ID")
    private String classroomId;

    /** 周次（如：1-16） */
    @Excel(name = "周次", readConverterExp = "如=：1-16")
    private Long week;

    /** 星期 */
    @Excel(name = "星期")
    private Integer weekday;

    /** 节次（如：1-2 节） */
    @Excel(name = "节次", readConverterExp = "如=：1-2,节=")
    private String period;

    /** 上课开始时间（如：08:30） */
    @Excel(name = "上课开始时间", readConverterExp = "如=：08:30")
    private Date startTime;

    /** 上课结束时间（如：10:00） */
    @Excel(name = "上课结束时间", readConverterExp = "如=：10:00")
    private Date endTime;

    /** 选课学生人数 */
    @Excel(name = "选课学生人数")
    private Long studentCount;

    /** 是否冲突 */
    @Excel(name = "是否冲突")
    private Integer isConflict;

    /** 冲突原因 */
    @Excel(name = "冲突原因")
    private String conflictReason;

    /** 结果状态 */
    @Excel(name = "结果状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setResultId(Long resultId) 
    {
        this.resultId = resultId;
    }

    public Long getResultId() 
    {
        return resultId;
    }

    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }

    public void setTeacherCourseId(Long teacherCourseId) 
    {
        this.teacherCourseId = teacherCourseId;
    }

    public Long getTeacherCourseId() 
    {
        return teacherCourseId;
    }

    public void setClassroomId(String classroomId) 
    {
        this.classroomId = classroomId;
    }

    public String getClassroomId() 
    {
        return classroomId;
    }

    public void setWeek(Long week) 
    {
        this.week = week;
    }

    public Long getWeek() 
    {
        return week;
    }

    public void setWeekday(Integer weekday) 
    {
        this.weekday = weekday;
    }

    public Integer getWeekday() 
    {
        return weekday;
    }

    public void setPeriod(String period) 
    {
        this.period = period;
    }

    public String getPeriod() 
    {
        return period;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setStudentCount(Long studentCount) 
    {
        this.studentCount = studentCount;
    }

    public Long getStudentCount() 
    {
        return studentCount;
    }

    public void setIsConflict(Integer isConflict) 
    {
        this.isConflict = isConflict;
    }

    public Integer getIsConflict() 
    {
        return isConflict;
    }

    public void setConflictReason(String conflictReason) 
    {
        this.conflictReason = conflictReason;
    }

    public String getConflictReason() 
    {
        return conflictReason;
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
            .append("resultId", getResultId())
            .append("taskId", getTaskId())
            .append("teacherCourseId", getTeacherCourseId())
            .append("classroomId", getClassroomId())
            .append("week", getWeek())
            .append("weekday", getWeekday())
            .append("period", getPeriod())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("studentCount", getStudentCount())
            .append("isConflict", getIsConflict())
            .append("conflictReason", getConflictReason())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
