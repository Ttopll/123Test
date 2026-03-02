package com.ruoyi.aipaper.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 组卷模板对象 edu_paper_template
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduPaperTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 组卷模板 ID */
    private Long templateId;

    /** 模板名称（如：《数据库原理》期末卷模板、计算机专业通用模板） */
    @Excel(name = "模板名称", readConverterExp = "如=：《数据库原理》期末卷模板、计算机专业通用模板")
    private String templateName;

    /** 关联课程 ID（为空表示通用模板） */
    @Excel(name = "关联课程 ID", readConverterExp = "为=空表示通用模板")
    private Long courseId;

    /** 关联专业 ID（为空表示跨专业通用） */
    @Excel(name = "关联专业 ID", readConverterExp = "为=空表示跨专业通用")
    private Long majorId;

    /** 适用考试类型 */
    @Excel(name = "适用考试类型")
    private Integer examType;

    /** 模板总分（固定分值，组卷时按模板分配题型分值） */
    @Excel(name = "模板总分", readConverterExp = "固=定分值，组卷时按模板分配题型分值")
    private Long totalScore;

    /** 适用考试时长（分钟） */
    @Excel(name = "适用考试时长", readConverterExp = "分=钟")
    private Long examTime;

    /** 题型配置（JSON 格式），示例：{"single_choice" : {"count": 10, "score": 2}, "multiple_choice" : {"count": 5, "score": 4} , "judgment" : {"count": 10, "score": 1} , "essay": {"count": 3, "score": 10}} */
    @Excel(name = "题型配置", readConverterExp = "J=SON,格=式")
    private String questionConfig;

    /** 难度配置（JSON 格式），示例：{"easy": 30, "medium": 50, "hard": 20} */
    @Excel(name = "难度配置", readConverterExp = "J=SON,格=式")
    private String difficultyConfig;

    /** 模板创建人 ID（教师 / 管理员） */
    @Excel(name = "模板创建人 ID", readConverterExp = "教=师,/=,管=理员")
    private String creatorId;

    /** 是否公开 */
    @Excel(name = "是否公开")
    private Integer isPublic;

    /** 所属院系 ID（公开范围为院系时必填） */
    @Excel(name = "所属院系 ID", readConverterExp = "公=开范围为院系时必填")
    private Long deptId;

    /** 模板状态 */
    @Excel(name = "模板状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }

    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setMajorId(Long majorId) 
    {
        this.majorId = majorId;
    }

    public Long getMajorId() 
    {
        return majorId;
    }

    public void setExamType(Integer examType) 
    {
        this.examType = examType;
    }

    public Integer getExamType() 
    {
        return examType;
    }

    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
    }

    public void setExamTime(Long examTime) 
    {
        this.examTime = examTime;
    }

    public Long getExamTime() 
    {
        return examTime;
    }

    public void setQuestionConfig(String questionConfig) 
    {
        this.questionConfig = questionConfig;
    }

    public String getQuestionConfig() 
    {
        return questionConfig;
    }

    public void setDifficultyConfig(String difficultyConfig) 
    {
        this.difficultyConfig = difficultyConfig;
    }

    public String getDifficultyConfig() 
    {
        return difficultyConfig;
    }

    public void setCreatorId(String creatorId) 
    {
        this.creatorId = creatorId;
    }

    public String getCreatorId() 
    {
        return creatorId;
    }

    public void setIsPublic(Integer isPublic) 
    {
        this.isPublic = isPublic;
    }

    public Integer getIsPublic() 
    {
        return isPublic;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
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
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("courseId", getCourseId())
            .append("majorId", getMajorId())
            .append("examType", getExamType())
            .append("totalScore", getTotalScore())
            .append("examTime", getExamTime())
            .append("questionConfig", getQuestionConfig())
            .append("difficultyConfig", getDifficultyConfig())
            .append("creatorId", getCreatorId())
            .append("isPublic", getIsPublic())
            .append("deptId", getDeptId())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
