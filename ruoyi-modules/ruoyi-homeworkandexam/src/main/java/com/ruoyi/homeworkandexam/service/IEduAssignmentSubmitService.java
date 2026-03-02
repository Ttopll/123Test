package com.ruoyi.homeworkandexam.service;

import java.util.List;
import com.ruoyi.homeworkandexam.domain.EduAssignmentSubmit;

/**
 * 作业提交/批改Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduAssignmentSubmitService 
{
    /**
     * 查询作业提交/批改
     * 
     * @param submitId 作业提交/批改主键
     * @return 作业提交/批改
     */
    public EduAssignmentSubmit selectEduAssignmentSubmitBySubmitId(Long submitId);

    /**
     * 查询作业提交/批改列表
     * 
     * @param eduAssignmentSubmit 作业提交/批改
     * @return 作业提交/批改集合
     */
    public List<EduAssignmentSubmit> selectEduAssignmentSubmitList(EduAssignmentSubmit eduAssignmentSubmit);

    /**
     * 新增作业提交/批改
     * 
     * @param eduAssignmentSubmit 作业提交/批改
     * @return 结果
     */
    public int insertEduAssignmentSubmit(EduAssignmentSubmit eduAssignmentSubmit);

    /**
     * 修改作业提交/批改
     * 
     * @param eduAssignmentSubmit 作业提交/批改
     * @return 结果
     */
    public int updateEduAssignmentSubmit(EduAssignmentSubmit eduAssignmentSubmit);

    /**
     * 批量删除作业提交/批改
     * 
     * @param submitIds 需要删除的作业提交/批改主键集合
     * @return 结果
     */
    public int deleteEduAssignmentSubmitBySubmitIds(Long[] submitIds);

    /**
     * 删除作业提交/批改信息
     * 
     * @param submitId 作业提交/批改主键
     * @return 结果
     */
    public int deleteEduAssignmentSubmitBySubmitId(Long submitId);
}
