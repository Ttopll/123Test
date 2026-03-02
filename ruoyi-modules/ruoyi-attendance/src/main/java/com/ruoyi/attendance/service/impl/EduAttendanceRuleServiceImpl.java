package com.ruoyi.attendance.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.attendance.mapper.EduAttendanceRuleMapper;
import com.ruoyi.attendance.domain.EduAttendanceRule;
import com.ruoyi.attendance.service.IEduAttendanceRuleService;

/**
 * 考勤规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAttendanceRuleServiceImpl implements IEduAttendanceRuleService 
{
    @Autowired
    private EduAttendanceRuleMapper eduAttendanceRuleMapper;

    /**
     * 查询考勤规则
     * 
     * @param ruleId 考勤规则主键
     * @return 考勤规则
     */
    @Override
    public EduAttendanceRule selectEduAttendanceRuleByRuleId(Long ruleId)
    {
        return eduAttendanceRuleMapper.selectEduAttendanceRuleByRuleId(ruleId);
    }

    /**
     * 查询考勤规则列表
     * 
     * @param eduAttendanceRule 考勤规则
     * @return 考勤规则
     */
    @Override
    public List<EduAttendanceRule> selectEduAttendanceRuleList(EduAttendanceRule eduAttendanceRule)
    {
        return eduAttendanceRuleMapper.selectEduAttendanceRuleList(eduAttendanceRule);
    }

    /**
     * 新增考勤规则
     * 
     * @param eduAttendanceRule 考勤规则
     * @return 结果
     */
    @Override
    public int insertEduAttendanceRule(EduAttendanceRule eduAttendanceRule)
    {
        eduAttendanceRule.setCreateTime(DateUtils.getNowDate());
        return eduAttendanceRuleMapper.insertEduAttendanceRule(eduAttendanceRule);
    }

    /**
     * 修改考勤规则
     * 
     * @param eduAttendanceRule 考勤规则
     * @return 结果
     */
    @Override
    public int updateEduAttendanceRule(EduAttendanceRule eduAttendanceRule)
    {
        eduAttendanceRule.setUpdateTime(DateUtils.getNowDate());
        return eduAttendanceRuleMapper.updateEduAttendanceRule(eduAttendanceRule);
    }

    /**
     * 批量删除考勤规则
     * 
     * @param ruleIds 需要删除的考勤规则主键
     * @return 结果
     */
    @Override
    public int deleteEduAttendanceRuleByRuleIds(Long[] ruleIds)
    {
        return eduAttendanceRuleMapper.deleteEduAttendanceRuleByRuleIds(ruleIds);
    }

    /**
     * 删除考勤规则信息
     * 
     * @param ruleId 考勤规则主键
     * @return 结果
     */
    @Override
    public int deleteEduAttendanceRuleByRuleId(Long ruleId)
    {
        return eduAttendanceRuleMapper.deleteEduAttendanceRuleByRuleId(ruleId);
    }
}
