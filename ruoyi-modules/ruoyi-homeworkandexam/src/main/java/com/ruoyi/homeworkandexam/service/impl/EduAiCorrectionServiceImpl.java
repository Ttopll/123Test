package com.ruoyi.homeworkandexam.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.homeworkandexam.mapper.EduAiCorrectionMapper;
import com.ruoyi.homeworkandexam.domain.EduAiCorrection;
import com.ruoyi.homeworkandexam.service.IEduAiCorrectionService;

/**
 * AI 批改记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAiCorrectionServiceImpl implements IEduAiCorrectionService 
{
    @Autowired
    private EduAiCorrectionMapper eduAiCorrectionMapper;

    /**
     * 查询AI 批改记录
     * 
     * @param correctionId AI 批改记录主键
     * @return AI 批改记录
     */
    @Override
    public EduAiCorrection selectEduAiCorrectionByCorrectionId(Long correctionId)
    {
        return eduAiCorrectionMapper.selectEduAiCorrectionByCorrectionId(correctionId);
    }

    /**
     * 查询AI 批改记录列表
     * 
     * @param eduAiCorrection AI 批改记录
     * @return AI 批改记录
     */
    @Override
    public List<EduAiCorrection> selectEduAiCorrectionList(EduAiCorrection eduAiCorrection)
    {
        return eduAiCorrectionMapper.selectEduAiCorrectionList(eduAiCorrection);
    }

    /**
     * 新增AI 批改记录
     * 
     * @param eduAiCorrection AI 批改记录
     * @return 结果
     */
    @Override
    public int insertEduAiCorrection(EduAiCorrection eduAiCorrection)
    {
        eduAiCorrection.setCreateTime(DateUtils.getNowDate());
        return eduAiCorrectionMapper.insertEduAiCorrection(eduAiCorrection);
    }

    /**
     * 修改AI 批改记录
     * 
     * @param eduAiCorrection AI 批改记录
     * @return 结果
     */
    @Override
    public int updateEduAiCorrection(EduAiCorrection eduAiCorrection)
    {
        eduAiCorrection.setUpdateTime(DateUtils.getNowDate());
        return eduAiCorrectionMapper.updateEduAiCorrection(eduAiCorrection);
    }

    /**
     * 批量删除AI 批改记录
     * 
     * @param correctionIds 需要删除的AI 批改记录主键
     * @return 结果
     */
    @Override
    public int deleteEduAiCorrectionByCorrectionIds(Long[] correctionIds)
    {
        return eduAiCorrectionMapper.deleteEduAiCorrectionByCorrectionIds(correctionIds);
    }

    /**
     * 删除AI 批改记录信息
     * 
     * @param correctionId AI 批改记录主键
     * @return 结果
     */
    @Override
    public int deleteEduAiCorrectionByCorrectionId(Long correctionId)
    {
        return eduAiCorrectionMapper.deleteEduAiCorrectionByCorrectionId(correctionId);
    }
}
