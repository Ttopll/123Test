package com.ruoyi.affair.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.affair.mapper.EduAffairTypeMapper;
import com.ruoyi.affair.domain.EduAffairType;
import com.ruoyi.affair.service.IEduAffairTypeService;

/**
 * 事务类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAffairTypeServiceImpl implements IEduAffairTypeService 
{
    @Autowired
    private EduAffairTypeMapper eduAffairTypeMapper;

    /**
     * 查询事务类型
     * 
     * @param affairTypeId 事务类型主键
     * @return 事务类型
     */
    @Override
    public EduAffairType selectEduAffairTypeByAffairTypeId(Long affairTypeId)
    {
        return eduAffairTypeMapper.selectEduAffairTypeByAffairTypeId(affairTypeId);
    }

    /**
     * 查询事务类型列表
     * 
     * @param eduAffairType 事务类型
     * @return 事务类型
     */
    @Override
    public List<EduAffairType> selectEduAffairTypeList(EduAffairType eduAffairType)
    {
        return eduAffairTypeMapper.selectEduAffairTypeList(eduAffairType);
    }

    /**
     * 新增事务类型
     * 
     * @param eduAffairType 事务类型
     * @return 结果
     */
    @Override
    public int insertEduAffairType(EduAffairType eduAffairType)
    {
        return eduAffairTypeMapper.insertEduAffairType(eduAffairType);
    }

    /**
     * 修改事务类型
     * 
     * @param eduAffairType 事务类型
     * @return 结果
     */
    @Override
    public int updateEduAffairType(EduAffairType eduAffairType)
    {
        return eduAffairTypeMapper.updateEduAffairType(eduAffairType);
    }

    /**
     * 批量删除事务类型
     * 
     * @param affairTypeIds 需要删除的事务类型主键
     * @return 结果
     */
    @Override
    public int deleteEduAffairTypeByAffairTypeIds(Long[] affairTypeIds, String updateUserId)
    {
        return eduAffairTypeMapper.deleteEduAffairTypeByAffairTypeIds(affairTypeIds, updateUserId);
    }

    /**
     * 删除事务类型信息
     * 
     * @param affairTypeId 事务类型主键
     * @return 结果
     */
    @Override
    public int deleteEduAffairTypeByAffairTypeId(Long affairTypeId, String updateUserId)
    {
        return eduAffairTypeMapper.deleteEduAffairTypeByAffairTypeId(affairTypeId, updateUserId);
    }
}
