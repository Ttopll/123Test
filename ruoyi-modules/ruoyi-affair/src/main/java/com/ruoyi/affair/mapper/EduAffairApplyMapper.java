package com.ruoyi.affair.mapper;

import java.util.List;
import com.ruoyi.affair.domain.EduAffairApply;
import org.apache.ibatis.annotations.Param;

/**
 * 事务申请Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduAffairApplyMapper 
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
     * 删除事务申请
     * 
     * @param applyId 事务申请主键
     * @param updateUserId 更新用户ID
     * @return 结果
     */
    public int deleteEduAffairApplyByApplyId(@Param("array") String applyId, @Param("updateUserId") String updateUserId);

    /**
     * 批量删除事务申请
     * 
     * @param applyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAffairApplyByApplyIds(@Param("array") String[] applyIds, @Param("updateUserId") String updateUserId);
}
