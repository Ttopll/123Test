package com.ruoyi.aipaper.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aipaper.mapper.EduQuestionMapper;
import com.ruoyi.aipaper.domain.EduQuestion;
import com.ruoyi.aipaper.service.IEduQuestionService;

/**
 * 试题Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduQuestionServiceImpl implements IEduQuestionService 
{
    @Autowired
    private EduQuestionMapper eduQuestionMapper;

    /**
     * 查询试题
     * 
     * @param questionId 试题主键
     * @return 试题
     */
    @Override
    public EduQuestion selectEduQuestionByQuestionId(Long questionId)
    {
        return eduQuestionMapper.selectEduQuestionByQuestionId(questionId);
    }

    /**
     * 查询试题列表
     * 
     * @param eduQuestion 试题
     * @return 试题
     */
    @Override
    public List<EduQuestion> selectEduQuestionList(EduQuestion eduQuestion)
    {
        return eduQuestionMapper.selectEduQuestionList(eduQuestion);
    }

    /**
     * 新增试题
     * 
     * @param eduQuestion 试题
     * @return 结果
     */
    @Override
    public int insertEduQuestion(EduQuestion eduQuestion)
    {
        eduQuestion.setCreateTime(DateUtils.getNowDate());
        return eduQuestionMapper.insertEduQuestion(eduQuestion);
    }

    /**
     * 修改试题
     * 
     * @param eduQuestion 试题
     * @return 结果
     */
    @Override
    public int updateEduQuestion(EduQuestion eduQuestion)
    {
        eduQuestion.setUpdateTime(DateUtils.getNowDate());
        return eduQuestionMapper.updateEduQuestion(eduQuestion);
    }

    /**
     * 批量删除试题
     * 
     * @param questionIds 需要删除的试题主键
     * @return 结果
     */
    @Override
    public int deleteEduQuestionByQuestionIds(Long[] questionIds)
    {
        return eduQuestionMapper.deleteEduQuestionByQuestionIds(questionIds);
    }

    /**
     * 删除试题信息
     * 
     * @param questionId 试题主键
     * @return 结果
     */
    @Override
    public int deleteEduQuestionByQuestionId(Long questionId)
    {
        return eduQuestionMapper.deleteEduQuestionByQuestionId(questionId);
    }
}
