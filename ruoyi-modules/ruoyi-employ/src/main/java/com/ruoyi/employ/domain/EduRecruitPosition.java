package com.ruoyi.employ.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 招聘岗位对象 edu_recruit_position
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public class EduRecruitPosition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 岗位ID */
    private Long positionId;

    /** 企业ID */
    @Excel(name = "企业ID")
    private Long enterpriseId;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String positionName;

    /** 薪资范围（如 "8k-12k"） */
    @Excel(name = "薪资范围", readConverterExp = "如=,'=8k-12k'")
    private String salaryRange;

    /** 细分行业 */
    @Excel(name = "细分行业")
    private String industryDetail;

    /** 岗位要求 */
    @Excel(name = "岗位要求")
    private String jobRequirement;

    /** 招聘人数 */
    @Excel(name = "招聘人数")
    private Long recruitCount;

    /** 招聘截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "招聘截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 状态（0 - 下架 / 1 - 正常） */
    @Excel(name = "状态", readConverterExp = "0=,-=,下=架,/=,1=,-=,正=常")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态（0 - 否 / 1 - 是） */
    @Excel(name = "删除状态", readConverterExp = "0=,-=,否=,/=,1=,-=,是=")
    private Integer isDeleted;

    public void setPositionId(Long positionId) 
    {
        this.positionId = positionId;
    }

    public Long getPositionId() 
    {
        return positionId;
    }

    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }

    public void setPositionName(String positionName) 
    {
        this.positionName = positionName;
    }

    public String getPositionName() 
    {
        return positionName;
    }

    public void setSalaryRange(String salaryRange) 
    {
        this.salaryRange = salaryRange;
    }

    public String getSalaryRange() 
    {
        return salaryRange;
    }

    public void setIndustryDetail(String industryDetail) 
    {
        this.industryDetail = industryDetail;
    }

    public String getIndustryDetail() 
    {
        return industryDetail;
    }

    public void setJobRequirement(String jobRequirement) 
    {
        this.jobRequirement = jobRequirement;
    }

    public String getJobRequirement() 
    {
        return jobRequirement;
    }

    public void setRecruitCount(Long recruitCount) 
    {
        this.recruitCount = recruitCount;
    }

    public Long getRecruitCount() 
    {
        return recruitCount;
    }

    public void setDeadline(Date deadline) 
    {
        this.deadline = deadline;
    }

    public Date getDeadline() 
    {
        return deadline;
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
            .append("positionId", getPositionId())
            .append("enterpriseId", getEnterpriseId())
            .append("positionName", getPositionName())
            .append("salaryRange", getSalaryRange())
            .append("industryDetail", getIndustryDetail())
            .append("jobRequirement", getJobRequirement())
            .append("recruitCount", getRecruitCount())
            .append("deadline", getDeadline())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
