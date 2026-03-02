package com.ruoyi.employ.mapper;

import java.util.List;
import com.ruoyi.employ.domain.EduRecruitPosition;

/**
 * 招聘岗位Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public interface EduRecruitPositionMapper 
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
     * 删除招聘岗位
     * 
     * @param positionId 招聘岗位主键
     * @return 结果
     */
    public int deleteEduRecruitPositionByPositionId(Long positionId);

    /**
     * 批量删除招聘岗位
     * 
     * @param positionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduRecruitPositionByPositionIds(Long[] positionIds);
}
