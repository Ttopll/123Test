package com.ruoyi.homeworkandexam.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.homeworkandexam.mapper.EduAssignmentSubmitMapper;
import com.ruoyi.homeworkandexam.domain.EduAssignmentSubmit;
import com.ruoyi.homeworkandexam.service.IEduAssignmentSubmitService;

/**
 * 作业提交/批改Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAssignmentSubmitServiceImpl implements IEduAssignmentSubmitService 
{
    @Autowired
    private EduAssignmentSubmitMapper eduAssignmentSubmitMapper;

    /**
     * 查询作业提交/批改
     * 
     * @param submitId 作业提交/批改主键
     * @return 作业提交/批改
     */
    @Override
    public EduAssignmentSubmit selectEduAssignmentSubmitBySubmitId(Long submitId)
    {
        return eduAssignmentSubmitMapper.selectEduAssignmentSubmitBySubmitId(submitId);
    }

    /**
     * 查询作业提交/批改列表
     * 
     * @param eduAssignmentSubmit 作业提交/批改
     * @return 作业提交/批改
     */
    @Override
    public List<EduAssignmentSubmit> selectEduAssignmentSubmitList(EduAssignmentSubmit eduAssignmentSubmit)
    {
        return eduAssignmentSubmitMapper.selectEduAssignmentSubmitList(eduAssignmentSubmit);
    }

    /**
     * 新增作业提交/批改
     * 
     * @param eduAssignmentSubmit 作业提交/批改
     * @return 结果
     */
    @Override
    public int insertEduAssignmentSubmit(EduAssignmentSubmit eduAssignmentSubmit)
    {
        eduAssignmentSubmit.setCreateTime(DateUtils.getNowDate());
        return eduAssignmentSubmitMapper.insertEduAssignmentSubmit(eduAssignmentSubmit);
    }

    /**
     * 修改作业提交/批改
     * 
     * @param eduAssignmentSubmit 作业提交/批改
     * @return 结果
     */
    @Override
    public int updateEduAssignmentSubmit(EduAssignmentSubmit eduAssignmentSubmit)
    {
        eduAssignmentSubmit.setUpdateTime(DateUtils.getNowDate());
        return eduAssignmentSubmitMapper.updateEduAssignmentSubmit(eduAssignmentSubmit);
    }

    /**
     * 批量删除作业提交/批改
     * 
     * @param submitIds 需要删除的作业提交/批改主键
     * @return 结果
     */
    @Override
    public int deleteEduAssignmentSubmitBySubmitIds(Long[] submitIds)
    {
        return eduAssignmentSubmitMapper.deleteEduAssignmentSubmitBySubmitIds(submitIds);
    }

    /**
     * 删除作业提交/批改信息
     * 
     * @param submitId 作业提交/批改主键
     * @return 结果
     */
    @Override
    public int deleteEduAssignmentSubmitBySubmitId(Long submitId)
    {
        return eduAssignmentSubmitMapper.deleteEduAssignmentSubmitBySubmitId(submitId);
    }
}
