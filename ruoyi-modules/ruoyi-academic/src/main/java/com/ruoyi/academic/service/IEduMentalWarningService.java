package com.ruoyi.academic.service;

import java.util.List;
import com.ruoyi.academic.domain.EduMentalWarning;

/**
 * 心理健康预警Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduMentalWarningService 
{
    /**
     * 查询心理健康预警
     * 
     * @param warningId 心理健康预警主键
     * @return 心理健康预警
     */
    public EduMentalWarning selectEduMentalWarningByWarningId(Long warningId);

    /**
     * 查询心理健康预警列表
     * 
     * @param eduMentalWarning 心理健康预警
     * @return 心理健康预警集合
     */
    public List<EduMentalWarning> selectEduMentalWarningList(EduMentalWarning eduMentalWarning);

    /**
     * 新增心理健康预警
     * 
     * @param eduMentalWarning 心理健康预警
     * @return 结果
     */
    public int insertEduMentalWarning(EduMentalWarning eduMentalWarning);

    /**
     * 修改心理健康预警
     * 
     * @param eduMentalWarning 心理健康预警
     * @return 结果
     */
    public int updateEduMentalWarning(EduMentalWarning eduMentalWarning);

    /**
     * 批量删除心理健康预警
     * 
     * @param warningIds 需要删除的心理健康预警主键集合
     * @return 结果
     */
    public int deleteEduMentalWarningByWarningIds(Long[] warningIds);

    /**
     * 删除心理健康预警信息
     * 
     * @param warningId 心理健康预警主键
     * @return 结果
     */
    public int deleteEduMentalWarningByWarningId(Long warningId);
}
