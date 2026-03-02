package com.ruoyi.aiInteraction.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 智能交互记录对象 edu_ai_interaction
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAiInteraction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 交互 ID */
    private String interactionId;

    /** 用户 ID */
    @Excel(name = "用户 ID")
    private String userId;

    /** 交互类型 */
    @Excel(name = "交互类型")
    private Integer interactionType;

    /** 交互状态 */
    @Excel(name = "交互状态")
    private Integer interactionStatus;

    /** 请求内容（问题 / 文档 URL / 图片 URL） */
    @Excel(name = "请求内容", readConverterExp = "问=题,/=,文=档,U=RL,/=,图=片,U=RL")
    private String requestContent;

    /** 响应内容 */
    @Excel(name = "响应内容")
    private String responseContent;

    /** 交互时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交互时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date interactionTime;

    /** 是否收藏 */
    @Excel(name = "是否收藏")
    private Integer isCollect;

    /** 文档解析时存储文档 URL（仅交互类型为 2 时填写） */
    @Excel(name = "文档解析时存储文档 URL", readConverterExp = "仅=交互类型为,2=,时=填写")
    private String documentUrl;

    /** 图片识别时存储图片 URL（仅交互类型为 3 时填写） */
    @Excel(name = "图片识别时存储图片 URL", readConverterExp = "仅=交互类型为,3=,时=填写")
    private String imageUrl;

    /** 系统功能引导标识（如：COURSE_SELECTION） */
    @Excel(name = "系统功能引导标识", readConverterExp = "如=：COURSE_SELECTION")
    private String guideFunction;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setInteractionId(String interactionId) 
    {
        this.interactionId = interactionId;
    }

    public String getInteractionId() 
    {
        return interactionId;
    }

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    public void setInteractionType(Integer interactionType) 
    {
        this.interactionType = interactionType;
    }

    public Integer getInteractionType() 
    {
        return interactionType;
    }

    public void setInteractionStatus(Integer interactionStatus) 
    {
        this.interactionStatus = interactionStatus;
    }

    public Integer getInteractionStatus() 
    {
        return interactionStatus;
    }

    public void setRequestContent(String requestContent) 
    {
        this.requestContent = requestContent;
    }

    public String getRequestContent() 
    {
        return requestContent;
    }

    public void setResponseContent(String responseContent) 
    {
        this.responseContent = responseContent;
    }

    public String getResponseContent() 
    {
        return responseContent;
    }

    public void setInteractionTime(Date interactionTime) 
    {
        this.interactionTime = interactionTime;
    }

    public Date getInteractionTime() 
    {
        return interactionTime;
    }

    public void setIsCollect(Integer isCollect) 
    {
        this.isCollect = isCollect;
    }

    public Integer getIsCollect() 
    {
        return isCollect;
    }

    public void setDocumentUrl(String documentUrl) 
    {
        this.documentUrl = documentUrl;
    }

    public String getDocumentUrl() 
    {
        return documentUrl;
    }

    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

    public void setGuideFunction(String guideFunction) 
    {
        this.guideFunction = guideFunction;
    }

    public String getGuideFunction() 
    {
        return guideFunction;
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
            .append("interactionId", getInteractionId())
            .append("userId", getUserId())
            .append("interactionType", getInteractionType())
            .append("interactionStatus", getInteractionStatus())
            .append("requestContent", getRequestContent())
            .append("responseContent", getResponseContent())
            .append("interactionTime", getInteractionTime())
            .append("isCollect", getIsCollect())
            .append("documentUrl", getDocumentUrl())
            .append("imageUrl", getImageUrl())
            .append("guideFunction", getGuideFunction())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
