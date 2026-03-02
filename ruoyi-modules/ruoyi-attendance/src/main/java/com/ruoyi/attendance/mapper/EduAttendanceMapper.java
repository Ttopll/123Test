package com.ruoyi.attendance.mapper;

import java.util.List;
import com.ruoyi.attendance.domain.EduAttendance;

/**
 * 考勤管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduAttendanceMapper 
{
    /**
     * 查询考勤管理
     * 
     * @param attendId 考勤管理主键
     * @return 考勤管理
     */
    public EduAttendance selectEduAttendanceByAttendId(Long attendId);

    /**
     * 查询考勤管理列表
     * 
     * @param eduAttendance 考勤管理
     * @return 考勤管理集合
     */
    public List<EduAttendance> selectEduAttendanceList(EduAttendance eduAttendance);

    /**
     * 新增考勤管理
     * 
     * @param eduAttendance 考勤管理
     * @return 结果
     */
    public int insertEduAttendance(EduAttendance eduAttendance);

    /**
     * 修改考勤管理
     * 
     * @param eduAttendance 考勤管理
     * @return 结果
     */
    public int updateEduAttendance(EduAttendance eduAttendance);

    /**
     * 删除考勤管理
     * 
     * @param attendId 考勤管理主键
     * @return 结果
     */
    public int deleteEduAttendanceByAttendId(Long attendId);

    /**
     * 批量删除考勤管理
     * 
     * @param attendIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAttendanceByAttendIds(Long[] attendIds);
}
