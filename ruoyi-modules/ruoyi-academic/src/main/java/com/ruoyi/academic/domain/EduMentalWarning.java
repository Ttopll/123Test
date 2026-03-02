package com.ruoyi.academic.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 心理健康预警对象 edu_mental_warning
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduMentalWarning extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 心理预警 ID */
    private Long warningId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 预警类型 */
    @Excel(name = "预警类型")
    private Integer warningType;

    /** 预警级别 */
    @Excel(name = "预警级别")
    private Integer warningLevel;

    /** 预警原因（如：抑郁） */
    @Excel(name = "预警原因", readConverterExp = "如=：抑郁")
    private String reason;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private Integer handleStatus;

    /** 心理辅导人 ID */
    @Excel(name = "心理辅导人 ID")
    private String handleUserId;

    /** 辅导记录 */
    @Excel(name = "辅导记录")
    private String handleRemark;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setWarningId(Long warningId) 
    {
        this.warningId = warningId;
    }

    public Long getWarningId() 
    {
        return warningId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setWarningType(Integer warningType) 
    {
        this.warningType = warningType;
    }

    public Integer getWarningType() 
    {
        return warningType;
    }

    public void setWarningLevel(Integer warningLevel) 
    {
        this.warningLevel = warningLevel;
    }

    public Integer getWarningLevel() 
    {
        return warningLevel;
    }

    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }

    public void setHandleStatus(Integer handleStatus) 
    {
        this.handleStatus = handleStatus;
    }

    public Integer getHandleStatus() 
    {
        return handleStatus;
    }

    public void setHandleUserId(String handleUserId) 
    {
        this.handleUserId = handleUserId;
    }

    public String getHandleUserId() 
    {
        return handleUserId;
    }

    public void setHandleRemark(String handleRemark) 
    {
        this.handleRemark = handleRemark;
    }

    public String getHandleRemark() 
    {
        return handleRemark;
    }

    public void setHandleTime(Date handleTime) 
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() 
    {
        return handleTime;
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
            .append("warningId", getWarningId())
            .append("studentId", getStudentId())
            .append("warningType", getWarningType())
            .append("warningLevel", getWarningLevel())
            .append("reason", getReason())
            .append("handleStatus", getHandleStatus())
            .append("handleUserId", getHandleUserId())
            .append("handleRemark", getHandleRemark())
            .append("createTime", getCreateTime())
            .append("handleTime", getHandleTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
