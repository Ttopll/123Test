package com.ruoyi.academic.service;

import java.util.List;
import com.ruoyi.academic.domain.EduGrowthRecord;

/**
 * 学生成长记录Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduGrowthRecordService 
{
    /**
     * 查询学生成长记录
     * 
     * @param recordId 学生成长记录主键
     * @return 学生成长记录
     */
    public EduGrowthRecord selectEduGrowthRecordByRecordId(Long recordId);

    /**
     * 查询学生成长记录列表
     * 
     * @param eduGrowthRecord 学生成长记录
     * @return 学生成长记录集合
     */
    public List<EduGrowthRecord> selectEduGrowthRecordList(EduGrowthRecord eduGrowthRecord);

    /**
     * 新增学生成长记录
     * 
     * @param eduGrowthRecord 学生成长记录
     * @return 结果
     */
    public int insertEduGrowthRecord(EduGrowthRecord eduGrowthRecord);

    /**
     * 修改学生成长记录
     * 
     * @param eduGrowthRecord 学生成长记录
     * @return 结果
     */
    public int updateEduGrowthRecord(EduGrowthRecord eduGrowthRecord);

    /**
     * 批量删除学生成长记录
     * 
     * @param recordIds 需要删除的学生成长记录主键集合
     * @return 结果
     */
    public int deleteEduGrowthRecordByRecordIds(Long[] recordIds);

    /**
     * 删除学生成长记录信息
     * 
     * @param recordId 学生成长记录主键
     * @return 结果
     */
    public int deleteEduGrowthRecordByRecordId(Long recordId);
}
