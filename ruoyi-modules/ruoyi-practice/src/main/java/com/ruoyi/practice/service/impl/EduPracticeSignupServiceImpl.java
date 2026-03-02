package com.ruoyi.practice.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.practice.mapper.EduPracticeSignupMapper;
import com.ruoyi.practice.domain.EduPracticeSignup;
import com.ruoyi.practice.service.IEduPracticeSignupService;

/**
 * 实践活动报名Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduPracticeSignupServiceImpl implements IEduPracticeSignupService 
{
    @Autowired
    private EduPracticeSignupMapper eduPracticeSignupMapper;

    /**
     * 查询实践活动报名
     * 
     * @param signupId 实践活动报名主键
     * @return 实践活动报名
     */
    @Override
    public EduPracticeSignup selectEduPracticeSignupBySignupId(Long signupId)
    {
        return eduPracticeSignupMapper.selectEduPracticeSignupBySignupId(signupId);
    }

    /**
     * 查询实践活动报名列表
     * 
     * @param eduPracticeSignup 实践活动报名
     * @return 实践活动报名
     */
    @Override
    public List<EduPracticeSignup> selectEduPracticeSignupList(EduPracticeSignup eduPracticeSignup)
    {
        return eduPracticeSignupMapper.selectEduPracticeSignupList(eduPracticeSignup);
    }

    /**
     * 新增实践活动报名
     * 
     * @param eduPracticeSignup 实践活动报名
     * @return 结果
     */
    @Override
    public int insertEduPracticeSignup(EduPracticeSignup eduPracticeSignup)
    {
        eduPracticeSignup.setCreateTime(DateUtils.getNowDate());
        return eduPracticeSignupMapper.insertEduPracticeSignup(eduPracticeSignup);
    }

    /**
     * 修改实践活动报名
     * 
     * @param eduPracticeSignup 实践活动报名
     * @return 结果
     */
    @Override
    public int updateEduPracticeSignup(EduPracticeSignup eduPracticeSignup)
    {
        eduPracticeSignup.setUpdateTime(DateUtils.getNowDate());
        return eduPracticeSignupMapper.updateEduPracticeSignup(eduPracticeSignup);
    }

    /**
     * 批量删除实践活动报名
     * 
     * @param signupIds 需要删除的实践活动报名主键
     * @return 结果
     */
    @Override
    public int deleteEduPracticeSignupBySignupIds(Long[] signupIds)
    {
        return eduPracticeSignupMapper.deleteEduPracticeSignupBySignupIds(signupIds);
    }

    /**
     * 删除实践活动报名信息
     * 
     * @param signupId 实践活动报名主键
     * @return 结果
     */
    @Override
    public int deleteEduPracticeSignupBySignupId(Long signupId)
    {
        return eduPracticeSignupMapper.deleteEduPracticeSignupBySignupId(signupId);
    }
}
