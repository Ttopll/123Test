package com.ruoyi.teachingmanagement.mapper;

import java.util.List;
import com.ruoyi.teachingmanagement.domain.EduTeacherCourse;

/**
 * 教师课程关联Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduTeacherCourseMapper 
{
    /**
     * 查询教师课程关联
     * 
     * @param tcId 教师课程关联主键
     * @return 教师课程关联
     */
    public EduTeacherCourse selectEduTeacherCourseByTcId(Long tcId);

    /**
     * 查询教师课程关联列表
     * 
     * @param eduTeacherCourse 教师课程关联
     * @return 教师课程关联集合
     */
    public List<EduTeacherCourse> selectEduTeacherCourseList(EduTeacherCourse eduTeacherCourse);

    /**
     * 新增教师课程关联
     * 
     * @param eduTeacherCourse 教师课程关联
     * @return 结果
     */
    public int insertEduTeacherCourse(EduTeacherCourse eduTeacherCourse);

    /**
     * 修改教师课程关联
     * 
     * @param eduTeacherCourse 教师课程关联
     * @return 结果
     */
    public int updateEduTeacherCourse(EduTeacherCourse eduTeacherCourse);

    /**
     * 删除教师课程关联
     * 
     * @param tcId 教师课程关联主键
     * @return 结果
     */
    public int deleteEduTeacherCourseByTcId(Long tcId);

    /**
     * 批量删除教师课程关联
     * 
     * @param tcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduTeacherCourseByTcIds(Long[] tcIds);
}
