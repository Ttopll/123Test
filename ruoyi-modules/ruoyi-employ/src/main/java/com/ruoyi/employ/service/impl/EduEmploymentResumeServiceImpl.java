package com.ruoyi.employ.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employ.mapper.EduEmploymentResumeMapper;
import com.ruoyi.employ.domain.EduEmploymentResume;
import com.ruoyi.employ.service.IEduEmploymentResumeService;

/**
 * 学生简历管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduEmploymentResumeServiceImpl implements IEduEmploymentResumeService 
{
    @Autowired
    private EduEmploymentResumeMapper eduEmploymentResumeMapper;

    /**
     * 查询学生简历管理
     * 
     * @param resumeId 学生简历管理主键
     * @return 学生简历管理
     */
    @Override
    public EduEmploymentResume selectEduEmploymentResumeByResumeId(Long resumeId)
    {
        return eduEmploymentResumeMapper.selectEduEmploymentResumeByResumeId(resumeId);
    }

    /**
     * 查询学生简历管理列表
     * 
     * @param eduEmploymentResume 学生简历管理
     * @return 学生简历管理
     */
    @Override
    public List<EduEmploymentResume> selectEduEmploymentResumeList(EduEmploymentResume eduEmploymentResume)
    {
        return eduEmploymentResumeMapper.selectEduEmploymentResumeList(eduEmploymentResume);
    }

    /**
     * 新增学生简历管理
     * 
     * @param eduEmploymentResume 学生简历管理
     * @return 结果
     */
    @Override
    public int insertEduEmploymentResume(EduEmploymentResume eduEmploymentResume)
    {
        eduEmploymentResume.setCreateTime(DateUtils.getNowDate());
        return eduEmploymentResumeMapper.insertEduEmploymentResume(eduEmploymentResume);
    }

    /**
     * 修改学生简历管理
     * 
     * @param eduEmploymentResume 学生简历管理
     * @return 结果
     */
    @Override
    public int updateEduEmploymentResume(EduEmploymentResume eduEmploymentResume)
    {
        eduEmploymentResume.setUpdateTime(DateUtils.getNowDate());
        return eduEmploymentResumeMapper.updateEduEmploymentResume(eduEmploymentResume);
    }

    /**
     * 批量删除学生简历管理
     * 
     * @param resumeIds 需要删除的学生简历管理主键
     * @return 结果
     */
    @Override
    public int deleteEduEmploymentResumeByResumeIds(Long[] resumeIds)
    {
        return eduEmploymentResumeMapper.deleteEduEmploymentResumeByResumeIds(resumeIds);
    }

    /**
     * 删除学生简历管理信息
     * 
     * @param resumeId 学生简历管理主键
     * @return 结果
     */
    @Override
    public int deleteEduEmploymentResumeByResumeId(Long resumeId)
    {
        return eduEmploymentResumeMapper.deleteEduEmploymentResumeByResumeId(resumeId);
    }
}
