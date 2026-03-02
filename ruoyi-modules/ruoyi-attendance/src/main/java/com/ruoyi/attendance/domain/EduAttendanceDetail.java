package com.ruoyi.attendance.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 考勤详情对象 edu_attendance_detail
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduAttendanceDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 详情 ID */
    private Long detailId;

    /** 考勤 ID */
    @Excel(name = "考勤 ID")
    private Long attendId;

    /** 签到方式 */
    @Excel(name = "签到方式")
    private Integer signType;

    /** 签到位置 */
    @Excel(name = "签到位置")
    private String location;

    /** 人脸图片 URL */
    @Excel(name = "人脸图片 URL")
    private String faceImage;

    /** 指纹图片 URL */
    @Excel(name = "指纹图片 URL")
    private String fingerprintImage;

    /** 二维码标识 */
    @Excel(name = "二维码标识")
    private String qrCode;

    /** 设备信息（如设备型号、系统版本等） */
    @Excel(name = "设备信息", readConverterExp = "如=设备型号、系统版本等")
    private String deviceInfo;

    /** 更新用户（关联 sys_user 的 user_id） */
    @Excel(name = "更新用户", readConverterExp = "关=联,s=ys_user,的=,u=ser_id")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }

    public void setAttendId(Long attendId) 
    {
        this.attendId = attendId;
    }

    public Long getAttendId() 
    {
        return attendId;
    }

    public void setSignType(Integer signType) 
    {
        this.signType = signType;
    }

    public Integer getSignType() 
    {
        return signType;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
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

    public void setQrCode(String qrCode) 
    {
        this.qrCode = qrCode;
    }

    public String getQrCode() 
    {
        return qrCode;
    }

    public void setDeviceInfo(String deviceInfo) 
    {
        this.deviceInfo = deviceInfo;
    }

    public String getDeviceInfo() 
    {
        return deviceInfo;
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
            .append("detailId", getDetailId())
            .append("attendId", getAttendId())
            .append("signType", getSignType())
            .append("location", getLocation())
            .append("faceImage", getFaceImage())
            .append("fingerprintImage", getFingerprintImage())
            .append("qrCode", getQrCode())
            .append("deviceInfo", getDeviceInfo())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
