package com.ruoyi.homeworkandexam.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * AI 批改记录对象 edu_ai_correction
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAiCorrection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** AI 批改记录 ID */
    private Long correctionId;

    /** 关联作业提交 ID */
    @Excel(name = "关联作业提交 ID")
    private Long submitId;

    /** 关联作业 ID */
    @Excel(name = "关联作业 ID")
    private Long assignId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 授课教师 ID */
    @Excel(name = "授课教师 ID")
    private String teacherId;

    /** AI 自动评分（0 - 100 分） */
    @Excel(name = "AI 自动评分", readConverterExp = "0=,-=,1=00,分=")
    private Long aiScore;

    /** AI 批改详情（含错误标注、批改意见、得分依据） */
    @Excel(name = "AI 批改详情", readConverterExp = "含=错误标注、批改意见、得分依据")
    private String aiCorrectionContent;

    /** 教师人工修正后得分（未修正则为 NULL） */
    @Excel(name = "教师人工修正后得分", readConverterExp = "未=修正则为,N=ULL")
    private Long manualScore;

    /** 教师人工修正详情（补充批改意见、修正 AI 错误） */
    @Excel(name = "教师人工修正详情", readConverterExp = "补=充批改意见、修正,A=I,错=误")
    private String manualCorrectionContent;

    /** AI 批改与人工批改相似度（0.00 - 100.00，仅人工修正后计算） */
    @Excel(name = "AI 批改与人工批改相似度", readConverterExp = "0=.00,-=,1=00.00，仅人工修正后计算")
    private BigDecimal similarityRate;

    /** 批改状态 */
    @Excel(name = "批改状态")
    private Integer correctionStatus;

    /** AI 批改完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "AI 批改完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date correctionTime;

    /** 人工修正完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "人工修正完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date manualTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 状态（逻辑删除标识） */
    @Excel(name = "状态", readConverterExp = "逻=辑删除标识")
    private Integer isDeleted;

    public void setCorrectionId(Long correctionId) 
    {
        this.correctionId = correctionId;
    }

    public Long getCorrectionId() 
    {
        return correctionId;
    }

    public void setSubmitId(Long submitId) 
    {
        this.submitId = submitId;
    }

    public Long getSubmitId() 
    {
        return submitId;
    }

    public void setAssignId(Long assignId) 
    {
        this.assignId = assignId;
    }

    public Long getAssignId() 
    {
        return assignId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setTeacherId(String teacherId) 
    {
        this.teacherId = teacherId;
    }

    public String getTeacherId() 
    {
        return teacherId;
    }

    public void setAiScore(Long aiScore) 
    {
        this.aiScore = aiScore;
    }

    public Long getAiScore() 
    {
        return aiScore;
    }

    public void setAiCorrectionContent(String aiCorrectionContent) 
    {
        this.aiCorrectionContent = aiCorrectionContent;
    }

    public String getAiCorrectionContent() 
    {
        return aiCorrectionContent;
    }

    public void setManualScore(Long manualScore) 
    {
        this.manualScore = manualScore;
    }

    public Long getManualScore() 
    {
        return manualScore;
    }

    public void setManualCorrectionContent(String manualCorrectionContent) 
    {
        this.manualCorrectionContent = manualCorrectionContent;
    }

    public String getManualCorrectionContent() 
    {
        return manualCorrectionContent;
    }

    public void setSimilarityRate(BigDecimal similarityRate) 
    {
        this.similarityRate = similarityRate;
    }

    public BigDecimal getSimilarityRate() 
    {
        return similarityRate;
    }

    public void setCorrectionStatus(Integer correctionStatus) 
    {
        this.correctionStatus = correctionStatus;
    }

    public Integer getCorrectionStatus() 
    {
        return correctionStatus;
    }

    public void setCorrectionTime(Date correctionTime) 
    {
        this.correctionTime = correctionTime;
    }

    public Date getCorrectionTime() 
    {
        return correctionTime;
    }

    public void setManualTime(Date manualTime) 
    {
        this.manualTime = manualTime;
    }

    public Date getManualTime() 
    {
        return manualTime;
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
            .append("correctionId", getCorrectionId())
            .append("submitId", getSubmitId())
            .append("assignId", getAssignId())
            .append("studentId", getStudentId())
            .append("teacherId", getTeacherId())
            .append("aiScore", getAiScore())
            .append("aiCorrectionContent", getAiCorrectionContent())
            .append("manualScore", getManualScore())
            .append("manualCorrectionContent", getManualCorrectionContent())
            .append("similarityRate", getSimilarityRate())
            .append("correctionStatus", getCorrectionStatus())
            .append("correctionTime", getCorrectionTime())
            .append("manualTime", getManualTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
