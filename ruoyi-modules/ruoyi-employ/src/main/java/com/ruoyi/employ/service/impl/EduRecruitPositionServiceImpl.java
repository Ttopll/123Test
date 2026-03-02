package com.ruoyi.employ.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employ.mapper.EduRecruitPositionMapper;
import com.ruoyi.employ.domain.EduRecruitPosition;
import com.ruoyi.employ.service.IEduRecruitPositionService;

/**
 * 招聘岗位Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@Service
public class EduRecruitPositionServiceImpl implements IEduRecruitPositionService 
{
    @Autowired
    private EduRecruitPositionMapper eduRecruitPositionMapper;

    /**
     * 查询招聘岗位
     * 
     * @param positionId 招聘岗位主键
     * @return 招聘岗位
     */
    @Override
    public EduRecruitPosition selectEduRecruitPositionByPositionId(Long positionId)
    {
        return eduRecruitPositionMapper.selectEduRecruitPositionByPositionId(positionId);
    }

    /**
     * 查询招聘岗位列表
     * 
     * @param eduRecruitPosition 招聘岗位
     * @return 招聘岗位
     */
    @Override
    public List<EduRecruitPosition> selectEduRecruitPositionList(EduRecruitPosition eduRecruitPosition)
    {
        return eduRecruitPositionMapper.selectEduRecruitPositionList(eduRecruitPosition);
    }

    /**
     * 新增招聘岗位
     * 
     * @param eduRecruitPosition 招聘岗位
     * @return 结果
     */
    @Override
    public int insertEduRecruitPosition(EduRecruitPosition eduRecruitPosition)
    {
        eduRecruitPosition.setCreateTime(DateUtils.getNowDate());
        return eduRecruitPositionMapper.insertEduRecruitPosition(eduRecruitPosition);
    }

    /**
     * 修改招聘岗位
     * 
     * @param eduRecruitPosition 招聘岗位
     * @return 结果
     */
    @Override
    public int updateEduRecruitPosition(EduRecruitPosition eduRecruitPosition)
    {
        eduRecruitPosition.setUpdateTime(DateUtils.getNowDate());
        return eduRecruitPositionMapper.updateEduRecruitPosition(eduRecruitPosition);
    }

    /**
     * 批量删除招聘岗位
     * 
     * @param positionIds 需要删除的招聘岗位主键
     * @return 结果
     */
    @Override
    public int deleteEduRecruitPositionByPositionIds(Long[] positionIds)
    {
        return eduRecruitPositionMapper.deleteEduRecruitPositionByPositionIds(positionIds);
    }

    /**
     * 删除招聘岗位信息
     * 
     * @param positionId 招聘岗位主键
     * @return 结果
     */
    @Override
    public int deleteEduRecruitPositionByPositionId(Long positionId)
    {
        return eduRecruitPositionMapper.deleteEduRecruitPositionByPositionId(positionId);
    }
}
