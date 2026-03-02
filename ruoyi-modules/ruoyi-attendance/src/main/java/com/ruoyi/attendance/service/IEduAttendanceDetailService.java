package com.ruoyi.attendance.service;

import java.util.List;
import com.ruoyi.attendance.domain.EduAttendanceDetail;

/**
 * 考勤详情Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduAttendanceDetailService 
{
    /**
     * 查询考勤详情
     * 
     * @param detailId 考勤详情主键
     * @return 考勤详情
     */
    public EduAttendanceDetail selectEduAttendanceDetailByDetailId(Long detailId);

    /**
     * 查询考勤详情列表
     * 
     * @param eduAttendanceDetail 考勤详情
     * @return 考勤详情集合
     */
    public List<EduAttendanceDetail> selectEduAttendanceDetailList(EduAttendanceDetail eduAttendanceDetail);

    /**
     * 新增考勤详情
     * 
     * @param eduAttendanceDetail 考勤详情
     * @return 结果
     */
    public int insertEduAttendanceDetail(EduAttendanceDetail eduAttendanceDetail);

    /**
     * 修改考勤详情
     * 
     * @param eduAttendanceDetail 考勤详情
     * @return 结果
     */
    public int updateEduAttendanceDetail(EduAttendanceDetail eduAttendanceDetail);

    /**
     * 批量删除考勤详情
     * 
     * @param detailIds 需要删除的考勤详情主键集合
     * @return 结果
     */
    public int deleteEduAttendanceDetailByDetailIds(Long[] detailIds);

    /**
     * 删除考勤详情信息
     * 
     * @param detailId 考勤详情主键
     * @return 结果
     */
    public int deleteEduAttendanceDetailByDetailId(Long detailId);
}
