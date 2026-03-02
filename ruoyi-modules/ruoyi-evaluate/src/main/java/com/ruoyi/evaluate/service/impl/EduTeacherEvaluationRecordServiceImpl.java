package com.ruoyi.evaluate.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.evaluate.mapper.EduTeacherEvaluationRecordMapper;
import com.ruoyi.evaluate.domain.EduTeacherEvaluationRecord;
import com.ruoyi.evaluate.service.IEduTeacherEvaluationRecordService;

/**
 * 教师评价记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduTeacherEvaluationRecordServiceImpl implements IEduTeacherEvaluationRecordService 
{
    @Autowired
    private EduTeacherEvaluationRecordMapper eduTeacherEvaluationRecordMapper;

    /**
     * 查询教师评价记录
     * 
     * @param recordId 教师评价记录主键
     * @return 教师评价记录
     */
    @Override
    public EduTeacherEvaluationRecord selectEduTeacherEvaluationRecordByRecordId(String recordId)
    {
        return eduTeacherEvaluationRecordMapper.selectEduTeacherEvaluationRecordByRecordId(recordId);
    }

    /**
     * 查询教师评价记录列表
     * 
     * @param eduTeacherEvaluationRecord 教师评价记录
     * @return 教师评价记录
     */
    @Override
    public List<EduTeacherEvaluationRecord> selectEduTeacherEvaluationRecordList(EduTeacherEvaluationRecord eduTeacherEvaluationRecord)
    {
        return eduTeacherEvaluationRecordMapper.selectEduTeacherEvaluationRecordList(eduTeacherEvaluationRecord);
    }

    /**
     * 新增教师评价记录
     * 
     * @param eduTeacherEvaluationRecord 教师评价记录
     * @return 结果
     */
    @Override
    public int insertEduTeacherEvaluationRecord(EduTeacherEvaluationRecord eduTeacherEvaluationRecord)
    {
        eduTeacherEvaluationRecord.setCreateTime(DateUtils.getNowDate());
        return eduTeacherEvaluationRecordMapper.insertEduTeacherEvaluationRecord(eduTeacherEvaluationRecord);
    }

    /**
     * 修改教师评价记录
     * 
     * @param eduTeacherEvaluationRecord 教师评价记录
     * @return 结果
     */
    @Override
    public int updateEduTeacherEvaluationRecord(EduTeacherEvaluationRecord eduTeacherEvaluationRecord)
    {
        eduTeacherEvaluationRecord.setUpdateTime(DateUtils.getNowDate());
        return eduTeacherEvaluationRecordMapper.updateEduTeacherEvaluationRecord(eduTeacherEvaluationRecord);
    }

    /**
     * 批量删除教师评价记录
     * 
     * @param recordIds 需要删除的教师评价记录主键
     * @return 结果
     */
    @Override
    public int deleteEduTeacherEvaluationRecordByRecordIds(String[] recordIds)
    {
        return eduTeacherEvaluationRecordMapper.deleteEduTeacherEvaluationRecordByRecordIds(recordIds);
    }

    /**
     * 删除教师评价记录信息
     * 
     * @param recordId 教师评价记录主键
     * @return 结果
     */
    @Override
    public int deleteEduTeacherEvaluationRecordByRecordId(String recordId)
    {
        return eduTeacherEvaluationRecordMapper.deleteEduTeacherEvaluationRecordByRecordId(recordId);
    }
}
