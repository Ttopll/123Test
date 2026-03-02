package com.ruoyi.evaluate.mapper;

import java.util.List;
import com.ruoyi.evaluate.domain.EduTeacherEvaluationRecord;

/**
 * 教师评价记录Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduTeacherEvaluationRecordMapper 
{
    /**
     * 查询教师评价记录
     * 
     * @param recordId 教师评价记录主键
     * @return 教师评价记录
     */
    public EduTeacherEvaluationRecord selectEduTeacherEvaluationRecordByRecordId(String recordId);

    /**
     * 查询教师评价记录列表
     * 
     * @param eduTeacherEvaluationRecord 教师评价记录
     * @return 教师评价记录集合
     */
    public List<EduTeacherEvaluationRecord> selectEduTeacherEvaluationRecordList(EduTeacherEvaluationRecord eduTeacherEvaluationRecord);

    /**
     * 新增教师评价记录
     * 
     * @param eduTeacherEvaluationRecord 教师评价记录
     * @return 结果
     */
    public int insertEduTeacherEvaluationRecord(EduTeacherEvaluationRecord eduTeacherEvaluationRecord);

    /**
     * 修改教师评价记录
     * 
     * @param eduTeacherEvaluationRecord 教师评价记录
     * @return 结果
     */
    public int updateEduTeacherEvaluationRecord(EduTeacherEvaluationRecord eduTeacherEvaluationRecord);

    /**
     * 删除教师评价记录
     * 
     * @param recordId 教师评价记录主键
     * @return 结果
     */
    public int deleteEduTeacherEvaluationRecordByRecordId(String recordId);

    /**
     * 批量删除教师评价记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduTeacherEvaluationRecordByRecordIds(String[] recordIds);
}
