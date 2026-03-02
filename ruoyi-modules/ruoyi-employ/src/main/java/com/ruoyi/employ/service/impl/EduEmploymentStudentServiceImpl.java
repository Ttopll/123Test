package com.ruoyi.employ.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employ.mapper.EduEmploymentStudentMapper;
import com.ruoyi.employ.domain.EduEmploymentStudent;
import com.ruoyi.employ.service.IEduEmploymentStudentService;

/**
 * 学生就业信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduEmploymentStudentServiceImpl implements IEduEmploymentStudentService 
{
    @Autowired
    private EduEmploymentStudentMapper eduEmploymentStudentMapper;

    /**
     * 查询学生就业信息
     * 
     * @param employmentId 学生就业信息主键
     * @return 学生就业信息
     */
    @Override
    public EduEmploymentStudent selectEduEmploymentStudentByEmploymentId(Long employmentId)
    {
        return eduEmploymentStudentMapper.selectEduEmploymentStudentByEmploymentId(employmentId);
    }

    /**
     * 查询学生就业信息列表
     * 
     * @param eduEmploymentStudent 学生就业信息
     * @return 学生就业信息
     */
    @Override
    public List<EduEmploymentStudent> selectEduEmploymentStudentList(EduEmploymentStudent eduEmploymentStudent)
    {
        return eduEmploymentStudentMapper.selectEduEmploymentStudentList(eduEmploymentStudent);
    }

    /**
     * 新增学生就业信息
     * 
     * @param eduEmploymentStudent 学生就业信息
     * @return 结果
     */
    @Override
    public int insertEduEmploymentStudent(EduEmploymentStudent eduEmploymentStudent)
    {
        eduEmploymentStudent.setCreateTime(DateUtils.getNowDate());
        return eduEmploymentStudentMapper.insertEduEmploymentStudent(eduEmploymentStudent);
    }

    /**
     * 修改学生就业信息
     * 
     * @param eduEmploymentStudent 学生就业信息
     * @return 结果
     */
    @Override
    public int updateEduEmploymentStudent(EduEmploymentStudent eduEmploymentStudent)
    {
        eduEmploymentStudent.setUpdateTime(DateUtils.getNowDate());
        return eduEmploymentStudentMapper.updateEduEmploymentStudent(eduEmploymentStudent);
    }

    /**
     * 批量删除学生就业信息
     * 
     * @param employmentIds 需要删除的学生就业信息主键
     * @return 结果
     */
    @Override
    public int deleteEduEmploymentStudentByEmploymentIds(Long[] employmentIds)
    {
        return eduEmploymentStudentMapper.deleteEduEmploymentStudentByEmploymentIds(employmentIds);
    }

    /**
     * 删除学生就业信息信息
     * 
     * @param employmentId 学生就业信息主键
     * @return 结果
     */
    @Override
    public int deleteEduEmploymentStudentByEmploymentId(Long employmentId)
    {
        return eduEmploymentStudentMapper.deleteEduEmploymentStudentByEmploymentId(employmentId);
    }
}
