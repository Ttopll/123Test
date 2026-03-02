package com.ruoyi.teachingmanagement.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.teachingmanagement.domain.EduCourseReplay;
import com.ruoyi.teachingmanagement.mapper.EduCourseReplayMapper;
import com.ruoyi.teachingmanagement.service.IEduCourseReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 课程回放Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduCourseReplayServiceImpl implements IEduCourseReplayService
{
    @Autowired
    private EduCourseReplayMapper eduCourseReplayMapper;

    /**
     * 查询课程回放
     * 
     * @param replayId 课程回放主键
     * @return 课程回放
     */
    @Override
    public EduCourseReplay selectEduCourseReplayByReplayId(Long replayId)
    {
        return eduCourseReplayMapper.selectEduCourseReplayByReplayId(replayId);
    }

    /**
     * 查询课程回放列表
     * 
     * @param eduCourseReplay 课程回放
     * @return 课程回放
     */
    @Override
    public List<EduCourseReplay> selectEduCourseReplayList(EduCourseReplay eduCourseReplay)
    {
        return eduCourseReplayMapper.selectEduCourseReplayList(eduCourseReplay);
    }

    /**
     * 新增课程回放
     * 
     * @param eduCourseReplay 课程回放
     * @return 结果
     */
    @Override
    public int insertEduCourseReplay(EduCourseReplay eduCourseReplay)
    {
        eduCourseReplay.setCreateTime(DateUtils.getNowDate());
        return eduCourseReplayMapper.insertEduCourseReplay(eduCourseReplay);
    }

    /**
     * 修改课程回放
     * 
     * @param eduCourseReplay 课程回放
     * @return 结果
     */
    @Override
    public int updateEduCourseReplay(EduCourseReplay eduCourseReplay)
    {
        eduCourseReplay.setUpdateTime(DateUtils.getNowDate());
        return eduCourseReplayMapper.updateEduCourseReplay(eduCourseReplay);
    }

    /**
     * 批量删除课程回放
     * 
     * @param replayIds 需要删除的课程回放主键
     * @return 结果
     */
    @Override
    public int deleteEduCourseReplayByReplayIds(Long[] replayIds)
    {
        return eduCourseReplayMapper.deleteEduCourseReplayByReplayIds(replayIds);
    }

    /**
     * 删除课程回放信息
     * 
     * @param replayId 课程回放主键
     * @return 结果
     */
    @Override
    public int deleteEduCourseReplayByReplayId(Long replayId)
    {
        return eduCourseReplayMapper.deleteEduCourseReplayByReplayId(replayId);
    }
}
