package com.ruoyi.evaluate.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 教师评价配置对象 edu_teacher_evaluation_config
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduTeacherEvaluationConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置 ID */
    private Long configId;

    /** 评价名称（如：2024-1 学期教师评价） */
    @Excel(name = "评价名称", readConverterExp = "如=：2024-1,学=期教师评价")
    private String evaluationName;

    /** 学期 */
    @Excel(name = "学期")
    private String semester;

    /** 评价开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评价开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 评价结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评价结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 评价发起角色（学生） */
    @Excel(name = "评价发起角色", readConverterExp = "学=生")
    private String targetRole;

    /** 被评价角色（教师） */
    @Excel(name = "被评价角色", readConverterExp = "教=师")
    private String evaluatedRole;

    /** 评价维度（JSON 格式，含权重） */
    @Excel(name = "评价维度", readConverterExp = "J=SON,格=式，含权重")
    private String evaluationDimensions;

    /** 评分规则（1 - 非常不满意～5 - 非常满意） */
    @Excel(name = "评分规则", readConverterExp = "1=,-=,非=常不满意～5,-=,非=常满意")
    private String scoreRule;

    /** 是否匿名 */
    @Excel(name = "是否匿名")
    private Integer isAnonymous;

    /** 是否需审核 */
    @Excel(name = "是否需审核")
    private Integer isAuditRequired;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 创建人 ID */
    @Excel(name = "创建人 ID")
    private String createUserId;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 状态（0 - 否 / 1 - 是） */
    @Excel(name = "状态", readConverterExp = "0=,-=,否=,/=,1=,-=,是=")
    private Integer isDeleted;

    public void setConfigId(Long configId) 
    {
        this.configId = configId;
    }

    public Long getConfigId() 
    {
        return configId;
    }

    public void setEvaluationName(String evaluationName) 
    {
        this.evaluationName = evaluationName;
    }

    public String getEvaluationName() 
    {
        return evaluationName;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
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

    public void setTargetRole(String targetRole) 
    {
        this.targetRole = targetRole;
    }

    public String getTargetRole() 
    {
        return targetRole;
    }

    public void setEvaluatedRole(String evaluatedRole) 
    {
        this.evaluatedRole = evaluatedRole;
    }

    public String getEvaluatedRole() 
    {
        return evaluatedRole;
    }

    public void setEvaluationDimensions(String evaluationDimensions) 
    {
        this.evaluationDimensions = evaluationDimensions;
    }

    public String getEvaluationDimensions() 
    {
        return evaluationDimensions;
    }

    public void setScoreRule(String scoreRule) 
    {
        this.scoreRule = scoreRule;
    }

    public String getScoreRule() 
    {
        return scoreRule;
    }

    public void setIsAnonymous(Integer isAnonymous) 
    {
        this.isAnonymous = isAnonymous;
    }

    public Integer getIsAnonymous() 
    {
        return isAnonymous;
    }

    public void setIsAuditRequired(Integer isAuditRequired) 
    {
        this.isAuditRequired = isAuditRequired;
    }

    public Integer getIsAuditRequired() 
    {
        return isAuditRequired;
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
            .append("configId", getConfigId())
            .append("evaluationName", getEvaluationName())
            .append("semester", getSemester())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("targetRole", getTargetRole())
            .append("evaluatedRole", getEvaluatedRole())
            .append("evaluationDimensions", getEvaluationDimensions())
            .append("scoreRule", getScoreRule())
            .append("isAnonymous", getIsAnonymous())
            .append("isAuditRequired", getIsAuditRequired())
            .append("status", getStatus())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .append("remark", getRemark())
            .toString();
    }
}
