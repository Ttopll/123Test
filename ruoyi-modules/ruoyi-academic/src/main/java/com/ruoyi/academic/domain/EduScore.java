package com.ruoyi.academic.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 成绩管理对象 edu_score
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩 ID */
    private Long scoreId;

    /** 学生 ID */
    @Excel(name = "学生 ID")
    private String studentId;

    /** 课程 ID */
    @Excel(name = "课程 ID")
    private Long courseId;

    /** 教师 ID */
    @Excel(name = "教师 ID")
    private String teacherId;

    /** 学期 */
    @Excel(name = "学期")
    private String semester;

    /** 考试类型 */
    @Excel(name = "考试类型")
    private Integer examType;

    /** 分数 */
    @Excel(name = "分数")
    private BigDecimal score;

    /** 成绩等级（A/B/C） */
    @Excel(name = "成绩等级", readConverterExp = "A=/B/C")
    private String scoreLevel;

    /** 是否及格 */
    @Excel(name = "是否及格")
    private Integer isPass;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setScoreId(Long scoreId) 
    {
        this.scoreId = scoreId;
    }

    public Long getScoreId() 
    {
        return scoreId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setTeacherId(String teacherId) 
    {
        this.teacherId = teacherId;
    }

    public String getTeacherId() 
    {
        return teacherId;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
    }

    public void setExamType(Integer examType) 
    {
        this.examType = examType;
    }

    public Integer getExamType() 
    {
        return examType;
    }

    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }

    public void setScoreLevel(String scoreLevel) 
    {
        this.scoreLevel = scoreLevel;
    }

    public String getScoreLevel() 
    {
        return scoreLevel;
    }

    public void setIsPass(Integer isPass) 
    {
        this.isPass = isPass;
    }

    public Integer getIsPass() 
    {
        return isPass;
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
            .append("scoreId", getScoreId())
            .append("studentId", getStudentId())
            .append("courseId", getCourseId())
            .append("teacherId", getTeacherId())
            .append("semester", getSemester())
            .append("examType", getExamType())
            .append("score", getScore())
            .append("scoreLevel", getScoreLevel())
            .append("isPass", getIsPass())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
