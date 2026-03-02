package com.ruoyi.classSchedule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 教室资源对象 edu_classroom
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public class EduClassroom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long classroomId;

    /** 教室名称（如：第一教学楼 101 室） */
    @Excel(name = "教室名称", readConverterExp = "如=：第一教学楼,1=01,室=")
    private String classroomName;

    /** 所属楼宇（如：第一教学楼） */
    @Excel(name = "所属楼宇", readConverterExp = "如=：第一教学楼")
    private String building;

    /** 所在楼层 */
    @Excel(name = "所在楼层")
    private Long floor;

    /** 教室容量（人数） */
    @Excel(name = "教室容量", readConverterExp = "人=数")
    private Long capacity;

    /** 教室类型 */
    @Excel(name = "教室类型")
    private Integer classroomType;

    /** 配备设备（如：投影仪） */
    @Excel(name = "配备设备", readConverterExp = "如=：投影仪")
    private String equipment;

    /** 可用状态 */
    @Excel(name = "可用状态")
    private Integer availableStatus;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setClassroomId(Long classroomId) 
    {
        this.classroomId = classroomId;
    }

    public Long getClassroomId() 
    {
        return classroomId;
    }

    public void setClassroomName(String classroomName) 
    {
        this.classroomName = classroomName;
    }

    public String getClassroomName() 
    {
        return classroomName;
    }

    public void setBuilding(String building) 
    {
        this.building = building;
    }

    public String getBuilding() 
    {
        return building;
    }

    public void setFloor(Long floor) 
    {
        this.floor = floor;
    }

    public Long getFloor() 
    {
        return floor;
    }

    public void setCapacity(Long capacity) 
    {
        this.capacity = capacity;
    }

    public Long getCapacity() 
    {
        return capacity;
    }

    public void setClassroomType(Integer classroomType) 
    {
        this.classroomType = classroomType;
    }

    public Integer getClassroomType() 
    {
        return classroomType;
    }

    public void setEquipment(String equipment) 
    {
        this.equipment = equipment;
    }

    public String getEquipment() 
    {
        return equipment;
    }

    public void setAvailableStatus(Integer availableStatus) 
    {
        this.availableStatus = availableStatus;
    }

    public Integer getAvailableStatus() 
    {
        return availableStatus;
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
            .append("classroomId", getClassroomId())
            .append("classroomName", getClassroomName())
            .append("building", getBuilding())
            .append("floor", getFloor())
            .append("capacity", getCapacity())
            .append("classroomType", getClassroomType())
            .append("equipment", getEquipment())
            .append("availableStatus", getAvailableStatus())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
