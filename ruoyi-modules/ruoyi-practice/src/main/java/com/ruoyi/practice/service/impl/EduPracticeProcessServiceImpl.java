package com.ruoyi.practice.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.practice.mapper.EduPracticeProcessMapper;
import com.ruoyi.practice.domain.EduPracticeProcess;
import com.ruoyi.practice.service.IEduPracticeProcessService;

/**
 * 实践活动过程Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduPracticeProcessServiceImpl implements IEduPracticeProcessService 
{
    @Autowired
    private EduPracticeProcessMapper eduPracticeProcessMapper;

    /**
     * 查询实践活动过程
     * 
     * @param processId 实践活动过程主键
     * @return 实践活动过程
     */
    @Override
    public EduPracticeProcess selectEduPracticeProcessByProcessId(Long processId)
    {
        return eduPracticeProcessMapper.selectEduPracticeProcessByProcessId(processId);
    }

    /**
     * 查询实践活动过程列表
     * 
     * @param eduPracticeProcess 实践活动过程
     * @return 实践活动过程
     */
    @Override
    public List<EduPracticeProcess> selectEduPracticeProcessList(EduPracticeProcess eduPracticeProcess)
    {
        return eduPracticeProcessMapper.selectEduPracticeProcessList(eduPracticeProcess);
    }

    /**
     * 新增实践活动过程
     * 
     * @param eduPracticeProcess 实践活动过程
     * @return 结果
     */
    @Override
    public int insertEduPracticeProcess(EduPracticeProcess eduPracticeProcess)
    {
        eduPracticeProcess.setCreateTime(DateUtils.getNowDate());
        return eduPracticeProcessMapper.insertEduPracticeProcess(eduPracticeProcess);
    }

    /**
     * 修改实践活动过程
     * 
     * @param eduPracticeProcess 实践活动过程
     * @return 结果
     */
    @Override
    public int updateEduPracticeProcess(EduPracticeProcess eduPracticeProcess)
    {
        eduPracticeProcess.setUpdateTime(DateUtils.getNowDate());
        return eduPracticeProcessMapper.updateEduPracticeProcess(eduPracticeProcess);
    }

    /**
     * 批量删除实践活动过程
     * 
     * @param processIds 需要删除的实践活动过程主键
     * @return 结果
     */
    @Override
    public int deleteEduPracticeProcessByProcessIds(Long[] processIds)
    {
        return eduPracticeProcessMapper.deleteEduPracticeProcessByProcessIds(processIds);
    }

    /**
     * 删除实践活动过程信息
     * 
     * @param processId 实践活动过程主键
     * @return 结果
     */
    @Override
    public int deleteEduPracticeProcessByProcessId(Long processId)
    {
        return eduPracticeProcessMapper.deleteEduPracticeProcessByProcessId(processId);
    }
}
