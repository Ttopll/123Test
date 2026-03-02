package com.ruoyi.affair.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 事务处理日志对象 edu_affair_handle_log
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAffairHandleLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志 ID */
    private Long logId;

    /** 申请 ID */
    @Excel(name = "申请 ID")
    private Long applyId;

    /** 处理人 ID */
    @Excel(name = "处理人 ID")
    private Long handleUserId;

    /** 处理人姓名 */
    @Excel(name = "处理人姓名")
    private String handleUserName;

    /** 处理人角色 */
    @Excel(name = "处理人角色")
    private String handleUserRole;

    /** 处理前状态 */
    @Excel(name = "处理前状态")
    private Integer beforeStatus;

    /** 处理后状态 */
    @Excel(name = "处理后状态")
    private Integer afterStatus;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /** 处理内容 */
    @Excel(name = "处理内容")
    private String handleContent;

    /** 处理附件 URL */
    @Excel(name = "处理附件 URL")
    private String handleAttachUrls;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setLogId(Long logId) 
    {
        this.logId = logId;
    }

    public Long getLogId() 
    {
        return logId;
    }

    public void setApplyId(Long applyId)
    {
        this.applyId = applyId;
    }

    public Long getApplyId()
    {
        return applyId;
    }

    public void setHandleUserId(Long handleUserId)
    {
        this.handleUserId = handleUserId;
    }

    public Long getHandleUserId()
    {
        return handleUserId;
    }

    public void setHandleUserName(String handleUserName) 
    {
        this.handleUserName = handleUserName;
    }

    public String getHandleUserName() 
    {
        return handleUserName;
    }

    public void setHandleUserRole(String handleUserRole) 
    {
        this.handleUserRole = handleUserRole;
    }

    public String getHandleUserRole() 
    {
        return handleUserRole;
    }

    public void setBeforeStatus(Integer beforeStatus) 
    {
        this.beforeStatus = beforeStatus;
    }

    public Integer getBeforeStatus() 
    {
        return beforeStatus;
    }

    public void setAfterStatus(Integer afterStatus) 
    {
        this.afterStatus = afterStatus;
    }

    public Integer getAfterStatus() 
    {
        return afterStatus;
    }

    public void setHandleTime(Date handleTime) 
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() 
    {
        return handleTime;
    }

    public void setHandleContent(String handleContent) 
    {
        this.handleContent = handleContent;
    }

    public String getHandleContent() 
    {
        return handleContent;
    }

    public void setHandleAttachUrls(String handleAttachUrls) 
    {
        this.handleAttachUrls = handleAttachUrls;
    }

    public String getHandleAttachUrls() 
    {
        return handleAttachUrls;
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
            .append("logId", getLogId())
            .append("applyId", getApplyId())
            .append("handleUserId", getHandleUserId())
            .append("handleUserName", getHandleUserName())
            .append("handleUserRole", getHandleUserRole())
            .append("beforeStatus", getBeforeStatus())
            .append("afterStatus", getAfterStatus())
            .append("handleTime", getHandleTime())
            .append("handleContent", getHandleContent())
            .append("handleAttachUrls", getHandleAttachUrls())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
