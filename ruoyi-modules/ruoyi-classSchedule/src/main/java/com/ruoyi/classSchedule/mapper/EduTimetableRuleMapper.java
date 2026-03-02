package com.ruoyi.classSchedule.mapper;

import java.util.List;
import com.ruoyi.classSchedule.domain.EduTimetableRule;

/**
 * 排课规则Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public interface EduTimetableRuleMapper 
{
    /**
     * 查询排课规则
     * 
     * @param ruleId 排课规则主键
     * @return 排课规则
     */
    public EduTimetableRule selectEduTimetableRuleByRuleId(Long ruleId);

    /**
     * 查询排课规则列表
     * 
     * @param eduTimetableRule 排课规则
     * @return 排课规则集合
     */
    public List<EduTimetableRule> selectEduTimetableRuleList(EduTimetableRule eduTimetableRule);

    /**
     * 新增排课规则
     * 
     * @param eduTimetableRule 排课规则
     * @return 结果
     */
    public int insertEduTimetableRule(EduTimetableRule eduTimetableRule);

    /**
     * 修改排课规则
     * 
     * @param eduTimetableRule 排课规则
     * @return 结果
     */
    public int updateEduTimetableRule(EduTimetableRule eduTimetableRule);

    /**
     * 删除排课规则
     * 
     * @param ruleId 排课规则主键
     * @return 结果
     */
    public int deleteEduTimetableRuleByRuleId(Long ruleId);

    /**
     * 批量删除排课规则
     * 
     * @param ruleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduTimetableRuleByRuleIds(Long[] ruleIds);
}
