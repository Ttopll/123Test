package com.ruoyi.classSchedule.mapper;

import java.util.List;
import com.ruoyi.classSchedule.domain.EduTimetableResult;

/**
 * 排课结果Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public interface EduTimetableResultMapper 
{
    /**
     * 查询排课结果
     * 
     * @param resultId 排课结果主键
     * @return 排课结果
     */
    public EduTimetableResult selectEduTimetableResultByResultId(Long resultId);

    /**
     * 查询排课结果列表
     * 
     * @param eduTimetableResult 排课结果
     * @return 排课结果集合
     */
    public List<EduTimetableResult> selectEduTimetableResultList(EduTimetableResult eduTimetableResult);

    /**
     * 新增排课结果
     * 
     * @param eduTimetableResult 排课结果
     * @return 结果
     */
    public int insertEduTimetableResult(EduTimetableResult eduTimetableResult);

    /**
     * 修改排课结果
     * 
     * @param eduTimetableResult 排课结果
     * @return 结果
     */
    public int updateEduTimetableResult(EduTimetableResult eduTimetableResult);

    /**
     * 删除排课结果
     * 
     * @param resultId 排课结果主键
     * @return 结果
     */
    public int deleteEduTimetableResultByResultId(Long resultId);

    /**
     * 批量删除排课结果
     * 
     * @param resultIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduTimetableResultByResultIds(Long[] resultIds);
}
