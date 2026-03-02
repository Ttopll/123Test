package com.ruoyi.attendance.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.attendance.mapper.EduAttendanceMapper;
import com.ruoyi.attendance.domain.EduAttendance;
import com.ruoyi.attendance.service.IEduAttendanceService;

/**
 * 考勤管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAttendanceServiceImpl implements IEduAttendanceService 
{
    @Autowired
    private EduAttendanceMapper eduAttendanceMapper;

    /**
     * 查询考勤管理
     * 
     * @param attendId 考勤管理主键
     * @return 考勤管理
     */
    @Override
    public EduAttendance selectEduAttendanceByAttendId(Long attendId)
    {
        return eduAttendanceMapper.selectEduAttendanceByAttendId(attendId);
    }

    /**
     * 查询考勤管理列表
     * 
     * @param eduAttendance 考勤管理
     * @return 考勤管理
     */
    @Override
    public List<EduAttendance> selectEduAttendanceList(EduAttendance eduAttendance)
    {
        return eduAttendanceMapper.selectEduAttendanceList(eduAttendance);
    }

    /**
     * 新增考勤管理
     * 
     * @param eduAttendance 考勤管理
     * @return 结果
     */
    @Override
    public int insertEduAttendance(EduAttendance eduAttendance)
    {
        eduAttendance.setCreateTime(DateUtils.getNowDate());
        return eduAttendanceMapper.insertEduAttendance(eduAttendance);
    }

    /**
     * 修改考勤管理
     * 
     * @param eduAttendance 考勤管理
     * @return 结果
     */
    @Override
    public int updateEduAttendance(EduAttendance eduAttendance)
    {
        eduAttendance.setUpdateTime(DateUtils.getNowDate());
        return eduAttendanceMapper.updateEduAttendance(eduAttendance);
    }

    /**
     * 批量删除考勤管理
     * 
     * @param attendIds 需要删除的考勤管理主键
     * @return 结果
     */
    @Override
    public int deleteEduAttendanceByAttendIds(Long[] attendIds)
    {
        return eduAttendanceMapper.deleteEduAttendanceByAttendIds(attendIds);
    }

    /**
     * 删除考勤管理信息
     * 
     * @param attendId 考勤管理主键
     * @return 结果
     */
    @Override
    public int deleteEduAttendanceByAttendId(Long attendId)
    {
        return eduAttendanceMapper.deleteEduAttendanceByAttendId(attendId);
    }
}
