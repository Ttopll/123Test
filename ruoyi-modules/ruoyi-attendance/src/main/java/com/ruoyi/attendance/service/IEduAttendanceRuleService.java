package com.ruoyi.attendance.service;

import java.util.List;
import com.ruoyi.attendance.domain.EduAttendanceRule;

/**
 * 考勤规则Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduAttendanceRuleService 
{
    /**
     * 查询考勤规则
     * 
     * @param ruleId 考勤规则主键
     * @return 考勤规则
     */
    public EduAttendanceRule selectEduAttendanceRuleByRuleId(Long ruleId);

    /**
     * 查询考勤规则列表
     * 
     * @param eduAttendanceRule 考勤规则
     * @return 考勤规则集合
     */
    public List<EduAttendanceRule> selectEduAttendanceRuleList(EduAttendanceRule eduAttendanceRule);

    /**
     * 新增考勤规则
     * 
     * @param eduAttendanceRule 考勤规则
     * @return 结果
     */
    public int insertEduAttendanceRule(EduAttendanceRule eduAttendanceRule);

    /**
     * 修改考勤规则
     * 
     * @param eduAttendanceRule 考勤规则
     * @return 结果
     */
    public int updateEduAttendanceRule(EduAttendanceRule eduAttendanceRule);

    /**
     * 批量删除考勤规则
     * 
     * @param ruleIds 需要删除的考勤规则主键集合
     * @return 结果
     */
    public int deleteEduAttendanceRuleByRuleIds(Long[] ruleIds);

    /**
     * 删除考勤规则信息
     * 
     * @param ruleId 考勤规则主键
     * @return 结果
     */
    public int deleteEduAttendanceRuleByRuleId(Long ruleId);
}
