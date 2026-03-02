package com.ruoyi.affair.mapper;

import java.util.List;
import com.ruoyi.affair.domain.EduAffairType;
import org.apache.ibatis.annotations.Param;

/**
 * 事务类型Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduAffairTypeMapper 
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
     * 删除事务类型
     * 
     * @param affairTypeId 事务类型主键
     * @param updateUserId 更新用户ID
     * @return 结果
     */
    public int deleteEduAffairTypeByAffairTypeId(@Param("array") Long affairTypeId, @Param("updateUserId") String updateUserId);

    /**
     * 批量删除事务类型
     * 
     * @param affairTypeIds 需要删除的数据主键集合
     * @param updateUserId 更新用户ID
     * @return 结果
     */
    public int deleteEduAffairTypeByAffairTypeIds(@Param("array") Long[] affairTypeIds, @Param("updateUserId") String updateUserId);
}
