package com.ruoyi.academic.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.academic.mapper.EduScoreMapper;
import com.ruoyi.academic.domain.EduScore;
import com.ruoyi.academic.service.IEduScoreService;

/**
 * 成绩管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduScoreServiceImpl implements IEduScoreService 
{
    @Autowired
    private EduScoreMapper eduScoreMapper;

    /**
     * 查询成绩管理
     * 
     * @param scoreId 成绩管理主键
     * @return 成绩管理
     */
    @Override
    public EduScore selectEduScoreByScoreId(Long scoreId)
    {
        return eduScoreMapper.selectEduScoreByScoreId(scoreId);
    }

    /**
     * 查询成绩管理列表
     * 
     * @param eduScore 成绩管理
     * @return 成绩管理
     */
    @Override
    public List<EduScore> selectEduScoreList(EduScore eduScore)
    {
        return eduScoreMapper.selectEduScoreList(eduScore);
    }

    /**
     * 新增成绩管理
     * 
     * @param eduScore 成绩管理
     * @return 结果
     */
    @Override
    public int insertEduScore(EduScore eduScore)
    {
        eduScore.setCreateTime(DateUtils.getNowDate());
        return eduScoreMapper.insertEduScore(eduScore);
    }

    /**
     * 修改成绩管理
     * 
     * @param eduScore 成绩管理
     * @return 结果
     */
    @Override
    public int updateEduScore(EduScore eduScore)
    {
        eduScore.setUpdateTime(DateUtils.getNowDate());
        return eduScoreMapper.updateEduScore(eduScore);
    }

    /**
     * 批量删除成绩管理
     * 
     * @param scoreIds 需要删除的成绩管理主键
     * @return 结果
     */
    @Override
    public int deleteEduScoreByScoreIds(Long[] scoreIds)
    {
        return eduScoreMapper.deleteEduScoreByScoreIds(scoreIds);
    }

    /**
     * 删除成绩管理信息
     * 
     * @param scoreId 成绩管理主键
     * @return 结果
     */
    @Override
    public int deleteEduScoreByScoreId(Long scoreId)
    {
        return eduScoreMapper.deleteEduScoreByScoreId(scoreId);
    }
}
