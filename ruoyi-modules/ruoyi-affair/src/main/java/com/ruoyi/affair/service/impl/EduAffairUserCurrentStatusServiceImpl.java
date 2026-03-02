package com.ruoyi.affair.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.affair.mapper.EduAffairUserCurrentStatusMapper;
import com.ruoyi.affair.domain.EduAffairUserCurrentStatus;
import com.ruoyi.affair.service.IEduAffairUserCurrentStatusService;

/**
 * 用户当前状态记录（全局可查）Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-28
 */
@Service
public class EduAffairUserCurrentStatusServiceImpl implements IEduAffairUserCurrentStatusService 
{
    @Autowired
    private EduAffairUserCurrentStatusMapper eduAffairUserCurrentStatusMapper;

    /**
     * 查询用户当前状态记录（全局可查）
     * 
     * @param currentStatusId 用户当前状态记录（全局可查）主键
     * @return 用户当前状态记录（全局可查）
     */
    @Override
    public EduAffairUserCurrentStatus selectEduAffairUserCurrentStatusByCurrentStatusId(Long currentStatusId)
    {
        return eduAffairUserCurrentStatusMapper.selectEduAffairUserCurrentStatusByCurrentStatusId(currentStatusId);
    }

    @Override
    public EduAffairUserCurrentStatus selectEduAffairUserCurrentStatusByUserId(Long userId){
        return eduAffairUserCurrentStatusMapper.selectEduAffairUserCurrentStatusByUserId(userId);
    }

    /**
     * 查询用户当前状态记录（全局可查）列表
     * 
     * @param eduAffairUserCurrentStatus 用户当前状态记录（全局可查）
     * @return 用户当前状态记录（全局可查）
     */
    @Override
    public List<EduAffairUserCurrentStatus> selectEduAffairUserCurrentStatusList(EduAffairUserCurrentStatus eduAffairUserCurrentStatus)
    {
        return eduAffairUserCurrentStatusMapper.selectEduAffairUserCurrentStatusList(eduAffairUserCurrentStatus);
    }

    /**
     * 新增用户当前状态记录（全局可查）
     * 
     * @param eduAffairUserCurrentStatus 用户当前状态记录（全局可查）
     * @return 结果
     */
    @Override
    public int insertEduAffairUserCurrentStatus(EduAffairUserCurrentStatus eduAffairUserCurrentStatus)
    {
        eduAffairUserCurrentStatus.setCreateTime(DateUtils.getNowDate());
        return eduAffairUserCurrentStatusMapper.insertEduAffairUserCurrentStatus(eduAffairUserCurrentStatus);
    }

    /**
     * 修改用户当前状态记录（全局可查）
     * 
     * @param eduAffairUserCurrentStatus 用户当前状态记录（全局可查）
     * @return 结果
     */
    @Override
    public int updateEduAffairUserCurrentStatus(EduAffairUserCurrentStatus eduAffairUserCurrentStatus)
    {
        eduAffairUserCurrentStatus.setUpdateTime(DateUtils.getNowDate());
        return eduAffairUserCurrentStatusMapper.updateEduAffairUserCurrentStatus(eduAffairUserCurrentStatus);
    }

    /**
     * 批量删除用户当前状态记录（全局可查）
     * 
     * @param currentStatusIds 需要删除的用户当前状态记录（全局可查）主键
     * @return 结果
     */
    @Override
    public int deleteEduAffairUserCurrentStatusByCurrentStatusIds(Long[] currentStatusIds)
    {
        return eduAffairUserCurrentStatusMapper.deleteEduAffairUserCurrentStatusByCurrentStatusIds(currentStatusIds);
    }

    /**
     * 删除用户当前状态记录（全局可查）信息
     * 
     * @param currentStatusId 用户当前状态记录（全局可查）主键
     * @return 结果
     */
    @Override
    public int deleteEduAffairUserCurrentStatusByCurrentStatusId(Long currentStatusId)
    {
        return eduAffairUserCurrentStatusMapper.deleteEduAffairUserCurrentStatusByCurrentStatusId(currentStatusId);
    }
}
