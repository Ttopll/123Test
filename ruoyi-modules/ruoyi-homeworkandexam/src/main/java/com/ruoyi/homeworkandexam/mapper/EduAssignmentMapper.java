package com.ruoyi.homeworkandexam.mapper;

import java.util.List;
import com.ruoyi.homeworkandexam.domain.EduAssignment;

/**
 * 作业管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduAssignmentMapper 
{
    /**
     * 查询作业管理
     * 
     * @param assignId 作业管理主键
     * @return 作业管理
     */
    public EduAssignment selectEduAssignmentByAssignId(Long assignId);

    /**
     * 查询作业管理列表
     * 
     * @param eduAssignment 作业管理
     * @return 作业管理集合
     */
    public List<EduAssignment> selectEduAssignmentList(EduAssignment eduAssignment);

    /**
     * 新增作业管理
     * 
     * @param eduAssignment 作业管理
     * @return 结果
     */
    public int insertEduAssignment(EduAssignment eduAssignment);

    /**
     * 修改作业管理
     * 
     * @param eduAssignment 作业管理
     * @return 结果
     */
    public int updateEduAssignment(EduAssignment eduAssignment);

    /**
     * 删除作业管理
     * 
     * @param assignId 作业管理主键
     * @return 结果
     */
    public int deleteEduAssignmentByAssignId(Long assignId);

    /**
     * 批量删除作业管理
     * 
     * @param assignIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAssignmentByAssignIds(Long[] assignIds);
}
