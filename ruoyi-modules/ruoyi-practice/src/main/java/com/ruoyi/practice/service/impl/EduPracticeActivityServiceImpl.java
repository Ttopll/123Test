package com.ruoyi.practice.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.practice.mapper.EduPracticeActivityMapper;
import com.ruoyi.practice.domain.EduPracticeActivity;
import com.ruoyi.practice.service.IEduPracticeActivityService;

/**
 * 实践活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduPracticeActivityServiceImpl implements IEduPracticeActivityService 
{
    @Autowired
    private EduPracticeActivityMapper eduPracticeActivityMapper;

    /**
     * 查询实践活动
     * 
     * @param activityId 实践活动主键
     * @return 实践活动
     */
    @Override
    public EduPracticeActivity selectEduPracticeActivityByActivityId(String activityId)
    {
        return eduPracticeActivityMapper.selectEduPracticeActivityByActivityId(activityId);
    }

    /**
     * 查询实践活动列表
     * 
     * @param eduPracticeActivity 实践活动
     * @return 实践活动
     */
    @Override
    public List<EduPracticeActivity> selectEduPracticeActivityList(EduPracticeActivity eduPracticeActivity)
    {
        return eduPracticeActivityMapper.selectEduPracticeActivityList(eduPracticeActivity);
    }

    /**
     * 新增实践活动
     * 
     * @param eduPracticeActivity 实践活动
     * @return 结果
     */
    @Override
    public int insertEduPracticeActivity(EduPracticeActivity eduPracticeActivity)
    {
        eduPracticeActivity.setCreateTime(DateUtils.getNowDate());
        return eduPracticeActivityMapper.insertEduPracticeActivity(eduPracticeActivity);
    }

    /**
     * 修改实践活动
     * 
     * @param eduPracticeActivity 实践活动
     * @return 结果
     */
    @Override
    public int updateEduPracticeActivity(EduPracticeActivity eduPracticeActivity)
    {
        eduPracticeActivity.setUpdateTime(DateUtils.getNowDate());
        return eduPracticeActivityMapper.updateEduPracticeActivity(eduPracticeActivity);
    }

    /**
     * 批量删除实践活动
     * 
     * @param activityIds 需要删除的实践活动主键
     * @return 结果
     */
    @Override
    public int deleteEduPracticeActivityByActivityIds(String[] activityIds)
    {
        return eduPracticeActivityMapper.deleteEduPracticeActivityByActivityIds(activityIds);
    }

    /**
     * 删除实践活动信息
     * 
     * @param activityId 实践活动主键
     * @return 结果
     */
    @Override
    public int deleteEduPracticeActivityByActivityId(String activityId)
    {
        return eduPracticeActivityMapper.deleteEduPracticeActivityByActivityId(activityId);
    }
}
