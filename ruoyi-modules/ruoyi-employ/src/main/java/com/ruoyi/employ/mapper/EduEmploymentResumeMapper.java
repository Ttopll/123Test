package com.ruoyi.employ.mapper;

import java.util.List;
import com.ruoyi.employ.domain.EduEmploymentResume;

/**
 * 学生简历管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduEmploymentResumeMapper 
{
    /**
     * 查询学生简历管理
     * 
     * @param resumeId 学生简历管理主键
     * @return 学生简历管理
     */
    public EduEmploymentResume selectEduEmploymentResumeByResumeId(Long resumeId);

    /**
     * 查询学生简历管理列表
     * 
     * @param eduEmploymentResume 学生简历管理
     * @return 学生简历管理集合
     */
    public List<EduEmploymentResume> selectEduEmploymentResumeList(EduEmploymentResume eduEmploymentResume);

    /**
     * 新增学生简历管理
     * 
     * @param eduEmploymentResume 学生简历管理
     * @return 结果
     */
    public int insertEduEmploymentResume(EduEmploymentResume eduEmploymentResume);

    /**
     * 修改学生简历管理
     * 
     * @param eduEmploymentResume 学生简历管理
     * @return 结果
     */
    public int updateEduEmploymentResume(EduEmploymentResume eduEmploymentResume);

    /**
     * 删除学生简历管理
     * 
     * @param resumeId 学生简历管理主键
     * @return 结果
     */
    public int deleteEduEmploymentResumeByResumeId(Long resumeId);

    /**
     * 批量删除学生简历管理
     * 
     * @param resumeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduEmploymentResumeByResumeIds(Long[] resumeIds);
}
