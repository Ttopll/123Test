package com.ruoyi.practice.service;

import java.util.List;
import com.ruoyi.practice.domain.EduPracticeActivity;

/**
 * 实践活动Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduPracticeActivityService 
{
    /**
     * 查询实践活动
     * 
     * @param activityId 实践活动主键
     * @return 实践活动
     */
    public EduPracticeActivity selectEduPracticeActivityByActivityId(String activityId);

    /**
     * 查询实践活动列表
     * 
     * @param eduPracticeActivity 实践活动
     * @return 实践活动集合
     */
    public List<EduPracticeActivity> selectEduPracticeActivityList(EduPracticeActivity eduPracticeActivity);

    /**
     * 新增实践活动
     * 
     * @param eduPracticeActivity 实践活动
     * @return 结果
     */
    public int insertEduPracticeActivity(EduPracticeActivity eduPracticeActivity);

    /**
     * 修改实践活动
     * 
     * @param eduPracticeActivity 实践活动
     * @return 结果
     */
    public int updateEduPracticeActivity(EduPracticeActivity eduPracticeActivity);

    /**
     * 批量删除实践活动
     * 
     * @param activityIds 需要删除的实践活动主键集合
     * @return 结果
     */
    public int deleteEduPracticeActivityByActivityIds(String[] activityIds);

    /**
     * 删除实践活动信息
     * 
     * @param activityId 实践活动主键
     * @return 结果
     */
    public int deleteEduPracticeActivityByActivityId(String activityId);
}
