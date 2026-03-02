package com.ruoyi.academic.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.academic.mapper.EduWarningMapper;
import com.ruoyi.academic.domain.EduWarning;
import com.ruoyi.academic.service.IEduWarningService;

/**
 * 学业预警Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduWarningServiceImpl implements IEduWarningService 
{
    @Autowired
    private EduWarningMapper eduWarningMapper;

    /**
     * 查询学业预警
     * 
     * @param warningId 学业预警主键
     * @return 学业预警
     */
    @Override
    public EduWarning selectEduWarningByWarningId(Long warningId)
    {
        return eduWarningMapper.selectEduWarningByWarningId(warningId);
    }

    /**
     * 查询学业预警列表
     * 
     * @param eduWarning 学业预警
     * @return 学业预警
     */
    @Override
    public List<EduWarning> selectEduWarningList(EduWarning eduWarning)
    {
        return eduWarningMapper.selectEduWarningList(eduWarning);
    }

    /**
     * 新增学业预警
     * 
     * @param eduWarning 学业预警
     * @return 结果
     */
    @Override
    public int insertEduWarning(EduWarning eduWarning)
    {
        eduWarning.setCreateTime(DateUtils.getNowDate());
        return eduWarningMapper.insertEduWarning(eduWarning);
    }

    /**
     * 修改学业预警
     * 
     * @param eduWarning 学业预警
     * @return 结果
     */
    @Override
    public int updateEduWarning(EduWarning eduWarning)
    {
        eduWarning.setUpdateTime(DateUtils.getNowDate());
        return eduWarningMapper.updateEduWarning(eduWarning);
    }

    /**
     * 批量删除学业预警
     * 
     * @param warningIds 需要删除的学业预警主键
     * @return 结果
     */
    @Override
    public int deleteEduWarningByWarningIds(Long[] warningIds)
    {
        return eduWarningMapper.deleteEduWarningByWarningIds(warningIds);
    }

    /**
     * 删除学业预警信息
     * 
     * @param warningId 学业预警主键
     * @return 结果
     */
    @Override
    public int deleteEduWarningByWarningId(Long warningId)
    {
        return eduWarningMapper.deleteEduWarningByWarningId(warningId);
    }
}
