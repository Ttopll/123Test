package com.ruoyi.affair.service;

import java.util.List;
import com.ruoyi.affair.domain.EduAffairApply;

/**
 * 事务申请Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduAffairApplyService 
{
    /**
     * 查询事务申请
     * 
     * @param applyId 事务申请主键
     * @return 事务申请
     */
    public EduAffairApply selectEduAffairApplyByApplyId(String applyId);

    /**
     * 查询事务申请列表
     * 
     * @param eduAffairApply 事务申请
     * @return 事务申请集合
     */
    public List<EduAffairApply> selectEduAffairApplyList(EduAffairApply eduAffairApply);

    /**
     * 新增事务申请
     * 
     * @param eduAffairApply 事务申请
     * @return 结果
     */
    public int insertEduAffairApply(EduAffairApply eduAffairApply);

    /**
     * 修改事务申请
     * 
     * @param eduAffairApply 事务申请
     * @return 结果
     */
    public int updateEduAffairApply(EduAffairApply eduAffairApply);

    /**
     * 批量删除事务申请
     * 
     * @param applyIds 需要删除的事务申请主键集合
     * @return 结果
     */
    public int deleteEduAffairApplyByApplyIds(String[] applyIds, String updateUserId);

    /**
     * 删除事务申请信息
     * 
     * @param applyId 事务申请主键
     * @return 结果
     */
    public int deleteEduAffairApplyByApplyId(String applyId, String updateUserId);
}
