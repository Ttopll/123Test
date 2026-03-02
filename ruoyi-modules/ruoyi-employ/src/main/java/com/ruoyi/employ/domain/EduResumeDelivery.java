package com.ruoyi.employ.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 简历投递记录对象 edu_resume_delivery
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduResumeDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 投递记录 ID */
    private Long deliveryId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 关联简历 ID */
    @Excel(name = "关联简历 ID")
    private Long resumeId;

    /** 投递企业 ID */
    @Excel(name = "投递企业 ID")
    private String enterpriseId;

    /** 投递岗位 ID */
    @Excel(name = "投递岗位 ID")
    private String positionId;

    /** 投递时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投递时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryTime;

    /** 投递状态 */
    @Excel(name = "投递状态")
    private Integer deliveryStatus;

    /** 企业反馈内容（如：面试时间、拒绝理由） */
    @Excel(name = "企业反馈内容", readConverterExp = "如=：面试时间、拒绝理由")
    private String feedbackContent;

    /** 企业反馈时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "企业反馈时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedbackTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setResumeId(Long resumeId) 
    {
        this.resumeId = resumeId;
    }

    public Long getResumeId() 
    {
        return resumeId;
    }

    public void setEnterpriseId(String enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseId() 
    {
        return enterpriseId;
    }

    public void setPositionId(String positionId) 
    {
        this.positionId = positionId;
    }

    public String getPositionId() 
    {
        return positionId;
    }

    public void setDeliveryTime(Date deliveryTime) 
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() 
    {
        return deliveryTime;
    }

    public void setDeliveryStatus(Integer deliveryStatus) 
    {
        this.deliveryStatus = deliveryStatus;
    }

    public Integer getDeliveryStatus() 
    {
        return deliveryStatus;
    }

    public void setFeedbackContent(String feedbackContent) 
    {
        this.feedbackContent = feedbackContent;
    }

    public String getFeedbackContent() 
    {
        return feedbackContent;
    }

    public void setFeedbackTime(Date feedbackTime) 
    {
        this.feedbackTime = feedbackTime;
    }

    public Date getFeedbackTime() 
    {
        return feedbackTime;
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
            .append("deliveryId", getDeliveryId())
            .append("studentId", getStudentId())
            .append("resumeId", getResumeId())
            .append("enterpriseId", getEnterpriseId())
            .append("positionId", getPositionId())
            .append("deliveryTime", getDeliveryTime())
            .append("deliveryStatus", getDeliveryStatus())
            .append("feedbackContent", getFeedbackContent())
            .append("feedbackTime", getFeedbackTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
