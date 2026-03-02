package com.ruoyi.aipaper.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 试卷对象 edu_paper
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduPaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 试卷 ID */
    private Long paperId;

    /** 试卷名称（如：《数据库原理》期中试卷） */
    @Excel(name = "试卷名称", readConverterExp = "如=：《数据库原理》期中试卷")
    private String paperName;

    /** 关联课程 ID */
    @Excel(name = "关联课程 ID")
    private Long courseId;

    /** 试卷总分 */
    @Excel(name = "试卷总分")
    private Long totalScore;

    /** 考试时长（分钟） */
    @Excel(name = "考试时长", readConverterExp = "分=钟")
    private Long examTime;

    /** 关联试题 ID（JSON 格式，如：[1,2,3]） */
    @Excel(name = "关联试题 ID", readConverterExp = "J=SON,格=式，如：[1,2,3]")
    private String questionIds;

    /** 创建人 ID */
    @Excel(name = "创建人 ID")
    private String createUserId;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer auditStatus;

    /** 审核人 ID */
    @Excel(name = "审核人 ID")
    private String auditorId;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setPaperId(Long paperId) 
    {
        this.paperId = paperId;
    }

    public Long getPaperId() 
    {
        return paperId;
    }

    public void setPaperName(String paperName) 
    {
        this.paperName = paperName;
    }

    public String getPaperName() 
    {
        return paperName;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
    }

    public void setExamTime(Long examTime) 
    {
        this.examTime = examTime;
    }

    public Long getExamTime() 
    {
        return examTime;
    }

    public void setQuestionIds(String questionIds) 
    {
        this.questionIds = questionIds;
    }

    public String getQuestionIds() 
    {
        return questionIds;
    }

    public void setCreateUserId(String createUserId) 
    {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() 
    {
        return createUserId;
    }

    public void setAuditStatus(Integer auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus() 
    {
        return auditStatus;
    }

    public void setAuditorId(String auditorId) 
    {
        this.auditorId = auditorId;
    }

    public String getAuditorId() 
    {
        return auditorId;
    }

    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
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
            .append("paperId", getPaperId())
            .append("paperName", getPaperName())
            .append("courseId", getCourseId())
            .append("totalScore", getTotalScore())
            .append("examTime", getExamTime())
            .append("questionIds", getQuestionIds())
            .append("createUserId", getCreateUserId())
            .append("auditStatus", getAuditStatus())
            .append("auditorId", getAuditorId())
            .append("auditTime", getAuditTime())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
