package com.ruoyi.classSchedule.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classSchedule.mapper.EduClassroomMapper;
import com.ruoyi.classSchedule.domain.EduClassroom;
import com.ruoyi.classSchedule.service.IEduClassroomService;

/**
 * 教室资源Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-26
 */
@Service
public class EduClassroomServiceImpl implements IEduClassroomService 
{
    @Autowired
    private EduClassroomMapper eduClassroomMapper;

    /**
     * 查询教室资源
     * 
     * @param classroomId 教室资源主键
     * @return 教室资源
     */
    @Override
    public EduClassroom selectEduClassroomByClassroomId(Long classroomId)
    {
        return eduClassroomMapper.selectEduClassroomByClassroomId(classroomId);
    }

    /**
     * 查询教室资源列表
     * 
     * @param eduClassroom 教室资源
     * @return 教室资源
     */
    @Override
    public List<EduClassroom> selectEduClassroomList(EduClassroom eduClassroom)
    {
        return eduClassroomMapper.selectEduClassroomList(eduClassroom);
    }

    /**
     * 新增教室资源
     * 
     * @param eduClassroom 教室资源
     * @return 结果
     */
    @Override
    public int insertEduClassroom(EduClassroom eduClassroom)
    {
        eduClassroom.setCreateTime(DateUtils.getNowDate());
        return eduClassroomMapper.insertEduClassroom(eduClassroom);
    }

    /**
     * 修改教室资源
     * 
     * @param eduClassroom 教室资源
     * @return 结果
     */
    @Override
    public int updateEduClassroom(EduClassroom eduClassroom)
    {
        eduClassroom.setUpdateTime(DateUtils.getNowDate());
        return eduClassroomMapper.updateEduClassroom(eduClassroom);
    }

    /**
     * 批量删除教室资源
     * 
     * @param classroomIds 需要删除的教室资源主键
     * @return 结果
     */
    @Override
    public int deleteEduClassroomByClassroomIds(Long[] classroomIds)
    {
        return eduClassroomMapper.deleteEduClassroomByClassroomIds(classroomIds);
    }

    /**
     * 删除教室资源信息
     * 
     * @param classroomId 教室资源主键
     * @return 结果
     */
    @Override
    public int deleteEduClassroomByClassroomId(Long classroomId)
    {
        return eduClassroomMapper.deleteEduClassroomByClassroomId(classroomId);
    }
}
