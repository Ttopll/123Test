package com.ruoyi.teachingmanagement.service;

import com.ruoyi.teachingmanagement.domain.EduCourse;

import java.util.List;


/**
 * 课程管理Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduCourseService 
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
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的课程管理主键集合
     * @return 结果
     */
    public int deleteEduCourseByCourseIds(Long[] courseIds);

    /**
     * 删除课程管理信息
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    public int deleteEduCourseByCourseId(Long courseId);
}
