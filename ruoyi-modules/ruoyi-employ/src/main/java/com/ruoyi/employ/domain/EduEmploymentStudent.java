package com.ruoyi.employ.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 学生就业信息对象 edu_employment_student
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduEmploymentStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 就业 ID */
    private Long employmentId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 专业 ID */
    @Excel(name = "专业 ID")
    private Long majorId;

    /** 投递 ID */
    @Excel(name = "投递 ID")
    private Long deliveryId;

    /** 毕业年份（如：2024） */
    @Excel(name = "毕业年份", readConverterExp = "如=：2024")
    private Long graduationYear;

    /** 就业状态 */
    @Excel(name = "就业状态")
    private Integer employmentStatus;

    /** 就业企业 ID */
    @Excel(name = "就业企业 ID")
    private String enterpriseId;

    /** 就业岗位 */
    @Excel(name = "就业岗位")
    private String position;

    /** 工作城市 */
    @Excel(name = "工作城市")
    private String workCity;

    /** 月薪（元） */
    @Excel(name = "月薪", readConverterExp = "元=")
    private BigDecimal salary;

    /** 合同类型 */
    @Excel(name = "合同类型")
    private Integer contractType;

    /** 合同开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractStart;

    /** 合同结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractEnd;

    /** 收到 offer 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收到 offer 时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date offerTime;

    /** 就业渠道（如：校招 / 网申） */
    @Excel(name = "就业渠道", readConverterExp = "如=：校招,/=,网=申")
    private String employmentSource;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 逻辑删除状态 */
    @Excel(name = "逻辑删除状态")
    private Integer isDeleted;

    public void setEmploymentId(Long employmentId) 
    {
        this.employmentId = employmentId;
    }

    public Long getEmploymentId() 
    {
        return employmentId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setMajorId(Long majorId) 
    {
        this.majorId = majorId;
    }

    public Long getMajorId() 
    {
        return majorId;
    }

    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }

    public void setGraduationYear(Long graduationYear) 
    {
        this.graduationYear = graduationYear;
    }

    public Long getGraduationYear() 
    {
        return graduationYear;
    }

    public void setEmploymentStatus(Integer employmentStatus) 
    {
        this.employmentStatus = employmentStatus;
    }

    public Integer getEmploymentStatus() 
    {
        return employmentStatus;
    }

    public void setEnterpriseId(String enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseId() 
    {
        return enterpriseId;
    }

    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }

    public void setWorkCity(String workCity) 
    {
        this.workCity = workCity;
    }

    public String getWorkCity() 
    {
        return workCity;
    }

    public void setSalary(BigDecimal salary) 
    {
        this.salary = salary;
    }

    public BigDecimal getSalary() 
    {
        return salary;
    }

    public void setContractType(Integer contractType) 
    {
        this.contractType = contractType;
    }

    public Integer getContractType() 
    {
        return contractType;
    }

    public void setContractStart(Date contractStart) 
    {
        this.contractStart = contractStart;
    }

    public Date getContractStart() 
    {
        return contractStart;
    }

    public void setContractEnd(Date contractEnd) 
    {
        this.contractEnd = contractEnd;
    }

    public Date getContractEnd() 
    {
        return contractEnd;
    }

    public void setOfferTime(Date offerTime) 
    {
        this.offerTime = offerTime;
    }

    public Date getOfferTime() 
    {
        return offerTime;
    }

    public void setEmploymentSource(String employmentSource) 
    {
        this.employmentSource = employmentSource;
    }

    public String getEmploymentSource() 
    {
        return employmentSource;
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
            .append("employmentId", getEmploymentId())
            .append("studentId", getStudentId())
            .append("majorId", getMajorId())
            .append("deliveryId", getDeliveryId())
            .append("graduationYear", getGraduationYear())
            .append("employmentStatus", getEmploymentStatus())
            .append("enterpriseId", getEnterpriseId())
            .append("position", getPosition())
            .append("workCity", getWorkCity())
            .append("salary", getSalary())
            .append("contractType", getContractType())
            .append("contractStart", getContractStart())
            .append("contractEnd", getContractEnd())
            .append("offerTime", getOfferTime())
            .append("employmentSource", getEmploymentSource())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
