package com.ruoyi.employ.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 就业合作企业对象 edu_employment_enterprise
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduEmploymentEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业 ID（如：COM-2024-001） */
    private String enterpriseId;

    /** 企业全称 */
    @Excel(name = "企业全称")
    private String enterpriseName;

    /** 企业简称 */
    @Excel(name = "企业简称")
    private String enterpriseShort;

    /** 所属行业（如：互联网） */
    @Excel(name = "所属行业", readConverterExp = "如=：互联网")
    private String industryType;

    /** 细分行业（如：互联网 - 人工智能） */
    @Excel(name = "细分行业", readConverterExp = "如=：互联网,-=,人=工智能")
    private String industryDetail;

    /** 企业规模 */
    @Excel(name = "企业规模")
    private Integer enterpriseScale;

    /** 所在省份 */
    @Excel(name = "所在省份")
    private String province;

    /** 所在城市 */
    @Excel(name = "所在城市")
    private String city;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 对接人姓名 */
    @Excel(name = "对接人姓名")
    private String contactPerson;

    /** 对接人电话 */
    @Excel(name = "对接人电话")
    private String contactPhone;

    /** 对接人邮箱 */
    @Excel(name = "对接人邮箱")
    private String contactEmail;

    /** 招聘状态 */
    @Excel(name = "招聘状态")
    private Integer recruitStatus;

    /** 合作等级 */
    @Excel(name = "合作等级")
    private Integer cooperationLevel;

    /** 合作状态 */
    @Excel(name = "合作状态")
    private Integer cooperationStatus;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setEnterpriseId(String enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseId() 
    {
        return enterpriseId;
    }

    public void setEnterpriseName(String enterpriseName) 
    {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() 
    {
        return enterpriseName;
    }

    public void setEnterpriseShort(String enterpriseShort) 
    {
        this.enterpriseShort = enterpriseShort;
    }

    public String getEnterpriseShort() 
    {
        return enterpriseShort;
    }

    public void setIndustryType(String industryType) 
    {
        this.industryType = industryType;
    }

    public String getIndustryType() 
    {
        return industryType;
    }

    public void setIndustryDetail(String industryDetail) 
    {
        this.industryDetail = industryDetail;
    }

    public String getIndustryDetail() 
    {
        return industryDetail;
    }

    public void setEnterpriseScale(Integer enterpriseScale) 
    {
        this.enterpriseScale = enterpriseScale;
    }

    public Integer getEnterpriseScale() 
    {
        return enterpriseScale;
    }

    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }

    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }

    public void setContactEmail(String contactEmail) 
    {
        this.contactEmail = contactEmail;
    }

    public String getContactEmail() 
    {
        return contactEmail;
    }

    public void setRecruitStatus(Integer recruitStatus) 
    {
        this.recruitStatus = recruitStatus;
    }

    public Integer getRecruitStatus() 
    {
        return recruitStatus;
    }

    public void setCooperationLevel(Integer cooperationLevel) 
    {
        this.cooperationLevel = cooperationLevel;
    }

    public Integer getCooperationLevel() 
    {
        return cooperationLevel;
    }

    public void setCooperationStatus(Integer cooperationStatus) 
    {
        this.cooperationStatus = cooperationStatus;
    }

    public Integer getCooperationStatus() 
    {
        return cooperationStatus;
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
            .append("enterpriseId", getEnterpriseId())
            .append("enterpriseName", getEnterpriseName())
            .append("enterpriseShort", getEnterpriseShort())
            .append("industryType", getIndustryType())
            .append("industryDetail", getIndustryDetail())
            .append("enterpriseScale", getEnterpriseScale())
            .append("province", getProvince())
            .append("city", getCity())
            .append("address", getAddress())
            .append("contactPerson", getContactPerson())
            .append("contactPhone", getContactPhone())
            .append("contactEmail", getContactEmail())
            .append("recruitStatus", getRecruitStatus())
            .append("cooperationLevel", getCooperationLevel())
            .append("cooperationStatus", getCooperationStatus())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
