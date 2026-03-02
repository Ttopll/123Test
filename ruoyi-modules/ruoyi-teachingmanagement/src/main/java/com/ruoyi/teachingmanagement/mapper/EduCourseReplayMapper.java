package com.ruoyi.teachingmanagement.mapper;

import com.ruoyi.teachingmanagement.domain.EduCourseReplay;

import java.util.List;


/**
 * 课程回放Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduCourseReplayMapper 
{
    /**
     * 查询课程回放
     * 
     * @param replayId 课程回放主键
     * @return 课程回放
     */
    public EduCourseReplay selectEduCourseReplayByReplayId(Long replayId);

    /**
     * 查询课程回放列表
     * 
     * @param eduCourseReplay 课程回放
     * @return 课程回放集合
     */
    public List<EduCourseReplay> selectEduCourseReplayList(EduCourseReplay eduCourseReplay);

    /**
     * 新增课程回放
     * 
     * @param eduCourseReplay 课程回放
     * @return 结果
     */
    public int insertEduCourseReplay(EduCourseReplay eduCourseReplay);

    /**
     * 修改课程回放
     * 
     * @param eduCourseReplay 课程回放
     * @return 结果
     */
    public int updateEduCourseReplay(EduCourseReplay eduCourseReplay);

    /**
     * 删除课程回放
     * 
     * @param replayId 课程回放主键
     * @return 结果
     */
    public int deleteEduCourseReplayByReplayId(Long replayId);

    /**
     * 批量删除课程回放
     * 
     * @param replayIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCourseReplayByReplayIds(Long[] replayIds);
}
