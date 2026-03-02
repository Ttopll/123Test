package com.ruoyi.teachingmanagement.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.teachingmanagement.mapper.EduTeacherCourseMapper;
import com.ruoyi.teachingmanagement.domain.EduTeacherCourse;
import com.ruoyi.teachingmanagement.service.IEduTeacherCourseService;

/**
 * 教师课程关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduTeacherCourseServiceImpl implements IEduTeacherCourseService 
{
    @Autowired
    private EduTeacherCourseMapper eduTeacherCourseMapper;

    /**
     * 查询教师课程关联
     * 
     * @param tcId 教师课程关联主键
     * @return 教师课程关联
     */
    @Override
    public EduTeacherCourse selectEduTeacherCourseByTcId(Long tcId)
    {
        return eduTeacherCourseMapper.selectEduTeacherCourseByTcId(tcId);
    }

    /**
     * 查询教师课程关联列表
     * 
     * @param eduTeacherCourse 教师课程关联
     * @return 教师课程关联
     */
    @Override
    public List<EduTeacherCourse> selectEduTeacherCourseList(EduTeacherCourse eduTeacherCourse)
    {
        return eduTeacherCourseMapper.selectEduTeacherCourseList(eduTeacherCourse);
    }

    /**
     * 新增教师课程关联
     * 
     * @param eduTeacherCourse 教师课程关联
     * @return 结果
     */
    @Override
    public int insertEduTeacherCourse(EduTeacherCourse eduTeacherCourse)
    {
        eduTeacherCourse.setCreateTime(DateUtils.getNowDate());
        return eduTeacherCourseMapper.insertEduTeacherCourse(eduTeacherCourse);
    }

    /**
     * 修改教师课程关联
     * 
     * @param eduTeacherCourse 教师课程关联
     * @return 结果
     */
    @Override
    public int updateEduTeacherCourse(EduTeacherCourse eduTeacherCourse)
    {
        eduTeacherCourse.setUpdateTime(DateUtils.getNowDate());
        return eduTeacherCourseMapper.updateEduTeacherCourse(eduTeacherCourse);
    }

    /**
     * 批量删除教师课程关联
     * 
     * @param tcIds 需要删除的教师课程关联主键
     * @return 结果
     */
    @Override
    public int deleteEduTeacherCourseByTcIds(Long[] tcIds)
    {
        return eduTeacherCourseMapper.deleteEduTeacherCourseByTcIds(tcIds);
    }

    /**
     * 删除教师课程关联信息
     * 
     * @param tcId 教师课程关联主键
     * @return 结果
     */
    @Override
    public int deleteEduTeacherCourseByTcId(Long tcId)
    {
        return eduTeacherCourseMapper.deleteEduTeacherCourseByTcId(tcId);
    }
}
