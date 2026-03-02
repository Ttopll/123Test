package com.ruoyi.classSchedule.service;

import java.util.List;
import com.ruoyi.classSchedule.domain.EduTimetableTask;

/**
 * 排课任务Service接口
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public interface IEduTimetableTaskService 
{
    /**
     * 查询排课任务
     * 
     * @param taskId 排课任务主键
     * @return 排课任务
     */
    public EduTimetableTask selectEduTimetableTaskByTaskId(Long taskId);

    /**
     * 查询排课任务列表
     * 
     * @param eduTimetableTask 排课任务
     * @return 排课任务集合
     */
    public List<EduTimetableTask> selectEduTimetableTaskList(EduTimetableTask eduTimetableTask);

    /**
     * 新增排课任务
     * 
     * @param eduTimetableTask 排课任务
     * @return 结果
     */
    public int insertEduTimetableTask(EduTimetableTask eduTimetableTask);

    /**
     * 修改排课任务
     * 
     * @param eduTimetableTask 排课任务
     * @return 结果
     */
    public int updateEduTimetableTask(EduTimetableTask eduTimetableTask);

    /**
     * 批量删除排课任务
     * 
     * @param taskIds 需要删除的排课任务主键集合
     * @return 结果
     */
    public int deleteEduTimetableTaskByTaskIds(Long[] taskIds);

    /**
     * 删除排课任务信息
     * 
     * @param taskId 排课任务主键
     * @return 结果
     */
    public int deleteEduTimetableTaskByTaskId(Long taskId);
}
