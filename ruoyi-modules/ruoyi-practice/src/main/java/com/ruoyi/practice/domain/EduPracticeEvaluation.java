package com.ruoyi.practice.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 实践活动评价对象 edu_practice_evaluation
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduPracticeEvaluation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价 ID */
    private Long evaluationId;

    /** 活动 ID */
    @Excel(name = "活动 ID")
    private String activityId;

    /** 报名 ID */
    @Excel(name = "报名 ID")
    private Long signupId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 实践结果 */
    @Excel(name = "实践结果")
    private String practiveResult;

    /** 评价类型 */
    @Excel(name = "评价类型")
    private Integer evaluationType;

    /** 评分（1-5 分） */
    @Excel(name = "评分", readConverterExp = "1=-5,分=")
    private Long score;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String evaluationContent;

    /** 评价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评价时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date evaluationTime;

    /** 评价人 ID */
    @Excel(name = "评价人 ID")
    private String createUserId;

    /** 是否匿名 */
    @Excel(name = "是否匿名")
    private Integer isAnonymous;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String replyContent;

    /** 回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date replyTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setEvaluationId(Long evaluationId) 
    {
        this.evaluationId = evaluationId;
    }

    public Long getEvaluationId() 
    {
        return evaluationId;
    }

    public void setActivityId(String activityId) 
    {
        this.activityId = activityId;
    }

    public String getActivityId() 
    {
        return activityId;
    }

    public void setSignupId(Long signupId) 
    {
        this.signupId = signupId;
    }

    public Long getSignupId() 
    {
        return signupId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }

    public void setPractiveResult(String practiveResult) 
    {
        this.practiveResult = practiveResult;
    }

    public String getPractiveResult() 
    {
        return practiveResult;
    }

    public void setEvaluationType(Integer evaluationType) 
    {
        this.evaluationType = evaluationType;
    }

    public Integer getEvaluationType() 
    {
        return evaluationType;
    }

    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
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

    public void setCreateUserId(String createUserId) 
    {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() 
    {
        return createUserId;
    }

    public void setIsAnonymous(Integer isAnonymous) 
    {
        this.isAnonymous = isAnonymous;
    }

    public Integer getIsAnonymous() 
    {
        return isAnonymous;
    }

    public void setReplyContent(String replyContent) 
    {
        this.replyContent = replyContent;
    }

    public String getReplyContent() 
    {
        return replyContent;
    }

    public void setReplyTime(Date replyTime) 
    {
        this.replyTime = replyTime;
    }

    public Date getReplyTime() 
    {
        return replyTime;
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
            .append("evaluationId", getEvaluationId())
            .append("activityId", getActivityId())
            .append("signupId", getSignupId())
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("practiveResult", getPractiveResult())
            .append("evaluationType", getEvaluationType())
            .append("score", getScore())
            .append("evaluationContent", getEvaluationContent())
            .append("evaluationTime", getEvaluationTime())
            .append("createUserId", getCreateUserId())
            .append("isAnonymous", getIsAnonymous())
            .append("replyContent", getReplyContent())
            .append("replyTime", getReplyTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
