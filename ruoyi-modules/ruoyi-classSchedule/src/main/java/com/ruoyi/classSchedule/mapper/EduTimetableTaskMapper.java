package com.ruoyi.classSchedule.mapper;

import java.util.List;
import com.ruoyi.classSchedule.domain.EduTimetableTask;

/**
 * 排课任务Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public interface EduTimetableTaskMapper 
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
     * 删除排课任务
     * 
     * @param taskId 排课任务主键
     * @return 结果
     */
    public int deleteEduTimetableTaskByTaskId(Long taskId);

    /**
     * 批量删除排课任务
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduTimetableTaskByTaskIds(Long[] taskIds);
}
