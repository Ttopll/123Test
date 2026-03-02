package com.ruoyi.employ.mapper;

import java.util.List;
import com.ruoyi.employ.domain.EduEmploymentStudent;

/**
 * 学生就业信息Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduEmploymentStudentMapper 
{
    /**
     * 查询学生就业信息
     * 
     * @param employmentId 学生就业信息主键
     * @return 学生就业信息
     */
    public EduEmploymentStudent selectEduEmploymentStudentByEmploymentId(Long employmentId);

    /**
     * 查询学生就业信息列表
     * 
     * @param eduEmploymentStudent 学生就业信息
     * @return 学生就业信息集合
     */
    public List<EduEmploymentStudent> selectEduEmploymentStudentList(EduEmploymentStudent eduEmploymentStudent);

    /**
     * 新增学生就业信息
     * 
     * @param eduEmploymentStudent 学生就业信息
     * @return 结果
     */
    public int insertEduEmploymentStudent(EduEmploymentStudent eduEmploymentStudent);

    /**
     * 修改学生就业信息
     * 
     * @param eduEmploymentStudent 学生就业信息
     * @return 结果
     */
    public int updateEduEmploymentStudent(EduEmploymentStudent eduEmploymentStudent);

    /**
     * 删除学生就业信息
     * 
     * @param employmentId 学生就业信息主键
     * @return 结果
     */
    public int deleteEduEmploymentStudentByEmploymentId(Long employmentId);

    /**
     * 批量删除学生就业信息
     * 
     * @param employmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduEmploymentStudentByEmploymentIds(Long[] employmentIds);
}
