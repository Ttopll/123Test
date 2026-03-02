package com.ruoyi.academic.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.academic.mapper.EduMentalWarningMapper;
import com.ruoyi.academic.domain.EduMentalWarning;
import com.ruoyi.academic.service.IEduMentalWarningService;

/**
 * 心理健康预警Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduMentalWarningServiceImpl implements IEduMentalWarningService 
{
    @Autowired
    private EduMentalWarningMapper eduMentalWarningMapper;

    /**
     * 查询心理健康预警
     * 
     * @param warningId 心理健康预警主键
     * @return 心理健康预警
     */
    @Override
    public EduMentalWarning selectEduMentalWarningByWarningId(Long warningId)
    {
        return eduMentalWarningMapper.selectEduMentalWarningByWarningId(warningId);
    }

    /**
     * 查询心理健康预警列表
     * 
     * @param eduMentalWarning 心理健康预警
     * @return 心理健康预警
     */
    @Override
    public List<EduMentalWarning> selectEduMentalWarningList(EduMentalWarning eduMentalWarning)
    {
        return eduMentalWarningMapper.selectEduMentalWarningList(eduMentalWarning);
    }

    /**
     * 新增心理健康预警
     * 
     * @param eduMentalWarning 心理健康预警
     * @return 结果
     */
    @Override
    public int insertEduMentalWarning(EduMentalWarning eduMentalWarning)
    {
        eduMentalWarning.setCreateTime(DateUtils.getNowDate());
        return eduMentalWarningMapper.insertEduMentalWarning(eduMentalWarning);
    }

    /**
     * 修改心理健康预警
     * 
     * @param eduMentalWarning 心理健康预警
     * @return 结果
     */
    @Override
    public int updateEduMentalWarning(EduMentalWarning eduMentalWarning)
    {
        eduMentalWarning.setUpdateTime(DateUtils.getNowDate());
        return eduMentalWarningMapper.updateEduMentalWarning(eduMentalWarning);
    }

    /**
     * 批量删除心理健康预警
     * 
     * @param warningIds 需要删除的心理健康预警主键
     * @return 结果
     */
    @Override
    public int deleteEduMentalWarningByWarningIds(Long[] warningIds)
    {
        return eduMentalWarningMapper.deleteEduMentalWarningByWarningIds(warningIds);
    }

    /**
     * 删除心理健康预警信息
     * 
     * @param warningId 心理健康预警主键
     * @return 结果
     */
    @Override
    public int deleteEduMentalWarningByWarningId(Long warningId)
    {
        return eduMentalWarningMapper.deleteEduMentalWarningByWarningId(warningId);
    }
}
