package com.ruoyi.affair.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用户当前状态记录（全局可查）对象 edu_affair_user_current_status
 * 
 * @author ruoyi
 * @date 2026-01-28
 */
public class EduAffairUserCurrentStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增唯一主键 */
    private Long currentStatusId;

    /** 用户ID（关联sys_user.user_id） */
    @Excel(name = "用户ID", readConverterExp = "关=联sys_user.user_id")
    private Long userId;

    /** 当前状态类型（0-正常上班/1-请假/2-调休/3-出差/4-外勤，可扩展） */
    @Excel(name = "当前状态类型", readConverterExp = "0=-正常上班/1-请假/2-调休/3-出差/4-外勤，可扩展")
    private Integer statusType;

    /** 状态描述（如：事假（3天）、调休（1天）、出差（北京）） */
    @Excel(name = "状态描述", readConverterExp = "如=：事假（3天")
    private String statusDesc;

    /** 状态开始时间（如请假开始时间） */
    @Excel(name = "状态开始时间", readConverterExp = "如=请假开始时间")
    private Date startTime;

    /** 状态结束时间（如请假结束时间，正常状态为NULL） */
    @Excel(name = "状态结束时间", readConverterExp = "如=请假结束时间，正常状态为NULL")
    private Date endTime;

    /** 状态来源申请ID（关联edu_affair_apply.apply_id，追溯状态来源） */
    @Excel(name = "状态来源申请ID", readConverterExp = "关=联edu_affair_apply.apply_id，追溯状态来源")
    private Long sourceApplyId;

    /** 状态更新人ID（谁修改的状态） */
    @Excel(name = "状态更新人ID", readConverterExp = "谁=修改的状态")
    private Long updateUserId;

    /** 状态更新人姓名（冗余，避免关联查询） */
    @Excel(name = "状态更新人姓名", readConverterExp = "冗=余，避免关联查询")
    private String updateUserName;

    public void setCurrentStatusId(Long currentStatusId) 
    {
        this.currentStatusId = currentStatusId;
    }

    public Long getCurrentStatusId() 
    {
        return currentStatusId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setStatusType(Integer statusType) 
    {
        this.statusType = statusType;
    }

    public Integer getStatusType() 
    {
        return statusType;
    }

    public void setStatusDesc(String statusDesc) 
    {
        this.statusDesc = statusDesc;
    }

    public String getStatusDesc() 
    {
        return statusDesc;
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

    public void setSourceApplyId(Long sourceApplyId) 
    {
        this.sourceApplyId = sourceApplyId;
    }

    public Long getSourceApplyId() 
    {
        return sourceApplyId;
    }

    public void setUpdateUserId(Long updateUserId) 
    {
        this.updateUserId = updateUserId;
    }

    public Long getUpdateUserId() 
    {
        return updateUserId;
    }

    public void setUpdateUserName(String updateUserName) 
    {
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserName() 
    {
        return updateUserName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("currentStatusId", getCurrentStatusId())
            .append("userId", getUserId())
            .append("statusType", getStatusType())
            .append("statusDesc", getStatusDesc())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("sourceApplyId", getSourceApplyId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("updateUserName", getUpdateUserName())
            .toString();
    }
}
