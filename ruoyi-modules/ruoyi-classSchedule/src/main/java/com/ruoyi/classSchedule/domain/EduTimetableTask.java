package com.ruoyi.classSchedule.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 排课任务对象 edu_timetable_task
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public class EduTimetableTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务 ID */
    private Long taskId;

    /** 任务名称（如：计算机学院 2024-1 排课） */
    @Excel(name = "任务名称", readConverterExp = "如=：计算机学院,2=024-1,排=课")
    private String taskName;

    /** 排课学期 */
    @Excel(name = "排课学期")
    private String semester;

    /** 排课院系 ID（为空表示全校） */
    @Excel(name = "排课院系 ID", readConverterExp = "为=空表示全校")
    private Long deptId;

    /** 排课年级（为空表示全年级） */
    @Excel(name = "排课年级", readConverterExp = "为=空表示全年级")
    private String grade;

    /** 排课周期开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "排课周期开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 排课周期结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "排课周期结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 关联规则 ID（逗号分隔） */
    @Excel(name = "关联规则 ID", readConverterExp = "逗=号分隔")
    private String ruleIds;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Integer taskStatus;

    /** 冲突数量 */
    @Excel(name = "冲突数量")
    private Long conflictCount;

    /** 创建人 ID */
    @Excel(name = "创建人 ID")
    private String createUserId;

    /** 执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "执行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date executeTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }

    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }

    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }

    public void setRuleIds(String ruleIds) 
    {
        this.ruleIds = ruleIds;
    }

    public String getRuleIds() 
    {
        return ruleIds;
    }

    public void setTaskStatus(Integer taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskStatus() 
    {
        return taskStatus;
    }

    public void setConflictCount(Long conflictCount) 
    {
        this.conflictCount = conflictCount;
    }

    public Long getConflictCount() 
    {
        return conflictCount;
    }

    public void setCreateUserId(String createUserId) 
    {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() 
    {
        return createUserId;
    }

    public void setExecuteTime(Date executeTime) 
    {
        this.executeTime = executeTime;
    }

    public Date getExecuteTime() 
    {
        return executeTime;
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
            .append("taskId", getTaskId())
            .append("taskName", getTaskName())
            .append("semester", getSemester())
            .append("deptId", getDeptId())
            .append("grade", getGrade())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("ruleIds", getRuleIds())
            .append("taskStatus", getTaskStatus())
            .append("conflictCount", getConflictCount())
            .append("createUserId", getCreateUserId())
            .append("executeTime", getExecuteTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
