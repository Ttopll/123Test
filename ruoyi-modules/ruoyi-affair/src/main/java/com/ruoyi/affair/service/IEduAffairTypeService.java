package com.ruoyi.affair.service;

import java.util.List;
import com.ruoyi.affair.domain.EduAffairType;

/**
 * 事务类型Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduAffairTypeService 
{
    /**
     * 查询事务类型
     * 
     * @param affairTypeId 事务类型主键
     * @return 事务类型
     */
    public EduAffairType selectEduAffairTypeByAffairTypeId(Long affairTypeId);

    /**
     * 查询事务类型列表
     * 
     * @param eduAffairType 事务类型
     * @return 事务类型集合
     */
    public List<EduAffairType> selectEduAffairTypeList(EduAffairType eduAffairType);

    /**
     * 新增事务类型
     * 
     * @param eduAffairType 事务类型
     * @return 结果
     */
    public int insertEduAffairType(EduAffairType eduAffairType);

    /**
     * 修改事务类型
     * 
     * @param eduAffairType 事务类型
     * @return 结果
     */
    public int updateEduAffairType(EduAffairType eduAffairType);

    /**
     * 批量删除事务类型
     * 
     * @param affairTypeIds 需要删除的事务类型主键集合
     * @return 结果
     */
    public int deleteEduAffairTypeByAffairTypeIds(Long[] affairTypeIds, String updateUserId);

    /**
     * 删除事务类型信息
     * 
     * @param affairTypeId 事务类型主键
     * @return 结果
     */
    public int deleteEduAffairTypeByAffairTypeId(Long affairTypeId, String updateUserId);
}
