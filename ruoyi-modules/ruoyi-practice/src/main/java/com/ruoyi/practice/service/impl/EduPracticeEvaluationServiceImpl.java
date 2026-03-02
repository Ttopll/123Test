package com.ruoyi.practice.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.practice.mapper.EduPracticeEvaluationMapper;
import com.ruoyi.practice.domain.EduPracticeEvaluation;
import com.ruoyi.practice.service.IEduPracticeEvaluationService;

/**
 * 实践活动评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduPracticeEvaluationServiceImpl implements IEduPracticeEvaluationService 
{
    @Autowired
    private EduPracticeEvaluationMapper eduPracticeEvaluationMapper;

    /**
     * 查询实践活动评价
     * 
     * @param evaluationId 实践活动评价主键
     * @return 实践活动评价
     */
    @Override
    public EduPracticeEvaluation selectEduPracticeEvaluationByEvaluationId(Long evaluationId)
    {
        return eduPracticeEvaluationMapper.selectEduPracticeEvaluationByEvaluationId(evaluationId);
    }

    /**
     * 查询实践活动评价列表
     * 
     * @param eduPracticeEvaluation 实践活动评价
     * @return 实践活动评价
     */
    @Override
    public List<EduPracticeEvaluation> selectEduPracticeEvaluationList(EduPracticeEvaluation eduPracticeEvaluation)
    {
        return eduPracticeEvaluationMapper.selectEduPracticeEvaluationList(eduPracticeEvaluation);
    }

    /**
     * 新增实践活动评价
     * 
     * @param eduPracticeEvaluation 实践活动评价
     * @return 结果
     */
    @Override
    public int insertEduPracticeEvaluation(EduPracticeEvaluation eduPracticeEvaluation)
    {
        eduPracticeEvaluation.setCreateTime(DateUtils.getNowDate());
        return eduPracticeEvaluationMapper.insertEduPracticeEvaluation(eduPracticeEvaluation);
    }

    /**
     * 修改实践活动评价
     * 
     * @param eduPracticeEvaluation 实践活动评价
     * @return 结果
     */
    @Override
    public int updateEduPracticeEvaluation(EduPracticeEvaluation eduPracticeEvaluation)
    {
        eduPracticeEvaluation.setUpdateTime(DateUtils.getNowDate());
        return eduPracticeEvaluationMapper.updateEduPracticeEvaluation(eduPracticeEvaluation);
    }

    /**
     * 批量删除实践活动评价
     * 
     * @param evaluationIds 需要删除的实践活动评价主键
     * @return 结果
     */
    @Override
    public int deleteEduPracticeEvaluationByEvaluationIds(Long[] evaluationIds)
    {
        return eduPracticeEvaluationMapper.deleteEduPracticeEvaluationByEvaluationIds(evaluationIds);
    }

    /**
     * 删除实践活动评价信息
     * 
     * @param evaluationId 实践活动评价主键
     * @return 结果
     */
    @Override
    public int deleteEduPracticeEvaluationByEvaluationId(Long evaluationId)
    {
        return eduPracticeEvaluationMapper.deleteEduPracticeEvaluationByEvaluationId(evaluationId);
    }
}
