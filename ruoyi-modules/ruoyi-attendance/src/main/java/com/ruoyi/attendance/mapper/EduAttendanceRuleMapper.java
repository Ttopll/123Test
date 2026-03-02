package com.ruoyi.attendance.mapper;

import java.util.List;
import com.ruoyi.attendance.domain.EduAttendanceRule;

/**
 * 考勤规则Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduAttendanceRuleMapper 
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
     * 删除考勤规则
     * 
     * @param ruleId 考勤规则主键
     * @return 结果
     */
    public int deleteEduAttendanceRuleByRuleId(Long ruleId);

    /**
     * 批量删除考勤规则
     * 
     * @param ruleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAttendanceRuleByRuleIds(Long[] ruleIds);
}
