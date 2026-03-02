package com.ruoyi.teachingmanagement.service;

import java.util.List;
import com.ruoyi.teachingmanagement.domain.EduStudentCourse;

/**
 * 学生课程关联Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduStudentCourseService 
{
    /**
     * 查询学生课程关联
     * 
     * @param scId 学生课程关联主键
     * @return 学生课程关联
     */
    public EduStudentCourse selectEduStudentCourseByScId(Long scId);

    /**
     * 查询学生课程关联列表
     * 
     * @param eduStudentCourse 学生课程关联
     * @return 学生课程关联集合
     */
    public List<EduStudentCourse> selectEduStudentCourseList(EduStudentCourse eduStudentCourse);

    /**
     * 新增学生课程关联
     * 
     * @param eduStudentCourse 学生课程关联
     * @return 结果
     */
    public int insertEduStudentCourse(EduStudentCourse eduStudentCourse);

    /**
     * 修改学生课程关联
     * 
     * @param eduStudentCourse 学生课程关联
     * @return 结果
     */
    public int updateEduStudentCourse(EduStudentCourse eduStudentCourse);

    /**
     * 批量删除学生课程关联
     * 
     * @param scIds 需要删除的学生课程关联主键集合
     * @return 结果
     */
    public int deleteEduStudentCourseByScIds(Long[] scIds);

    /**
     * 删除学生课程关联信息
     * 
     * @param scId 学生课程关联主键
     * @return 结果
     */
    public int deleteEduStudentCourseByScId(Long scId);
}
