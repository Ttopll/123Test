package com.ruoyi.homeworkandexam.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 作业提交/批改对象 edu_assignment_submit
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAssignmentSubmit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 提交 ID */
    private Long submitId;

    /** 作业 ID */
    @Excel(name = "作业 ID")
    private Long assignId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 提交内容 */
    @Excel(name = "提交内容")
    private String content;

    /** 提交文件 URL */
    @Excel(name = "提交文件 URL")
    private String fileUrl;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 批改状态 */
    @Excel(name = "批改状态")
    private Integer correctStatus;

    /** 得分 */
    @Excel(name = "得分")
    private Long score;

    /** 批改备注 */
    @Excel(name = "批改备注")
    private String correctRemark;

    /** 批改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "批改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date correctTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

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

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }

    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }

    public void setCorrectStatus(Integer correctStatus) 
    {
        this.correctStatus = correctStatus;
    }

    public Integer getCorrectStatus() 
    {
        return correctStatus;
    }

    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }

    public void setCorrectRemark(String correctRemark) 
    {
        this.correctRemark = correctRemark;
    }

    public String getCorrectRemark() 
    {
        return correctRemark;
    }

    public void setCorrectTime(Date correctTime) 
    {
        this.correctTime = correctTime;
    }

    public Date getCorrectTime() 
    {
        return correctTime;
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
            .append("submitId", getSubmitId())
            .append("assignId", getAssignId())
            .append("studentId", getStudentId())
            .append("content", getContent())
            .append("fileUrl", getFileUrl())
            .append("submitTime", getSubmitTime())
            .append("correctStatus", getCorrectStatus())
            .append("score", getScore())
            .append("correctRemark", getCorrectRemark())
            .append("correctTime", getCorrectTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
