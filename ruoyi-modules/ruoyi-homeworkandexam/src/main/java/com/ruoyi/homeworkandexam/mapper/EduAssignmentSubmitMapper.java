package com.ruoyi.homeworkandexam.mapper;

import java.util.List;
import com.ruoyi.homeworkandexam.domain.EduAssignmentSubmit;

/**
 * 作业提交/批改Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduAssignmentSubmitMapper 
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
     * 删除作业提交/批改
     * 
     * @param submitId 作业提交/批改主键
     * @return 结果
     */
    public int deleteEduAssignmentSubmitBySubmitId(Long submitId);

    /**
     * 批量删除作业提交/批改
     * 
     * @param submitIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAssignmentSubmitBySubmitIds(Long[] submitIds);
}
