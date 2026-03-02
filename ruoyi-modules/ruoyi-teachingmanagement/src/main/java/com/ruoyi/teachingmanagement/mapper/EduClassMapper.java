package com.ruoyi.teachingmanagement.mapper;

import com.ruoyi.teachingmanagement.domain.EduClass;

import java.util.List;


/**
 * 班级管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduClassMapper 
{
    /**
     * 查询班级管理
     * 
     * @param classId 班级管理主键
     * @return 班级管理
     */
    public EduClass selectEduClassByClassId(Long classId);

    /**
     * 查询班级管理列表
     * 
     * @param eduClass 班级管理
     * @return 班级管理集合
     */
    public List<EduClass> selectEduClassList(EduClass eduClass);

    /**
     * 新增班级管理
     * 
     * @param eduClass 班级管理
     * @return 结果
     */
    public int insertEduClass(EduClass eduClass);

    /**
     * 修改班级管理
     * 
     * @param eduClass 班级管理
     * @return 结果
     */
    public int updateEduClass(EduClass eduClass);

    /**
     * 删除班级管理
     * 
     * @param classId 班级管理主键
     * @return 结果
     */
    public int deleteEduClassByClassId(Long classId);

    /**
     * 批量删除班级管理
     * 
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduClassByClassIds(Long[] classIds);
}
