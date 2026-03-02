package com.ruoyi.academic.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 预警处理对象 edu_warning_handle
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduWarningHandle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预警处理记录 ID */
    private Long handleId;

    /** 关联预警 ID */
    @Excel(name = "关联预警 ID")
    private Long warningId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 处理人 ID（教师 / 管理员） */
    @Excel(name = "处理人 ID", readConverterExp = "教=师,/=,管=理员")
    private String handlerId;

    /** 处理类型 */
    @Excel(name = "处理类型")
    private Integer handleType;

    /** 处理事项标题 */
    @Excel(name = "处理事项标题")
    private String handleTitle;

    /** 处理内容（如：辅导时间 / 沟通记录） */
    @Excel(name = "处理内容", readConverterExp = "如=：辅导时间,/=,沟=通记录")
    private String handleContent;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private Integer handleStatus;

    /** 处理记录创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理记录创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /** 处理完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

    /** 审核人 ID（预警解除需审核） */
    @Excel(name = "审核人 ID", readConverterExp = "预=警解除需审核")
    private String verifyUserId;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date verifyTime;

    /** 审核备注（如：解除 / 驳回理由） */
    @Excel(name = "审核备注", readConverterExp = "如=：解除,/=,驳=回理由")
    private String verifyRemark;

    /** 处理附件 URL（如：辅导记录单、沟通截图、审批文件） */
    @Excel(name = "处理附件 URL", readConverterExp = "如=：辅导记录单、沟通截图、审批文件")
    private String attachUrl;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private Integer isDeleted;

    public void setHandleId(Long handleId) 
    {
        this.handleId = handleId;
    }

    public Long getHandleId() 
    {
        return handleId;
    }

    public void setWarningId(Long warningId) 
    {
        this.warningId = warningId;
    }

    public Long getWarningId() 
    {
        return warningId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setHandlerId(String handlerId) 
    {
        this.handlerId = handlerId;
    }

    public String getHandlerId() 
    {
        return handlerId;
    }

    public void setHandleType(Integer handleType) 
    {
        this.handleType = handleType;
    }

    public Integer getHandleType() 
    {
        return handleType;
    }

    public void setHandleTitle(String handleTitle) 
    {
        this.handleTitle = handleTitle;
    }

    public String getHandleTitle() 
    {
        return handleTitle;
    }

    public void setHandleContent(String handleContent) 
    {
        this.handleContent = handleContent;
    }

    public String getHandleContent() 
    {
        return handleContent;
    }

    public void setHandleStatus(Integer handleStatus) 
    {
        this.handleStatus = handleStatus;
    }

    public Integer getHandleStatus() 
    {
        return handleStatus;
    }

    public void setHandleTime(Date handleTime) 
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() 
    {
        return handleTime;
    }

    public void setCompleteTime(Date completeTime) 
    {
        this.completeTime = completeTime;
    }

    public Date getCompleteTime() 
    {
        return completeTime;
    }

    public void setVerifyUserId(String verifyUserId) 
    {
        this.verifyUserId = verifyUserId;
    }

    public String getVerifyUserId() 
    {
        return verifyUserId;
    }

    public void setVerifyTime(Date verifyTime) 
    {
        this.verifyTime = verifyTime;
    }

    public Date getVerifyTime() 
    {
        return verifyTime;
    }

    public void setVerifyRemark(String verifyRemark) 
    {
        this.verifyRemark = verifyRemark;
    }

    public String getVerifyRemark() 
    {
        return verifyRemark;
    }

    public void setAttachUrl(String attachUrl) 
    {
        this.attachUrl = attachUrl;
    }

    public String getAttachUrl() 
    {
        return attachUrl;
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
            .append("handleId", getHandleId())
            .append("warningId", getWarningId())
            .append("studentId", getStudentId())
            .append("handlerId", getHandlerId())
            .append("handleType", getHandleType())
            .append("handleTitle", getHandleTitle())
            .append("handleContent", getHandleContent())
            .append("handleStatus", getHandleStatus())
            .append("handleTime", getHandleTime())
            .append("completeTime", getCompleteTime())
            .append("verifyUserId", getVerifyUserId())
            .append("verifyTime", getVerifyTime())
            .append("verifyRemark", getVerifyRemark())
            .append("attachUrl", getAttachUrl())
            .append("remark", getRemark())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
