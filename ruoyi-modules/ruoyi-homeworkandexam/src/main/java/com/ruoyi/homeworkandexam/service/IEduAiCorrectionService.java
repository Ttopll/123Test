package com.ruoyi.homeworkandexam.service;

import java.util.List;
import com.ruoyi.homeworkandexam.domain.EduAiCorrection;

/**
 * AI 批改记录Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduAiCorrectionService 
{
    /**
     * 查询AI 批改记录
     * 
     * @param correctionId AI 批改记录主键
     * @return AI 批改记录
     */
    public EduAiCorrection selectEduAiCorrectionByCorrectionId(Long correctionId);

    /**
     * 查询AI 批改记录列表
     * 
     * @param eduAiCorrection AI 批改记录
     * @return AI 批改记录集合
     */
    public List<EduAiCorrection> selectEduAiCorrectionList(EduAiCorrection eduAiCorrection);

    /**
     * 新增AI 批改记录
     * 
     * @param eduAiCorrection AI 批改记录
     * @return 结果
     */
    public int insertEduAiCorrection(EduAiCorrection eduAiCorrection);

    /**
     * 修改AI 批改记录
     * 
     * @param eduAiCorrection AI 批改记录
     * @return 结果
     */
    public int updateEduAiCorrection(EduAiCorrection eduAiCorrection);

    /**
     * 批量删除AI 批改记录
     * 
     * @param correctionIds 需要删除的AI 批改记录主键集合
     * @return 结果
     */
    public int deleteEduAiCorrectionByCorrectionIds(Long[] correctionIds);

    /**
     * 删除AI 批改记录信息
     * 
     * @param correctionId AI 批改记录主键
     * @return 结果
     */
    public int deleteEduAiCorrectionByCorrectionId(Long correctionId);
}
