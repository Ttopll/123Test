package com.ruoyi.practice.mapper;

import java.util.List;
import com.ruoyi.practice.domain.EduPracticeSignup;

/**
 * 实践活动报名Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduPracticeSignupMapper 
{
    /**
     * 查询实践活动报名
     * 
     * @param signupId 实践活动报名主键
     * @return 实践活动报名
     */
    public EduPracticeSignup selectEduPracticeSignupBySignupId(Long signupId);

    /**
     * 查询实践活动报名列表
     * 
     * @param eduPracticeSignup 实践活动报名
     * @return 实践活动报名集合
     */
    public List<EduPracticeSignup> selectEduPracticeSignupList(EduPracticeSignup eduPracticeSignup);

    /**
     * 新增实践活动报名
     * 
     * @param eduPracticeSignup 实践活动报名
     * @return 结果
     */
    public int insertEduPracticeSignup(EduPracticeSignup eduPracticeSignup);

    /**
     * 修改实践活动报名
     * 
     * @param eduPracticeSignup 实践活动报名
     * @return 结果
     */
    public int updateEduPracticeSignup(EduPracticeSignup eduPracticeSignup);

    /**
     * 删除实践活动报名
     * 
     * @param signupId 实践活动报名主键
     * @return 结果
     */
    public int deleteEduPracticeSignupBySignupId(Long signupId);

    /**
     * 批量删除实践活动报名
     * 
     * @param signupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduPracticeSignupBySignupIds(Long[] signupIds);
}
