package com.ruoyi.aipaper.mapper;

import java.util.List;
import com.ruoyi.aipaper.domain.EduQuestion;

/**
 * 试题Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduQuestionMapper 
{
    /**
     * 查询试题
     * 
     * @param questionId 试题主键
     * @return 试题
     */
    public EduQuestion selectEduQuestionByQuestionId(Long questionId);

    /**
     * 查询试题列表
     * 
     * @param eduQuestion 试题
     * @return 试题集合
     */
    public List<EduQuestion> selectEduQuestionList(EduQuestion eduQuestion);

    /**
     * 新增试题
     * 
     * @param eduQuestion 试题
     * @return 结果
     */
    public int insertEduQuestion(EduQuestion eduQuestion);

    /**
     * 修改试题
     * 
     * @param eduQuestion 试题
     * @return 结果
     */
    public int updateEduQuestion(EduQuestion eduQuestion);

    /**
     * 删除试题
     * 
     * @param questionId 试题主键
     * @return 结果
     */
    public int deleteEduQuestionByQuestionId(Long questionId);

    /**
     * 批量删除试题
     * 
     * @param questionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduQuestionByQuestionIds(Long[] questionIds);
}
