package com.ruoyi.classSchedule.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classSchedule.mapper.EduTimetableTaskMapper;
import com.ruoyi.classSchedule.domain.EduTimetableTask;
import com.ruoyi.classSchedule.service.IEduTimetableTaskService;

/**
 * 排课任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@Service
public class EduTimetableTaskServiceImpl implements IEduTimetableTaskService 
{
    @Autowired
    private EduTimetableTaskMapper eduTimetableTaskMapper;

    /**
     * 查询排课任务
     * 
     * @param taskId 排课任务主键
     * @return 排课任务
     */
    @Override
    public EduTimetableTask selectEduTimetableTaskByTaskId(Long taskId)
    {
        return eduTimetableTaskMapper.selectEduTimetableTaskByTaskId(taskId);
    }

    /**
     * 查询排课任务列表
     * 
     * @param eduTimetableTask 排课任务
     * @return 排课任务
     */
    @Override
    public List<EduTimetableTask> selectEduTimetableTaskList(EduTimetableTask eduTimetableTask)
    {
        return eduTimetableTaskMapper.selectEduTimetableTaskList(eduTimetableTask);
    }

    /**
     * 新增排课任务
     * 
     * @param eduTimetableTask 排课任务
     * @return 结果
     */
    @Override
    public int insertEduTimetableTask(EduTimetableTask eduTimetableTask)
    {
        eduTimetableTask.setCreateTime(DateUtils.getNowDate());
        return eduTimetableTaskMapper.insertEduTimetableTask(eduTimetableTask);
    }

    /**
     * 修改排课任务
     * 
     * @param eduTimetableTask 排课任务
     * @return 结果
     */
    @Override
    public int updateEduTimetableTask(EduTimetableTask eduTimetableTask)
    {
        eduTimetableTask.setUpdateTime(DateUtils.getNowDate());
        return eduTimetableTaskMapper.updateEduTimetableTask(eduTimetableTask);
    }

    /**
     * 批量删除排课任务
     * 
     * @param taskIds 需要删除的排课任务主键
     * @return 结果
     */
    @Override
    public int deleteEduTimetableTaskByTaskIds(Long[] taskIds)
    {
        return eduTimetableTaskMapper.deleteEduTimetableTaskByTaskIds(taskIds);
    }

    /**
     * 删除排课任务信息
     * 
     * @param taskId 排课任务主键
     * @return 结果
     */
    @Override
    public int deleteEduTimetableTaskByTaskId(Long taskId)
    {
        return eduTimetableTaskMapper.deleteEduTimetableTaskByTaskId(taskId);
    }
}
