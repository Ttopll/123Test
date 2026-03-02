package com.ruoyi.teachingmanagement.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.teachingmanagement.domain.EduClass;
import com.ruoyi.teachingmanagement.mapper.EduClassMapper;
import com.ruoyi.teachingmanagement.service.IEduClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 班级管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduClassServiceImpl implements IEduClassService
{
    @Autowired
    private EduClassMapper eduClassMapper;

    /**
     * 查询班级管理
     * 
     * @param classId 班级管理主键
     * @return 班级管理
     */
    @Override
    public EduClass selectEduClassByClassId(Long classId)
    {
        return eduClassMapper.selectEduClassByClassId(classId);
    }

    /**
     * 查询班级管理列表
     * 
     * @param eduClass 班级管理
     * @return 班级管理
     */
    @Override
    public List<EduClass> selectEduClassList(EduClass eduClass)
    {
        return eduClassMapper.selectEduClassList(eduClass);
    }

    /**
     * 新增班级管理
     * 
     * @param eduClass 班级管理
     * @return 结果
     */
    @Override
    public int insertEduClass(EduClass eduClass)
    {
        eduClass.setCreateTime(DateUtils.getNowDate());
        return eduClassMapper.insertEduClass(eduClass);
    }

    /**
     * 修改班级管理
     * 
     * @param eduClass 班级管理
     * @return 结果
     */
    @Override
    public int updateEduClass(EduClass eduClass)
    {
        eduClass.setUpdateTime(DateUtils.getNowDate());
        return eduClassMapper.updateEduClass(eduClass);
    }

    /**
     * 批量删除班级管理
     * 
     * @param classIds 需要删除的班级管理主键
     * @return 结果
     */
    @Override
    public int deleteEduClassByClassIds(Long[] classIds)
    {
        return eduClassMapper.deleteEduClassByClassIds(classIds);
    }

    /**
     * 删除班级管理信息
     * 
     * @param classId 班级管理主键
     * @return 结果
     */
    @Override
    public int deleteEduClassByClassId(Long classId)
    {
        return eduClassMapper.deleteEduClassByClassId(classId);
    }
}
