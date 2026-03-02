package com.ruoyi.academic.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 学业预警对象 edu_warning
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduWarning extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预警 ID */
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

    /** 预警原因（如：数学不及格） */
    @Excel(name = "预警原因", readConverterExp = "如=：数学不及格")
    private String reason;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private Integer handleStatus;

    /** 处理人 ID */
    @Excel(name = "处理人 ID")
    private String handleUserId;

    /** 处理备注 */
    @Excel(name = "处理备注")
    private String handleRemark;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /** 审核人 ID */
    @Excel(name = "审核人 ID")
    private String verifyUserId;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date verifyTime;

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

    public void setUpdateUserId(String updateUserId) 
    {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserId() 
    {
        return updateUserId;
    }

    public void setHandleTime(Date handleTime) 
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() 
    {
        return handleTime;
    }

    public void setVerifyUserId(String verifyUserId) 
    {
        this.verifyUserId = verifyUserId;
    }

    public String getVerifyUserId() 
    {
        return verifyUserId;
    }

    public void setVerifyTime(Date verifyTime) 
    {
        this.verifyTime = verifyTime;
    }

    public Date getVerifyTime() 
    {
        return verifyTime;
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
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("handleTime", getHandleTime())
            .append("verifyUserId", getVerifyUserId())
            .append("verifyTime", getVerifyTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
