package com.ruoyi.academic.service;

import java.util.List;
import com.ruoyi.academic.domain.EduWarning;

/**
 * 学业预警Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduWarningService 
{
    /**
     * 查询学业预警
     * 
     * @param warningId 学业预警主键
     * @return 学业预警
     */
    public EduWarning selectEduWarningByWarningId(Long warningId);

    /**
     * 查询学业预警列表
     * 
     * @param eduWarning 学业预警
     * @return 学业预警集合
     */
    public List<EduWarning> selectEduWarningList(EduWarning eduWarning);

    /**
     * 新增学业预警
     * 
     * @param eduWarning 学业预警
     * @return 结果
     */
    public int insertEduWarning(EduWarning eduWarning);

    /**
     * 修改学业预警
     * 
     * @param eduWarning 学业预警
     * @return 结果
     */
    public int updateEduWarning(EduWarning eduWarning);

    /**
     * 批量删除学业预警
     * 
     * @param warningIds 需要删除的学业预警主键集合
     * @return 结果
     */
    public int deleteEduWarningByWarningIds(Long[] warningIds);

    /**
     * 删除学业预警信息
     * 
     * @param warningId 学业预警主键
     * @return 结果
     */
    public int deleteEduWarningByWarningId(Long warningId);
}
