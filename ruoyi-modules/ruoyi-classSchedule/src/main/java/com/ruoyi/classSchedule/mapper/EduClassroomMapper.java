package com.ruoyi.classSchedule.mapper;

import java.util.List;
import com.ruoyi.classSchedule.domain.EduClassroom;

/**
 * 教室资源Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
public interface EduClassroomMapper 
{
    /**
     * 查询教室资源
     * 
     * @param classroomId 教室资源主键
     * @return 教室资源
     */
    public EduClassroom selectEduClassroomByClassroomId(Long classroomId);

    /**
     * 查询教室资源列表
     * 
     * @param eduClassroom 教室资源
     * @return 教室资源集合
     */
    public List<EduClassroom> selectEduClassroomList(EduClassroom eduClassroom);

    /**
     * 新增教室资源
     * 
     * @param eduClassroom 教室资源
     * @return 结果
     */
    public int insertEduClassroom(EduClassroom eduClassroom);

    /**
     * 修改教室资源
     * 
     * @param eduClassroom 教室资源
     * @return 结果
     */
    public int updateEduClassroom(EduClassroom eduClassroom);

    /**
     * 删除教室资源
     * 
     * @param classroomId 教室资源主键
     * @return 结果
     */
    public int deleteEduClassroomByClassroomId(Long classroomId);

    /**
     * 批量删除教室资源
     * 
     * @param classroomIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduClassroomByClassroomIds(Long[] classroomIds);
}
