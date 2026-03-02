package com.ruoyi.attendance.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 考勤规则对象 edu_attendance_rule
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAttendanceRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则 ID */
    private Long ruleId;

    /** 规则名称（如：迟到判定规则） */
    @Excel(name = "规则名称", readConverterExp = "如=：迟到判定规则")
    private String ruleName;

    /** 迟到判定分钟数（10 分钟） */
    @Excel(name = "迟到判定分钟数", readConverterExp = "1=0,分=钟")
    private Long lateMinute;

    /** 缺勤判定分钟数（30 分钟） */
    @Excel(name = "缺勤判定分钟数", readConverterExp = "3=0,分=钟")
    private Long absentCondition;

    /** 早退判定分钟数（10 分钟） */
    @Excel(name = "早退判定分钟数", readConverterExp = "1=0,分=钟")
    private Long earlyLeaveMinute;

    /** 定位签到范围（100 米） */
    @Excel(name = "定位签到范围", readConverterExp = "1=00,米=")
    private Long signRange;

    /** 考勤预警阈值（3 次缺勤） */
    @Excel(name = "考勤预警阈值", readConverterExp = "3=,次=缺勤")
    private Long warningThreshold;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
    }

    public void setRuleName(String ruleName) 
    {
        this.ruleName = ruleName;
    }

    public String getRuleName() 
    {
        return ruleName;
    }

    public void setLateMinute(Long lateMinute) 
    {
        this.lateMinute = lateMinute;
    }

    public Long getLateMinute() 
    {
        return lateMinute;
    }

    public void setAbsentCondition(Long absentCondition) 
    {
        this.absentCondition = absentCondition;
    }

    public Long getAbsentCondition() 
    {
        return absentCondition;
    }

    public void setEarlyLeaveMinute(Long earlyLeaveMinute) 
    {
        this.earlyLeaveMinute = earlyLeaveMinute;
    }

    public Long getEarlyLeaveMinute() 
    {
        return earlyLeaveMinute;
    }

    public void setSignRange(Long signRange) 
    {
        this.signRange = signRange;
    }

    public Long getSignRange() 
    {
        return signRange;
    }

    public void setWarningThreshold(Long warningThreshold) 
    {
        this.warningThreshold = warningThreshold;
    }

    public Long getWarningThreshold() 
    {
        return warningThreshold;
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
            .append("ruleId", getRuleId())
            .append("ruleName", getRuleName())
            .append("lateMinute", getLateMinute())
            .append("absentCondition", getAbsentCondition())
            .append("earlyLeaveMinute", getEarlyLeaveMinute())
            .append("signRange", getSignRange())
            .append("warningThreshold", getWarningThreshold())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
