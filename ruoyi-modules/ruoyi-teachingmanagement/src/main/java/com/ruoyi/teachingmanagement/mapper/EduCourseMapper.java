package com.ruoyi.teachingmanagement.mapper;

import com.ruoyi.teachingmanagement.domain.EduCourse;

import java.util.List;


/**
 * 课程管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduCourseMapper 
{
    /**
     * 查询课程管理
     * 
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    public EduCourse selectEduCourseByCourseId(Long courseId);

    /**
     * 查询课程管理列表
     * 
     * @param eduCourse 课程管理
     * @return 课程管理集合
     */
    public List<EduCourse> selectEduCourseList(EduCourse eduCourse);

    /**
     * 新增课程管理
     * 
     * @param eduCourse 课程管理
     * @return 结果
     */
    public int insertEduCourse(EduCourse eduCourse);

    /**
     * 修改课程管理
     * 
     * @param eduCourse 课程管理
     * @return 结果
     */
    public int updateEduCourse(EduCourse eduCourse);

    /**
     * 删除课程管理
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    public int deleteEduCourseByCourseId(Long courseId);

    /**
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCourseByCourseIds(Long[] courseIds);
}
