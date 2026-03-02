package com.ruoyi.teachingmanagement.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teachingmanagement.mapper.EduStudentCourseMapper;
import com.ruoyi.teachingmanagement.domain.EduStudentCourse;
import com.ruoyi.teachingmanagement.service.IEduStudentCourseService;

/**
 * 学生课程关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduStudentCourseServiceImpl implements IEduStudentCourseService 
{
    @Autowired
    private EduStudentCourseMapper eduStudentCourseMapper;

    /**
     * 查询学生课程关联
     * 
     * @param scId 学生课程关联主键
     * @return 学生课程关联
     */
    @Override
    public EduStudentCourse selectEduStudentCourseByScId(Long scId)
    {
        return eduStudentCourseMapper.selectEduStudentCourseByScId(scId);
    }

    /**
     * 查询学生课程关联列表
     * 
     * @param eduStudentCourse 学生课程关联
     * @return 学生课程关联
     */
    @Override
    public List<EduStudentCourse> selectEduStudentCourseList(EduStudentCourse eduStudentCourse)
    {
        return eduStudentCourseMapper.selectEduStudentCourseList(eduStudentCourse);
    }

    /**
     * 新增学生课程关联
     * 
     * @param eduStudentCourse 学生课程关联
     * @return 结果
     */
    @Override
    public int insertEduStudentCourse(EduStudentCourse eduStudentCourse)
    {
        eduStudentCourse.setCreateTime(DateUtils.getNowDate());
        return eduStudentCourseMapper.insertEduStudentCourse(eduStudentCourse);
    }

    /**
     * 修改学生课程关联
     * 
     * @param eduStudentCourse 学生课程关联
     * @return 结果
     */
    @Override
    public int updateEduStudentCourse(EduStudentCourse eduStudentCourse)
    {
        eduStudentCourse.setUpdateTime(DateUtils.getNowDate());
        return eduStudentCourseMapper.updateEduStudentCourse(eduStudentCourse);
    }

    /**
     * 批量删除学生课程关联
     * 
     * @param scIds 需要删除的学生课程关联主键
     * @return 结果
     */
    @Override
    public int deleteEduStudentCourseByScIds(Long[] scIds)
    {
        return eduStudentCourseMapper.deleteEduStudentCourseByScIds(scIds);
    }

    /**
     * 删除学生课程关联信息
     * 
     * @param scId 学生课程关联主键
     * @return 结果
     */
    @Override
    public int deleteEduStudentCourseByScId(Long scId)
    {
        return eduStudentCourseMapper.deleteEduStudentCourseByScId(scId);
    }
}
