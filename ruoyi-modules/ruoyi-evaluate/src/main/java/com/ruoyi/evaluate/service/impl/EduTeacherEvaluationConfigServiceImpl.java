package com.ruoyi.evaluate.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.evaluate.mapper.EduTeacherEvaluationConfigMapper;
import com.ruoyi.evaluate.domain.EduTeacherEvaluationConfig;
import com.ruoyi.evaluate.service.IEduTeacherEvaluationConfigService;

/**
 * 教师评价配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduTeacherEvaluationConfigServiceImpl implements IEduTeacherEvaluationConfigService 
{
    @Autowired
    private EduTeacherEvaluationConfigMapper eduTeacherEvaluationConfigMapper;

    /**
     * 查询教师评价配置
     * 
     * @param configId 教师评价配置主键
     * @return 教师评价配置
     */
    @Override
    public EduTeacherEvaluationConfig selectEduTeacherEvaluationConfigByConfigId(Long configId)
    {
        return eduTeacherEvaluationConfigMapper.selectEduTeacherEvaluationConfigByConfigId(configId);
    }

    /**
     * 查询教师评价配置列表
     * 
     * @param eduTeacherEvaluationConfig 教师评价配置
     * @return 教师评价配置
     */
    @Override
    public List<EduTeacherEvaluationConfig> selectEduTeacherEvaluationConfigList(EduTeacherEvaluationConfig eduTeacherEvaluationConfig)
    {
        return eduTeacherEvaluationConfigMapper.selectEduTeacherEvaluationConfigList(eduTeacherEvaluationConfig);
    }

    /**
     * 新增教师评价配置
     * 
     * @param eduTeacherEvaluationConfig 教师评价配置
     * @return 结果
     */
    @Override
    public int insertEduTeacherEvaluationConfig(EduTeacherEvaluationConfig eduTeacherEvaluationConfig)
    {
        eduTeacherEvaluationConfig.setCreateTime(DateUtils.getNowDate());
        return eduTeacherEvaluationConfigMapper.insertEduTeacherEvaluationConfig(eduTeacherEvaluationConfig);
    }

    /**
     * 修改教师评价配置
     * 
     * @param eduTeacherEvaluationConfig 教师评价配置
     * @return 结果
     */
    @Override
    public int updateEduTeacherEvaluationConfig(EduTeacherEvaluationConfig eduTeacherEvaluationConfig)
    {
        eduTeacherEvaluationConfig.setUpdateTime(DateUtils.getNowDate());
        return eduTeacherEvaluationConfigMapper.updateEduTeacherEvaluationConfig(eduTeacherEvaluationConfig);
    }

    /**
     * 批量删除教师评价配置
     * 
     * @param configIds 需要删除的教师评价配置主键
     * @return 结果
     */
    @Override
    public int deleteEduTeacherEvaluationConfigByConfigIds(Long[] configIds)
    {
        return eduTeacherEvaluationConfigMapper.deleteEduTeacherEvaluationConfigByConfigIds(configIds);
    }

    /**
     * 删除教师评价配置信息
     * 
     * @param configId 教师评价配置主键
     * @return 结果
     */
    @Override
    public int deleteEduTeacherEvaluationConfigByConfigId(Long configId)
    {
        return eduTeacherEvaluationConfigMapper.deleteEduTeacherEvaluationConfigByConfigId(configId);
    }
}
