package com.ruoyi.practice.mapper;

import java.util.List;
import com.ruoyi.practice.domain.EduPracticeProcess;

/**
 * 实践活动过程Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduPracticeProcessMapper 
{
    /**
     * 查询实践活动过程
     * 
     * @param processId 实践活动过程主键
     * @return 实践活动过程
     */
    public EduPracticeProcess selectEduPracticeProcessByProcessId(Long processId);

    /**
     * 查询实践活动过程列表
     * 
     * @param eduPracticeProcess 实践活动过程
     * @return 实践活动过程集合
     */
    public List<EduPracticeProcess> selectEduPracticeProcessList(EduPracticeProcess eduPracticeProcess);

    /**
     * 新增实践活动过程
     * 
     * @param eduPracticeProcess 实践活动过程
     * @return 结果
     */
    public int insertEduPracticeProcess(EduPracticeProcess eduPracticeProcess);

    /**
     * 修改实践活动过程
     * 
     * @param eduPracticeProcess 实践活动过程
     * @return 结果
     */
    public int updateEduPracticeProcess(EduPracticeProcess eduPracticeProcess);

    /**
     * 删除实践活动过程
     * 
     * @param processId 实践活动过程主键
     * @return 结果
     */
    public int deleteEduPracticeProcessByProcessId(Long processId);

    /**
     * 批量删除实践活动过程
     * 
     * @param processIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduPracticeProcessByProcessIds(Long[] processIds);
}
