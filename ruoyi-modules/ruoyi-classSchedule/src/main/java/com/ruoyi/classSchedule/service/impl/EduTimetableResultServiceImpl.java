package com.ruoyi.classSchedule.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classSchedule.mapper.EduTimetableResultMapper;
import com.ruoyi.classSchedule.domain.EduTimetableResult;
import com.ruoyi.classSchedule.service.IEduTimetableResultService;

/**
 * 排课结果Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@Service
public class EduTimetableResultServiceImpl implements IEduTimetableResultService 
{
    @Autowired
    private EduTimetableResultMapper eduTimetableResultMapper;

    /**
     * 查询排课结果
     * 
     * @param resultId 排课结果主键
     * @return 排课结果
     */
    @Override
    public EduTimetableResult selectEduTimetableResultByResultId(Long resultId)
    {
        return eduTimetableResultMapper.selectEduTimetableResultByResultId(resultId);
    }

    /**
     * 查询排课结果列表
     * 
     * @param eduTimetableResult 排课结果
     * @return 排课结果
     */
    @Override
    public List<EduTimetableResult> selectEduTimetableResultList(EduTimetableResult eduTimetableResult)
    {
        return eduTimetableResultMapper.selectEduTimetableResultList(eduTimetableResult);
    }

    /**
     * 新增排课结果
     * 
     * @param eduTimetableResult 排课结果
     * @return 结果
     */
    @Override
    public int insertEduTimetableResult(EduTimetableResult eduTimetableResult)
    {
        eduTimetableResult.setCreateTime(DateUtils.getNowDate());
        return eduTimetableResultMapper.insertEduTimetableResult(eduTimetableResult);
    }

    /**
     * 修改排课结果
     * 
     * @param eduTimetableResult 排课结果
     * @return 结果
     */
    @Override
    public int updateEduTimetableResult(EduTimetableResult eduTimetableResult)
    {
        eduTimetableResult.setUpdateTime(DateUtils.getNowDate());
        return eduTimetableResultMapper.updateEduTimetableResult(eduTimetableResult);
    }

    /**
     * 批量删除排课结果
     * 
     * @param resultIds 需要删除的排课结果主键
     * @return 结果
     */
    @Override
    public int deleteEduTimetableResultByResultIds(Long[] resultIds)
    {
        return eduTimetableResultMapper.deleteEduTimetableResultByResultIds(resultIds);
    }

    /**
     * 删除排课结果信息
     * 
     * @param resultId 排课结果主键
     * @return 结果
     */
    @Override
    public int deleteEduTimetableResultByResultId(Long resultId)
    {
        return eduTimetableResultMapper.deleteEduTimetableResultByResultId(resultId);
    }
}
