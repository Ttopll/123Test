package com.ruoyi.practice.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 实践活动过程对象 edu_practice_process
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduPracticeProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 过程记录 ID */
    private Long processId;

    /** 关联报名 ID */
    @Excel(name = "关联报名 ID")
    private Long signupId;

    /** 关联活动 ID */
    @Excel(name = "关联活动 ID")
    private String activityId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 阶段 */
    @Excel(name = "阶段")
    private Integer processStage;

    /** 阶段内容（如：实验数据、活动日志） */
    @Excel(name = "阶段内容", readConverterExp = "如=：实验数据、活动日志")
    private String stageContent;

    /** 阶段记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "阶段记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stageTime;

    /** 阶段附件 URL（如：实验报告、现场照片） */
    @Excel(name = "阶段附件 URL", readConverterExp = "如=：实验报告、现场照片")
    private String attachUrl;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }

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

    public void setProcessStage(Integer processStage) 
    {
        this.processStage = processStage;
    }

    public Integer getProcessStage() 
    {
        return processStage;
    }

    public void setStageContent(String stageContent) 
    {
        this.stageContent = stageContent;
    }

    public String getStageContent() 
    {
        return stageContent;
    }

    public void setStageTime(Date stageTime) 
    {
        this.stageTime = stageTime;
    }

    public Date getStageTime() 
    {
        return stageTime;
    }

    public void setAttachUrl(String attachUrl) 
    {
        this.attachUrl = attachUrl;
    }

    public String getAttachUrl() 
    {
        return attachUrl;
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
            .append("processId", getProcessId())
            .append("signupId", getSignupId())
            .append("activityId", getActivityId())
            .append("studentId", getStudentId())
            .append("processStage", getProcessStage())
            .append("stageContent", getStageContent())
            .append("stageTime", getStageTime())
            .append("attachUrl", getAttachUrl())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
