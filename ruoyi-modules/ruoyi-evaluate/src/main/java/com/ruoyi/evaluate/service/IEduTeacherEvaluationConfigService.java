package com.ruoyi.evaluate.service;

import java.util.List;
import com.ruoyi.evaluate.domain.EduTeacherEvaluationConfig;

/**
 * 教师评价配置Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduTeacherEvaluationConfigService 
{
    /**
     * 查询教师评价配置
     * 
     * @param configId 教师评价配置主键
     * @return 教师评价配置
     */
    public EduTeacherEvaluationConfig selectEduTeacherEvaluationConfigByConfigId(Long configId);

    /**
     * 查询教师评价配置列表
     * 
     * @param eduTeacherEvaluationConfig 教师评价配置
     * @return 教师评价配置集合
     */
    public List<EduTeacherEvaluationConfig> selectEduTeacherEvaluationConfigList(EduTeacherEvaluationConfig eduTeacherEvaluationConfig);

    /**
     * 新增教师评价配置
     * 
     * @param eduTeacherEvaluationConfig 教师评价配置
     * @return 结果
     */
    public int insertEduTeacherEvaluationConfig(EduTeacherEvaluationConfig eduTeacherEvaluationConfig);

    /**
     * 修改教师评价配置
     * 
     * @param eduTeacherEvaluationConfig 教师评价配置
     * @return 结果
     */
    public int updateEduTeacherEvaluationConfig(EduTeacherEvaluationConfig eduTeacherEvaluationConfig);

    /**
     * 批量删除教师评价配置
     * 
     * @param configIds 需要删除的教师评价配置主键集合
     * @return 结果
     */
    public int deleteEduTeacherEvaluationConfigByConfigIds(Long[] configIds);

    /**
     * 删除教师评价配置信息
     * 
     * @param configId 教师评价配置主键
     * @return 结果
     */
    public int deleteEduTeacherEvaluationConfigByConfigId(Long configId);
}
