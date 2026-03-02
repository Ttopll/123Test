package com.ruoyi.practice.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 实践活动报名对象 edu_practice_signup
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduPracticeSignup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报名 ID */
    private Long signupId;

    /** 活动 ID */
    @Excel(name = "活动 ID")
    private String activityId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 报名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signupTime;

    /** 报名材料 URL */
    @Excel(name = "报名材料 URL")
    private String materialUrls;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer auditStatus;

    /** 审核人 ID */
    @Excel(name = "审核人 ID")
    private String auditUserId;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String auditRemark;

    /** 参与状态 */
    @Excel(name = "参与状态")
    private Integer participateStatus;

    /** 签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signinTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setSignupId(Long signupId) 
    {
        this.signupId = signupId;
    }

    public Long getSignupId() 
    {
        return signupId;
    }

    public void setActivityId(String activityId) 
    {
        this.activityId = activityId;
    }

    public String getActivityId() 
    {
        return activityId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setSignupTime(Date signupTime) 
    {
        this.signupTime = signupTime;
    }

    public Date getSignupTime() 
    {
        return signupTime;
    }

    public void setMaterialUrls(String materialUrls) 
    {
        this.materialUrls = materialUrls;
    }

    public String getMaterialUrls() 
    {
        return materialUrls;
    }

    public void setAuditStatus(Integer auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus() 
    {
        return auditStatus;
    }

    public void setAuditUserId(String auditUserId) 
    {
        this.auditUserId = auditUserId;
    }

    public String getAuditUserId() 
    {
        return auditUserId;
    }

    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }

    public void setAuditRemark(String auditRemark) 
    {
        this.auditRemark = auditRemark;
    }

    public String getAuditRemark() 
    {
        return auditRemark;
    }

    public void setParticipateStatus(Integer participateStatus) 
    {
        this.participateStatus = participateStatus;
    }

    public Integer getParticipateStatus() 
    {
        return participateStatus;
    }

    public void setSigninTime(Date signinTime) 
    {
        this.signinTime = signinTime;
    }

    public Date getSigninTime() 
    {
        return signinTime;
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
            .append("signupId", getSignupId())
            .append("activityId", getActivityId())
            .append("studentId", getStudentId())
            .append("signupTime", getSignupTime())
            .append("materialUrls", getMaterialUrls())
            .append("auditStatus", getAuditStatus())
            .append("auditUserId", getAuditUserId())
            .append("auditTime", getAuditTime())
            .append("auditRemark", getAuditRemark())
            .append("participateStatus", getParticipateStatus())
            .append("signinTime", getSigninTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
