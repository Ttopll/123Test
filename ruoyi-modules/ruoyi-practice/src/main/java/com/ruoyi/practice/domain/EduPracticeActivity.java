package com.ruoyi.practice.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 实践活动对象 edu_practice_activity
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduPracticeActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动 ID（如：ACT-2024-001） */
    private String activityId;

    /** 活动名称（如：腾讯暑期实习） */
    @Excel(name = "活动名称", readConverterExp = "如=：腾讯暑期实习")
    private String activityName;

    /** 活动类型 */
    @Excel(name = "活动类型")
    private Integer activityType;

    /** 主办方（如：就业指导中心） */
    @Excel(name = "主办方", readConverterExp = "如=：就业指导中心")
    private String organizer;

    /** 协办方 */
    @Excel(name = "协办方")
    private String coOrganizer;

    /** 活动开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activityTime;

    /** 活动结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activityEndTime;

    /** 报名开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signStartTime;

    /** 报名结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signEndTime;

    /** 活动地点 */
    @Excel(name = "活动地点")
    private String activityLocation;

    /** 最大参与人数（0 - 无限制） */
    @Excel(name = "最大参与人数", readConverterExp = "0=,-=,无=限制")
    private Long maxQuota;

    /** 面向人群（如：全体学生） */
    @Excel(name = "面向人群", readConverterExp = "如=：全体学生")
    private String targetGroup;

    /** 活动详情 */
    @Excel(name = "活动详情")
    private String activityDesc;

    /** 所需材料（如：简历） */
    @Excel(name = "所需材料", readConverterExp = "如=：简历")
    private String requiredMaterial;

    /** 活动状态 */
    @Excel(name = "活动状态")
    private Integer activityStatus;

    /** 创建人 ID */
    @Excel(name = "创建人 ID")
    private String createUserId;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setActivityId(String activityId) 
    {
        this.activityId = activityId;
    }

    public String getActivityId() 
    {
        return activityId;
    }

    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }

    public String getActivityName() 
    {
        return activityName;
    }

    public void setActivityType(Integer activityType) 
    {
        this.activityType = activityType;
    }

    public Integer getActivityType() 
    {
        return activityType;
    }

    public void setOrganizer(String organizer) 
    {
        this.organizer = organizer;
    }

    public String getOrganizer() 
    {
        return organizer;
    }

    public void setCoOrganizer(String coOrganizer) 
    {
        this.coOrganizer = coOrganizer;
    }

    public String getCoOrganizer() 
    {
        return coOrganizer;
    }

    public void setActivityTime(Date activityTime) 
    {
        this.activityTime = activityTime;
    }

    public Date getActivityTime() 
    {
        return activityTime;
    }

    public void setActivityEndTime(Date activityEndTime) 
    {
        this.activityEndTime = activityEndTime;
    }

    public Date getActivityEndTime() 
    {
        return activityEndTime;
    }

    public void setSignStartTime(Date signStartTime) 
    {
        this.signStartTime = signStartTime;
    }

    public Date getSignStartTime() 
    {
        return signStartTime;
    }

    public void setSignEndTime(Date signEndTime) 
    {
        this.signEndTime = signEndTime;
    }

    public Date getSignEndTime() 
    {
        return signEndTime;
    }

    public void setActivityLocation(String activityLocation) 
    {
        this.activityLocation = activityLocation;
    }

    public String getActivityLocation() 
    {
        return activityLocation;
    }

    public void setMaxQuota(Long maxQuota) 
    {
        this.maxQuota = maxQuota;
    }

    public Long getMaxQuota() 
    {
        return maxQuota;
    }

    public void setTargetGroup(String targetGroup) 
    {
        this.targetGroup = targetGroup;
    }

    public String getTargetGroup() 
    {
        return targetGroup;
    }

    public void setActivityDesc(String activityDesc) 
    {
        this.activityDesc = activityDesc;
    }

    public String getActivityDesc() 
    {
        return activityDesc;
    }

    public void setRequiredMaterial(String requiredMaterial) 
    {
        this.requiredMaterial = requiredMaterial;
    }

    public String getRequiredMaterial() 
    {
        return requiredMaterial;
    }

    public void setActivityStatus(Integer activityStatus) 
    {
        this.activityStatus = activityStatus;
    }

    public Integer getActivityStatus() 
    {
        return activityStatus;
    }

    public void setCreateUserId(String createUserId) 
    {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() 
    {
        return createUserId;
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
            .append("activityId", getActivityId())
            .append("activityName", getActivityName())
            .append("activityType", getActivityType())
            .append("organizer", getOrganizer())
            .append("coOrganizer", getCoOrganizer())
            .append("activityTime", getActivityTime())
            .append("activityEndTime", getActivityEndTime())
            .append("signStartTime", getSignStartTime())
            .append("signEndTime", getSignEndTime())
            .append("activityLocation", getActivityLocation())
            .append("maxQuota", getMaxQuota())
            .append("targetGroup", getTargetGroup())
            .append("activityDesc", getActivityDesc())
            .append("requiredMaterial", getRequiredMaterial())
            .append("activityStatus", getActivityStatus())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
