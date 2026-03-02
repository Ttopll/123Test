package com.ruoyi.attendance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 签到设备对象 edu_signin_device
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduSigninDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备记录 ID */
    private Long deviceId;

    /** 设备唯一标识（设备 SN 码） */
    @Excel(name = "设备唯一标识", readConverterExp = "设=备,S=N,码=")
    private String deviceCode;

    /** 设备名称（如：教学楼 A101 摄像头） */
    @Excel(name = "设备名称", readConverterExp = "如=：教学楼,A=101,摄=像头")
    private String deviceName;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Integer deviceType;

    /** 设备 IP 地址 */
    @Excel(name = "设备 IP 地址")
    private String deviceIp;

    /** 设备 MAC 地址 */
    @Excel(name = "设备 MAC 地址")
    private String deviceMac;

    /** 设备物理位置 */
    @Excel(name = "设备物理位置")
    private String deviceAddr;

    /** 绑定用户 ID（如：教师绑定教室摄像头 / 学生绑定手机） */
    @Excel(name = "绑定用户 ID", readConverterExp = "如=：教师绑定教室摄像头,/=,学=生绑定手机")
    private String bindUserId;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Integer deviceStatus;

    /** 在线状态 */
    @Excel(name = "在线状态")
    private Integer onlineStatus;

    /** 最后在线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后在线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOnlineTime;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 状态（逻辑删除标识） */
    @Excel(name = "状态", readConverterExp = "逻=辑删除标识")
    private Integer isDeleted;

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setDeviceCode(String deviceCode) 
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }

    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }

    public void setDeviceType(Integer deviceType) 
    {
        this.deviceType = deviceType;
    }

    public Integer getDeviceType() 
    {
        return deviceType;
    }

    public void setDeviceIp(String deviceIp) 
    {
        this.deviceIp = deviceIp;
    }

    public String getDeviceIp() 
    {
        return deviceIp;
    }

    public void setDeviceMac(String deviceMac) 
    {
        this.deviceMac = deviceMac;
    }

    public String getDeviceMac() 
    {
        return deviceMac;
    }

    public void setDeviceAddr(String deviceAddr) 
    {
        this.deviceAddr = deviceAddr;
    }

    public String getDeviceAddr() 
    {
        return deviceAddr;
    }

    public void setBindUserId(String bindUserId) 
    {
        this.bindUserId = bindUserId;
    }

    public String getBindUserId() 
    {
        return bindUserId;
    }

    public void setDeviceStatus(Integer deviceStatus) 
    {
        this.deviceStatus = deviceStatus;
    }

    public Integer getDeviceStatus() 
    {
        return deviceStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) 
    {
        this.onlineStatus = onlineStatus;
    }

    public Integer getOnlineStatus() 
    {
        return onlineStatus;
    }

    public void setLastOnlineTime(Date lastOnlineTime) 
    {
        this.lastOnlineTime = lastOnlineTime;
    }

    public Date getLastOnlineTime() 
    {
        return lastOnlineTime;
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
            .append("deviceId", getDeviceId())
            .append("deviceCode", getDeviceCode())
            .append("deviceName", getDeviceName())
            .append("deviceType", getDeviceType())
            .append("deviceIp", getDeviceIp())
            .append("deviceMac", getDeviceMac())
            .append("deviceAddr", getDeviceAddr())
            .append("bindUserId", getBindUserId())
            .append("deviceStatus", getDeviceStatus())
            .append("onlineStatus", getOnlineStatus())
            .append("lastOnlineTime", getLastOnlineTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
