package com.ruoyi.homeworkandexam.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.homeworkandexam.mapper.EduAssignmentMapper;
import com.ruoyi.homeworkandexam.domain.EduAssignment;
import com.ruoyi.homeworkandexam.service.IEduAssignmentService;

/**
 * 作业管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAssignmentServiceImpl implements IEduAssignmentService 
{
    @Autowired
    private EduAssignmentMapper eduAssignmentMapper;

    /**
     * 查询作业管理
     * 
     * @param assignId 作业管理主键
     * @return 作业管理
     */
    @Override
    public EduAssignment selectEduAssignmentByAssignId(Long assignId)
    {
        return eduAssignmentMapper.selectEduAssignmentByAssignId(assignId);
    }

    /**
     * 查询作业管理列表
     * 
     * @param eduAssignment 作业管理
     * @return 作业管理
     */
    @Override
    public List<EduAssignment> selectEduAssignmentList(EduAssignment eduAssignment)
    {
        return eduAssignmentMapper.selectEduAssignmentList(eduAssignment);
    }

    /**
     * 新增作业管理
     * 
     * @param eduAssignment 作业管理
     * @return 结果
     */
    @Override
    public int insertEduAssignment(EduAssignment eduAssignment)
    {
        eduAssignment.setCreateTime(DateUtils.getNowDate());
        return eduAssignmentMapper.insertEduAssignment(eduAssignment);
    }

    /**
     * 修改作业管理
     * 
     * @param eduAssignment 作业管理
     * @return 结果
     */
    @Override
    public int updateEduAssignment(EduAssignment eduAssignment)
    {
        eduAssignment.setUpdateTime(DateUtils.getNowDate());
        return eduAssignmentMapper.updateEduAssignment(eduAssignment);
    }

    /**
     * 批量删除作业管理
     * 
     * @param assignIds 需要删除的作业管理主键
     * @return 结果
     */
    @Override
    public int deleteEduAssignmentByAssignIds(Long[] assignIds)
    {
        return eduAssignmentMapper.deleteEduAssignmentByAssignIds(assignIds);
    }

    /**
     * 删除作业管理信息
     * 
     * @param assignId 作业管理主键
     * @return 结果
     */
    @Override
    public int deleteEduAssignmentByAssignId(Long assignId)
    {
        return eduAssignmentMapper.deleteEduAssignmentByAssignId(assignId);
    }
}
