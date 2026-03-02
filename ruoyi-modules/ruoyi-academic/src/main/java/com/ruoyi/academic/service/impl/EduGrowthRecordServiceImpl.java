package com.ruoyi.academic.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.ruoyi.academic.mapper.EduGrowthRecordMapper;
import com.ruoyi.academic.domain.EduGrowthRecord;
import com.ruoyi.academic.service.IEduGrowthRecordService;

/**
 * 学生成长记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduGrowthRecordServiceImpl implements IEduGrowthRecordService 
{
    @Autowired
    private EduGrowthRecordMapper eduGrowthRecordMapper;

    /**
     * 查询学生成长记录
     * 
     * @param recordId 学生成长记录主键
     * @return 学生成长记录
     */
    @Override
    public EduGrowthRecord selectEduGrowthRecordByRecordId(Long recordId)
    {
        return eduGrowthRecordMapper.selectEduGrowthRecordByRecordId(recordId);
    }

    /**
     * 查询学生成长记录列表
     * 
     * @param eduGrowthRecord 学生成长记录
     * @return 学生成长记录
     */
    @Override
    public List<EduGrowthRecord> selectEduGrowthRecordList(EduGrowthRecord eduGrowthRecord)
    {
        return eduGrowthRecordMapper.selectEduGrowthRecordList(eduGrowthRecord);
    }

    /**
     * 新增学生成长记录
     * 
     * @param eduGrowthRecord 学生成长记录
     * @return 结果
     */
    @Override
    public int insertEduGrowthRecord(EduGrowthRecord eduGrowthRecord)
    {
        eduGrowthRecord.setUpdateUserId(getCurrentUserId());  // 修复：添加获取当前用户的方法
        eduGrowthRecord.setCreateTime(DateUtils.getNowDate());
        eduGrowthRecord.setUpdateTime(DateUtils.getNowDate());
        return eduGrowthRecordMapper.insertEduGrowthRecord(eduGrowthRecord);
    }

    private String getCurrentUserId() {
        try {
            // 获取当前用户ID
            Long userId = SecurityUtils.getUserId();
            return userId != null ? userId.toString() : "system";
        } catch (Exception e) {
            // 如果获取失败，返回默认值
            return "system";
        }
    }

    /**
     * 修改学生成长记录
     * 
     * @param eduGrowthRecord 学生成长记录
     * @return 结果
     */
    @Override
    public int updateEduGrowthRecord(EduGrowthRecord eduGrowthRecord)
    {
        eduGrowthRecord.setUpdateTime(DateUtils.getNowDate());
        return eduGrowthRecordMapper.updateEduGrowthRecord(eduGrowthRecord);
    }

    /**
     * 批量删除学生成长记录
     * 
     * @param recordIds 需要删除的学生成长记录主键
     * @return 结果
     */
    @Override
    public int deleteEduGrowthRecordByRecordIds(Long[] recordIds)
    {
        return eduGrowthRecordMapper.deleteEduGrowthRecordByRecordIds(recordIds);
    }

    /**
     * 删除学生成长记录信息
     * 
     * @param recordId 学生成长记录主键
     * @return 结果
     */
    @Override
    public int deleteEduGrowthRecordByRecordId(Long recordId)
    {
        return eduGrowthRecordMapper.deleteEduGrowthRecordByRecordId(recordId);
    }
}
