package com.ruoyi.practice.mapper;

import java.util.List;
import com.ruoyi.practice.domain.EduPracticeEvaluation;

/**
 * 实践活动评价Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduPracticeEvaluationMapper 
{
    /**
     * 查询实践活动评价
     * 
     * @param evaluationId 实践活动评价主键
     * @return 实践活动评价
     */
    public EduPracticeEvaluation selectEduPracticeEvaluationByEvaluationId(Long evaluationId);

    /**
     * 查询实践活动评价列表
     * 
     * @param eduPracticeEvaluation 实践活动评价
     * @return 实践活动评价集合
     */
    public List<EduPracticeEvaluation> selectEduPracticeEvaluationList(EduPracticeEvaluation eduPracticeEvaluation);

    /**
     * 新增实践活动评价
     * 
     * @param eduPracticeEvaluation 实践活动评价
     * @return 结果
     */
    public int insertEduPracticeEvaluation(EduPracticeEvaluation eduPracticeEvaluation);

    /**
     * 修改实践活动评价
     * 
     * @param eduPracticeEvaluation 实践活动评价
     * @return 结果
     */
    public int updateEduPracticeEvaluation(EduPracticeEvaluation eduPracticeEvaluation);

    /**
     * 删除实践活动评价
     * 
     * @param evaluationId 实践活动评价主键
     * @return 结果
     */
    public int deleteEduPracticeEvaluationByEvaluationId(Long evaluationId);

    /**
     * 批量删除实践活动评价
     * 
     * @param evaluationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduPracticeEvaluationByEvaluationIds(Long[] evaluationIds);
}
