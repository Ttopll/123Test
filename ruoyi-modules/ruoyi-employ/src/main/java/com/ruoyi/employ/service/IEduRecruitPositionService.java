package com.ruoyi.employ.service;

import java.util.List;
import com.ruoyi.employ.domain.EduRecruitPosition;

/**
 * 招聘岗位Service接口
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public interface IEduRecruitPositionService 
{
    /**
     * 查询招聘岗位
     * 
     * @param positionId 招聘岗位主键
     * @return 招聘岗位
     */
    public EduRecruitPosition selectEduRecruitPositionByPositionId(Long positionId);

    /**
     * 查询招聘岗位列表
     * 
     * @param eduRecruitPosition 招聘岗位
     * @return 招聘岗位集合
     */
    public List<EduRecruitPosition> selectEduRecruitPositionList(EduRecruitPosition eduRecruitPosition);

    /**
     * 新增招聘岗位
     * 
     * @param eduRecruitPosition 招聘岗位
     * @return 结果
     */
    public int insertEduRecruitPosition(EduRecruitPosition eduRecruitPosition);

    /**
     * 修改招聘岗位
     * 
     * @param eduRecruitPosition 招聘岗位
     * @return 结果
     */
    public int updateEduRecruitPosition(EduRecruitPosition eduRecruitPosition);

    /**
     * 批量删除招聘岗位
     * 
     * @param positionIds 需要删除的招聘岗位主键集合
     * @return 结果
     */
    public int deleteEduRecruitPositionByPositionIds(Long[] positionIds);

    /**
     * 删除招聘岗位信息
     * 
     * @param positionId 招聘岗位主键
     * @return 结果
     */
    public int deleteEduRecruitPositionByPositionId(Long positionId);
}
