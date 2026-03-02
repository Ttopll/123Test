package com.ruoyi.affair.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 事务申请对象 edu_affair_apply
 *
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAffairApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请 ID */
    private String applyId;

    /** 事务类型 ID */
    @Excel(name = "事务类型 ID")
    private Long affairTypeId;

    /** 提交人 ID */
    @Excel(name = "提交人 ID")
    private String applyUserId;

    /** 提交人角色 */
    @Excel(name = "提交人角色")
    private String applyUserRole;

    /** 事务标题 */
    @Excel(name = "事务标题")
    private String title;

    /** 事务内容 */
    @Excel(name = "事务内容")
    private String content;

    /** 附件 URL（JSON 结构化存储） */
    @Excel(name = "附件 URL", readConverterExp = "J=SON,结=构化存储")
    private String attachUrls;

    /** 签名图片 URL（JSON 结构化存储） */
    @Excel(name = "签名图片 URL", readConverterExp = "J=SON,结=构化存储")
    private String signatureUrl;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer currentStatus;

    /** 处理人 ID */
    @Excel(name = "处理人 ID")
    private String handleUserId;

    /** 超期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "超期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;

    /** 撤回原因 */
    @Excel(name = "撤回原因")
    private String cancelReason;

    /** 撤回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "撤回时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cancelTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setApplyId(String applyId)
    {
        this.applyId = applyId;
    }

    public String getApplyId()
    {
        return applyId;
    }

    public void setAffairTypeId(Long affairTypeId)
    {
        this.affairTypeId = affairTypeId;
    }

    public Long getAffairTypeId()
    {
        return affairTypeId;
    }

    public void setApplyUserId(String applyUserId)
    {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserId()
    {
        return applyUserId;
    }

    public void setApplyUserRole(String applyUserRole)
    {
        this.applyUserRole = applyUserRole;
    }

    public String getApplyUserRole()
    {
        return applyUserRole;
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

    public void setAttachUrls(String attachUrls)
    {
        this.attachUrls = attachUrls;
    }

    public String getAttachUrls()
    {
        return attachUrls;
    }

    public void setSignatureUrl(String signatureUrl)
    {
        this.signatureUrl = signatureUrl;
    }

    public String getSignatureUrl()
    {
        return signatureUrl;
    }

    public void setApplyTime(Date applyTime)
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime()
    {
        return applyTime;
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

    public void setCurrentStatus(Integer currentStatus)
    {
        this.currentStatus = currentStatus;
    }

    public Integer getCurrentStatus()
    {
        return currentStatus;
    }

    public void setHandleUserId(String handleUserId)
    {
        this.handleUserId = handleUserId;
    }

    public String getHandleUserId()
    {
        return handleUserId;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }

    public void setCancelReason(String cancelReason)
    {
        this.cancelReason = cancelReason;
    }

    public String getCancelReason()
    {
        return cancelReason;
    }

    public void setCancelTime(Date cancelTime)
    {
        this.cancelTime = cancelTime;
    }

    public Date getCancelTime()
    {
        return cancelTime;
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
                .append("applyId", getApplyId())
                .append("affairTypeId", getAffairTypeId())
                .append("applyUserId", getApplyUserId())
                .append("applyUserRole", getApplyUserRole())
                .append("title", getTitle())
                .append("content", getContent())
                .append("attachUrls", getAttachUrls())
                .append("signatureUrl", getSignatureUrl())
                .append("applyTime", getApplyTime())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("currentStatus", getCurrentStatus())
                .append("handleUserId", getHandleUserId())
                .append("expireTime", getExpireTime())
                .append("cancelReason", getCancelReason())
                .append("cancelTime", getCancelTime())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("updateUserId", getUpdateUserId())
                .append("isDeleted", getIsDeleted())
                .toString();
    }
}
