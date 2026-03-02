package com.ruoyi.classSchedule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 排课规则对象 edu_timetable_rule
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public class EduTimetableRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则 ID */
    private Long ruleId;

    /** 规则名称（如：教师时间冲突规避） */
    @Excel(name = "规则名称", readConverterExp = "如=：教师时间冲突规避")
    private String ruleName;

    /** 规则类型 */
    @Excel(name = "规则类型")
    private Integer ruleType;

    /** 规则配置（JSON，如：教师时间冲突 = true） */
    @Excel(name = "规则配置", readConverterExp = "J=SON，如：教师时间冲突,==,t=rue")
    private String ruleContent;

    /** 规则说明 */
    @Excel(name = "规则说明")
    private String ruleDesc;

    /** 适用学期 */
    @Excel(name = "适用学期")
    private String semester;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 创建人 ID */
    @Excel(name = "创建人 ID")
    private String createUserId;

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

    public void setRuleType(Integer ruleType) 
    {
        this.ruleType = ruleType;
    }

    public Integer getRuleType() 
    {
        return ruleType;
    }

    public void setRuleContent(String ruleContent) 
    {
        this.ruleContent = ruleContent;
    }

    public String getRuleContent() 
    {
        return ruleContent;
    }

    public void setRuleDesc(String ruleDesc) 
    {
        this.ruleDesc = ruleDesc;
    }

    public String getRuleDesc() 
    {
        return ruleDesc;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
            .append("ruleId", getRuleId())
            .append("ruleName", getRuleName())
            .append("ruleType", getRuleType())
            .append("ruleContent", getRuleContent())
            .append("ruleDesc", getRuleDesc())
            .append("semester", getSemester())
            .append("status", getStatus())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
