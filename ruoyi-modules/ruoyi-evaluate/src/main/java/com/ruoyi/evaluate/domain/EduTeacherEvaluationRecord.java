package com.ruoyi.evaluate.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 教师评价记录对象 edu_teacher_evaluation_record
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduTeacherEvaluationRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价 ID */
    private String recordId;

    /** 配置 ID */
    @Excel(name = "配置 ID")
    private Long configId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 学生与课程绑定 ID */
    @Excel(name = "学生与课程绑定 ID")
    private String scId;

    /** 教师 ID */
    @Excel(name = "教师 ID")
    private String teacherId;

    /** 课程 ID */
    @Excel(name = "课程 ID")
    private Long courseId;

    /** 各维度评分（JSON） */
    @Excel(name = "各维度评分", readConverterExp = "J=SON")
    private String dimensionScores;

    /** 综合评分（按权重计算） */
    @Excel(name = "综合评分", readConverterExp = "按=权重计算")
    private BigDecimal comprehensiveScore;

    /** 文字评价 */
    @Excel(name = "文字评价")
    private String evaluationContent;

    /** 评价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评价时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date evaluationTime;

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

    /** 记录有效性 */
    @Excel(name = "记录有效性")
    private Integer isValid;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setRecordId(String recordId) 
    {
        this.recordId = recordId;
    }

    public String getRecordId() 
    {
        return recordId;
    }

    public void setConfigId(Long configId) 
    {
        this.configId = configId;
    }

    public Long getConfigId() 
    {
        return configId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setScId(String scId) 
    {
        this.scId = scId;
    }

    public String getScId() 
    {
        return scId;
    }

    public void setTeacherId(String teacherId) 
    {
        this.teacherId = teacherId;
    }

    public String getTeacherId() 
    {
        return teacherId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setDimensionScores(String dimensionScores) 
    {
        this.dimensionScores = dimensionScores;
    }

    public String getDimensionScores() 
    {
        return dimensionScores;
    }

    public void setComprehensiveScore(BigDecimal comprehensiveScore) 
    {
        this.comprehensiveScore = comprehensiveScore;
    }

    public BigDecimal getComprehensiveScore() 
    {
        return comprehensiveScore;
    }

    public void setEvaluationContent(String evaluationContent) 
    {
        this.evaluationContent = evaluationContent;
    }

    public String getEvaluationContent() 
    {
        return evaluationContent;
    }

    public void setEvaluationTime(Date evaluationTime) 
    {
        this.evaluationTime = evaluationTime;
    }

    public Date getEvaluationTime() 
    {
        return evaluationTime;
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

    public void setIsValid(Integer isValid) 
    {
        this.isValid = isValid;
    }

    public Integer getIsValid() 
    {
        return isValid;
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
            .append("recordId", getRecordId())
            .append("configId", getConfigId())
            .append("studentId", getStudentId())
            .append("scId", getScId())
            .append("teacherId", getTeacherId())
            .append("courseId", getCourseId())
            .append("dimensionScores", getDimensionScores())
            .append("comprehensiveScore", getComprehensiveScore())
            .append("evaluationContent", getEvaluationContent())
            .append("evaluationTime", getEvaluationTime())
            .append("auditStatus", getAuditStatus())
            .append("auditUserId", getAuditUserId())
            .append("auditTime", getAuditTime())
            .append("auditRemark", getAuditRemark())
            .append("isValid", getIsValid())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
