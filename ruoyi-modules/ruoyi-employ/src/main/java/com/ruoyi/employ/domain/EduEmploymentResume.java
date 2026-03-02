package com.ruoyi.employ.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 学生简历管理对象 edu_employment_resume
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduEmploymentResume extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 简历 ID */
    private Long resumeId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 简历标题（如：张三 - 技术岗简历） */
    @Excel(name = "简历标题", readConverterExp = "如=：张三,-=,技=术岗简历")
    private String resumeTitle;

    /** 简历类型 */
    @Excel(name = "简历类型")
    private Integer resumeType;

    /** 教育背景 */
    @Excel(name = "教育背景")
    private String educationBg;

    /** 工作 / 实习经历 */
    @Excel(name = "工作 / 实习经历")
    private String workExp;

    /** 项目经历 */
    @Excel(name = "项目经历")
    private String projectExp;

    /** 技能特长 */
    @Excel(name = "技能特长")
    private String skill;

    /** 荣誉奖项 */
    @Excel(name = "荣誉奖项")
    private String honor;

    /** 简历文件 URL */
    @Excel(name = "简历文件 URL")
    private String resumeFileUrl;

    /** 文件大小（MB） */
    @Excel(name = "文件大小", readConverterExp = "M=B")
    private BigDecimal fileSize;

    /** 是否默认简历 */
    @Excel(name = "是否默认简历")
    private Integer isDefault;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setResumeId(Long resumeId) 
    {
        this.resumeId = resumeId;
    }

    public Long getResumeId() 
    {
        return resumeId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setResumeTitle(String resumeTitle) 
    {
        this.resumeTitle = resumeTitle;
    }

    public String getResumeTitle() 
    {
        return resumeTitle;
    }

    public void setResumeType(Integer resumeType) 
    {
        this.resumeType = resumeType;
    }

    public Integer getResumeType() 
    {
        return resumeType;
    }

    public void setEducationBg(String educationBg) 
    {
        this.educationBg = educationBg;
    }

    public String getEducationBg() 
    {
        return educationBg;
    }

    public void setWorkExp(String workExp) 
    {
        this.workExp = workExp;
    }

    public String getWorkExp() 
    {
        return workExp;
    }

    public void setProjectExp(String projectExp) 
    {
        this.projectExp = projectExp;
    }

    public String getProjectExp() 
    {
        return projectExp;
    }

    public void setSkill(String skill) 
    {
        this.skill = skill;
    }

    public String getSkill() 
    {
        return skill;
    }

    public void setHonor(String honor) 
    {
        this.honor = honor;
    }

    public String getHonor() 
    {
        return honor;
    }

    public void setResumeFileUrl(String resumeFileUrl) 
    {
        this.resumeFileUrl = resumeFileUrl;
    }

    public String getResumeFileUrl() 
    {
        return resumeFileUrl;
    }

    public void setFileSize(BigDecimal fileSize) 
    {
        this.fileSize = fileSize;
    }

    public BigDecimal getFileSize() 
    {
        return fileSize;
    }

    public void setIsDefault(Integer isDefault) 
    {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault() 
    {
        return isDefault;
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
            .append("resumeId", getResumeId())
            .append("studentId", getStudentId())
            .append("resumeTitle", getResumeTitle())
            .append("resumeType", getResumeType())
            .append("educationBg", getEducationBg())
            .append("workExp", getWorkExp())
            .append("projectExp", getProjectExp())
            .append("skill", getSkill())
            .append("honor", getHonor())
            .append("resumeFileUrl", getResumeFileUrl())
            .append("fileSize", getFileSize())
            .append("isDefault", getIsDefault())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
