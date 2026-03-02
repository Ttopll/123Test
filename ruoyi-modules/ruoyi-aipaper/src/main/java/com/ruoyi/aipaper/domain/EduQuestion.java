package com.ruoyi.aipaper.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 试题对象 edu_question
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 试题 ID */
    private Long questionId;

    /** 关联课程 ID */
    @Excel(name = "关联课程 ID")
    private Long courseId;

    /** 试题类型 */
    @Excel(name = "试题类型")
    private Integer questionType;

    /** 试题内容（支持富文本） */
    @Excel(name = "试题内容", readConverterExp = "支=持富文本")
    private String questionContent;

    /** 选项内容（JSON 格式，单选 / 多选需填） */
    @Excel(name = "选项内容", readConverterExp = "J=SON,格=式，单选,/=,多=选需填")
    private String optionContent;

    /** 参考答案 */
    @Excel(name = "参考答案")
    private String answer;

    /** 试题分值 */
    @Excel(name = "试题分值")
    private Long score;

    /** 难度等级 */
    @Excel(name = "难度等级")
    private Integer difficulty;

    /** 创建人 ID */
    @Excel(name = "创建人 ID")
    private String createUserId;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setQuestionType(Integer questionType) 
    {
        this.questionType = questionType;
    }

    public Integer getQuestionType() 
    {
        return questionType;
    }

    public void setQuestionContent(String questionContent) 
    {
        this.questionContent = questionContent;
    }

    public String getQuestionContent() 
    {
        return questionContent;
    }

    public void setOptionContent(String optionContent) 
    {
        this.optionContent = optionContent;
    }

    public String getOptionContent() 
    {
        return optionContent;
    }

    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }

    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }

    public void setDifficulty(Integer difficulty) 
    {
        this.difficulty = difficulty;
    }

    public Integer getDifficulty() 
    {
        return difficulty;
    }

    public void setCreateUserId(String createUserId) 
    {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() 
    {
        return createUserId;
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
            .append("questionId", getQuestionId())
            .append("courseId", getCourseId())
            .append("questionType", getQuestionType())
            .append("questionContent", getQuestionContent())
            .append("optionContent", getOptionContent())
            .append("answer", getAnswer())
            .append("score", getScore())
            .append("difficulty", getDifficulty())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
