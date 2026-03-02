package com.ruoyi.affair.service;

import java.util.List;
import com.ruoyi.affair.domain.EduAffairUserCurrentStatus;

/**
 * 用户当前状态记录（全局可查）Service接口
 * 
 * @author ruoyi
 * @date 2026-01-28
 */
public interface IEduAffairUserCurrentStatusService 
{
    /**
     * 查询用户当前状态记录（全局可查）
     * 
     * @param currentStatusId 用户当前状态记录（全局可查）主键
     * @return 用户当前状态记录（全局可查）
     */
    public EduAffairUserCurrentStatus selectEduAffairUserCurrentStatusByCurrentStatusId(Long currentStatusId);

    /**
     * 查询用户当前状态记录（全局可查）
     *
     * @param userId 用户Id
     * @return 用户当前状态记录（全局可查）
     */
    public EduAffairUserCurrentStatus selectEduAffairUserCurrentStatusByUserId(Long userId);

    /**
     * 查询用户当前状态记录（全局可查）列表
     * 
     * @param eduAffairUserCurrentStatus 用户当前状态记录（全局可查）
     * @return 用户当前状态记录（全局可查）集合
     */
    public List<EduAffairUserCurrentStatus> selectEduAffairUserCurrentStatusList(EduAffairUserCurrentStatus eduAffairUserCurrentStatus);

    /**
     * 新增用户当前状态记录（全局可查）
     * 
     * @param eduAffairUserCurrentStatus 用户当前状态记录（全局可查）
     * @return 结果
     */
    public int insertEduAffairUserCurrentStatus(EduAffairUserCurrentStatus eduAffairUserCurrentStatus);

    /**
     * 修改用户当前状态记录（全局可查）
     * 
     * @param eduAffairUserCurrentStatus 用户当前状态记录（全局可查）
     * @return 结果
     */
    public int updateEduAffairUserCurrentStatus(EduAffairUserCurrentStatus eduAffairUserCurrentStatus);

    /**
     * 批量删除用户当前状态记录（全局可查）
     * 
     * @param currentStatusIds 需要删除的用户当前状态记录（全局可查）主键集合
     * @return 结果
     */
    public int deleteEduAffairUserCurrentStatusByCurrentStatusIds(Long[] currentStatusIds);

    /**
     * 删除用户当前状态记录（全局可查）信息
     * 
     * @param currentStatusId 用户当前状态记录（全局可查）主键
     * @return 结果
     */
    public int deleteEduAffairUserCurrentStatusByCurrentStatusId(Long currentStatusId);
}
