package com.ruoyi.academic.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 学生成长记录对象 edu_growth_record
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduGrowthRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录 ID */
    private Long recordId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 记录类型 */
    @Excel(name = "记录类型")
    private Integer recordType;

    /** 记录标题 */
    @Excel(name = "记录标题")
    private String recordTitle;

    /** 记录详情 */
    @Excel(name = "记录详情")
    private String recordContent;

    /** 相关时间（获奖 / 参与时间） */
    @Excel(name = "相关时间", readConverterExp = "获=奖,/=,参=与时间")
    private Date relatedTime;

    /** 附件 URL（证书 / 证明） */
    @Excel(name = "附件 URL", readConverterExp = "证=书,/=,证=明")
    private String attachUrl;

    /** 成长趋势标签 */
    @Excel(name = "成长趋势标签")
    private String growthTrend;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setRecordType(Integer recordType) 
    {
        this.recordType = recordType;
    }

    public Integer getRecordType() 
    {
        return recordType;
    }

    public void setRecordTitle(String recordTitle) 
    {
        this.recordTitle = recordTitle;
    }

    public String getRecordTitle() 
    {
        return recordTitle;
    }

    public void setRecordContent(String recordContent) 
    {
        this.recordContent = recordContent;
    }

    public String getRecordContent() 
    {
        return recordContent;
    }

    public void setRelatedTime(Date relatedTime) 
    {
        this.relatedTime = relatedTime;
    }

    public Date getRelatedTime() 
    {
        return relatedTime;
    }

    public void setAttachUrl(String attachUrl) 
    {
        this.attachUrl = attachUrl;
    }

    public String getAttachUrl() 
    {
        return attachUrl;
    }

    public void setGrowthTrend(String growthTrend) 
    {
        this.growthTrend = growthTrend;
    }

    public String getGrowthTrend() 
    {
        return growthTrend;
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
            .append("recordId", getRecordId())
            .append("studentId", getStudentId())
            .append("recordType", getRecordType())
            .append("recordTitle", getRecordTitle())
            .append("recordContent", getRecordContent())
            .append("relatedTime", getRelatedTime())
            .append("attachUrl", getAttachUrl())
            .append("growthTrend", getGrowthTrend())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
