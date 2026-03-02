package com.ruoyi.attendance.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 考勤人脸指纹记录对象 edu_attendance_biometric
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAttendanceBiometric extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录 ID */
    private Long infoId;

    /** 用户 ID */
    @Excel(name = "用户 ID")
    private String userId;

    /** 人脸图片 URL */
    @Excel(name = "人脸图片 URL")
    private String faceImage;

    /** 指纹图片 URL */
    @Excel(name = "指纹图片 URL")
    private String fingerprintImage;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setInfoId(Long infoId) 
    {
        this.infoId = infoId;
    }

    public Long getInfoId() 
    {
        return infoId;
    }

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    public void setFaceImage(String faceImage) 
    {
        this.faceImage = faceImage;
    }

    public String getFaceImage() 
    {
        return faceImage;
    }

    public void setFingerprintImage(String fingerprintImage) 
    {
        this.fingerprintImage = fingerprintImage;
    }

    public String getFingerprintImage() 
    {
        return fingerprintImage;
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
            .append("infoId", getInfoId())
            .append("userId", getUserId())
            .append("faceImage", getFaceImage())
            .append("fingerprintImage", getFingerprintImage())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
