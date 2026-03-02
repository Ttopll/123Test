package com.ruoyi.teachingmanagement.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.teachingmanagement.domain.EduCourse;
import com.ruoyi.teachingmanagement.mapper.EduCourseMapper;
import com.ruoyi.teachingmanagement.service.IEduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 课程管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduCourseServiceImpl implements IEduCourseService
{
    @Autowired
    private EduCourseMapper eduCourseMapper;

    /**
     * 查询课程管理
     * 
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    @Override
    public EduCourse selectEduCourseByCourseId(Long courseId)
    {
        return eduCourseMapper.selectEduCourseByCourseId(courseId);
    }

    /**
     * 查询课程管理列表
     * 
     * @param eduCourse 课程管理
     * @return 课程管理
     */
    @Override
    public List<EduCourse> selectEduCourseList(EduCourse eduCourse)
    {
        return eduCourseMapper.selectEduCourseList(eduCourse);
    }

    /**
     * 新增课程管理
     * 
     * @param eduCourse 课程管理
     * @return 结果
     */
    @Override
    public int insertEduCourse(EduCourse eduCourse)
    {
        eduCourse.setCreateTime(DateUtils.getNowDate());
        return eduCourseMapper.insertEduCourse(eduCourse);
    }

    /**
     * 修改课程管理
     * 
     * @param eduCourse 课程管理
     * @return 结果
     */
    @Override
    public int updateEduCourse(EduCourse eduCourse)
    {
        eduCourse.setUpdateTime(DateUtils.getNowDate());
        return eduCourseMapper.updateEduCourse(eduCourse);
    }

    /**
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的课程管理主键
     * @return 结果
     */
    @Override
    public int deleteEduCourseByCourseIds(Long[] courseIds)
    {
        return eduCourseMapper.deleteEduCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程管理信息
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    @Override
    public int deleteEduCourseByCourseId(Long courseId)
    {
        return eduCourseMapper.deleteEduCourseByCourseId(courseId);
    }
}
