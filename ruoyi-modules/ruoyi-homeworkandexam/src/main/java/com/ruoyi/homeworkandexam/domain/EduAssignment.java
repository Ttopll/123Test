package com.ruoyi.homeworkandexam.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 作业管理对象 edu_assignment
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAssignment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业 ID */
    private Long assignId;

    /** 课程 ID */
    @Excel(name = "课程 ID")
    private Long courseId;

    /** 教师 ID */
    @Excel(name = "教师 ID")
    private String teacherId;

    /** 作业标题 */
    @Excel(name = "作业标题")
    private String title;

    /** 作业内容（富文本） */
    @Excel(name = "作业内容", readConverterExp = "富=文本")
    private String content;

    /** 附件 URL（JSON 结构化存储） */
    @Excel(name = "附件 URL", readConverterExp = "J=SON,结=构化存储")
    private String attachUrl;

    /** 提交截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 作业类型 */
    @Excel(name = "作业类型")
    private Integer assignmentType;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setAssignId(Long assignId) 
    {
        this.assignId = assignId;
    }

    public Long getAssignId() 
    {
        return assignId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setTeacherId(String teacherId) 
    {
        this.teacherId = teacherId;
    }

    public String getTeacherId() 
    {
        return teacherId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setAttachUrl(String attachUrl) 
    {
        this.attachUrl = attachUrl;
    }

    public String getAttachUrl() 
    {
        return attachUrl;
    }

    public void setDeadline(Date deadline) 
    {
        this.deadline = deadline;
    }

    public Date getDeadline() 
    {
        return deadline;
    }

    public void setAssignmentType(Integer assignmentType) 
    {
        this.assignmentType = assignmentType;
    }

    public Integer getAssignmentType() 
    {
        return assignmentType;
    }

    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
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
            .append("assignId", getAssignId())
            .append("courseId", getCourseId())
            .append("teacherId", getTeacherId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("attachUrl", getAttachUrl())
            .append("deadline", getDeadline())
            .append("assignmentType", getAssignmentType())
            .append("totalScore", getTotalScore())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
