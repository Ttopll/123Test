package com.ruoyi.academic.service;

import java.util.List;
import com.ruoyi.academic.domain.EduScore;

/**
 * 成绩管理Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduScoreService 
{
    /**
     * 查询成绩管理
     * 
     * @param scoreId 成绩管理主键
     * @return 成绩管理
     */
    public EduScore selectEduScoreByScoreId(Long scoreId);

    /**
     * 查询成绩管理列表
     * 
     * @param eduScore 成绩管理
     * @return 成绩管理集合
     */
    public List<EduScore> selectEduScoreList(EduScore eduScore);

    /**
     * 新增成绩管理
     * 
     * @param eduScore 成绩管理
     * @return 结果
     */
    public int insertEduScore(EduScore eduScore);

    /**
     * 修改成绩管理
     * 
     * @param eduScore 成绩管理
     * @return 结果
     */
    public int updateEduScore(EduScore eduScore);

    /**
     * 批量删除成绩管理
     * 
     * @param scoreIds 需要删除的成绩管理主键集合
     * @return 结果
     */
    public int deleteEduScoreByScoreIds(Long[] scoreIds);

    /**
     * 删除成绩管理信息
     * 
     * @param scoreId 成绩管理主键
     * @return 结果
     */
    public int deleteEduScoreByScoreId(Long scoreId);
}
