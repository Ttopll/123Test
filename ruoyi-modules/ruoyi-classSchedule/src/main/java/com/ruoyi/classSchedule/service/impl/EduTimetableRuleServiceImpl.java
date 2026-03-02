package com.ruoyi.classSchedule.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classSchedule.mapper.EduTimetableRuleMapper;
import com.ruoyi.classSchedule.domain.EduTimetableRule;
import com.ruoyi.classSchedule.service.IEduTimetableRuleService;

/**
 * 排课规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@Service
public class EduTimetableRuleServiceImpl implements IEduTimetableRuleService 
{
    @Autowired
    private EduTimetableRuleMapper eduTimetableRuleMapper;

    /**
     * 查询排课规则
     * 
     * @param ruleId 排课规则主键
     * @return 排课规则
     */
    @Override
    public EduTimetableRule selectEduTimetableRuleByRuleId(Long ruleId)
    {
        return eduTimetableRuleMapper.selectEduTimetableRuleByRuleId(ruleId);
    }

    /**
     * 查询排课规则列表
     * 
     * @param eduTimetableRule 排课规则
     * @return 排课规则
     */
    @Override
    public List<EduTimetableRule> selectEduTimetableRuleList(EduTimetableRule eduTimetableRule)
    {
        return eduTimetableRuleMapper.selectEduTimetableRuleList(eduTimetableRule);
    }

    /**
     * 新增排课规则
     * 
     * @param eduTimetableRule 排课规则
     * @return 结果
     */
    @Override
    public int insertEduTimetableRule(EduTimetableRule eduTimetableRule)
    {
        eduTimetableRule.setCreateTime(DateUtils.getNowDate());
        return eduTimetableRuleMapper.insertEduTimetableRule(eduTimetableRule);
    }

    /**
     * 修改排课规则
     * 
     * @param eduTimetableRule 排课规则
     * @return 结果
     */
    @Override
    public int updateEduTimetableRule(EduTimetableRule eduTimetableRule)
    {
        eduTimetableRule.setUpdateTime(DateUtils.getNowDate());
        return eduTimetableRuleMapper.updateEduTimetableRule(eduTimetableRule);
    }

    /**
     * 批量删除排课规则
     * 
     * @param ruleIds 需要删除的排课规则主键
     * @return 结果
     */
    @Override
    public int deleteEduTimetableRuleByRuleIds(Long[] ruleIds)
    {
        return eduTimetableRuleMapper.deleteEduTimetableRuleByRuleIds(ruleIds);
    }

    /**
     * 删除排课规则信息
     * 
     * @param ruleId 排课规则主键
     * @return 结果
     */
    @Override
    public int deleteEduTimetableRuleByRuleId(Long ruleId)
    {
        return eduTimetableRuleMapper.deleteEduTimetableRuleByRuleId(ruleId);
    }
}
