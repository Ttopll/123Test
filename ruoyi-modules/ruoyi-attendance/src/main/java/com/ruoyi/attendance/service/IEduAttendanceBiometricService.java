package com.ruoyi.attendance.service;

import java.util.List;
import com.ruoyi.attendance.domain.EduAttendanceBiometric;

/**
 * 考勤人脸指纹记录Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduAttendanceBiometricService 
{
    /**
     * 查询考勤人脸指纹记录
     * 
     * @param infoId 考勤人脸指纹记录主键
     * @return 考勤人脸指纹记录
     */
    public EduAttendanceBiometric selectEduAttendanceBiometricByInfoId(Long infoId);

    /**
     * 查询考勤人脸指纹记录列表
     * 
     * @param eduAttendanceBiometric 考勤人脸指纹记录
     * @return 考勤人脸指纹记录集合
     */
    public List<EduAttendanceBiometric> selectEduAttendanceBiometricList(EduAttendanceBiometric eduAttendanceBiometric);

    /**
     * 新增考勤人脸指纹记录
     * 
     * @param eduAttendanceBiometric 考勤人脸指纹记录
     * @return 结果
     */
    public int insertEduAttendanceBiometric(EduAttendanceBiometric eduAttendanceBiometric);

    /**
     * 修改考勤人脸指纹记录
     * 
     * @param eduAttendanceBiometric 考勤人脸指纹记录
     * @return 结果
     */
    public int updateEduAttendanceBiometric(EduAttendanceBiometric eduAttendanceBiometric);

    /**
     * 批量删除考勤人脸指纹记录
     * 
     * @param infoIds 需要删除的考勤人脸指纹记录主键集合
     * @return 结果
     */
    public int deleteEduAttendanceBiometricByInfoIds(Long[] infoIds);

    /**
     * 删除考勤人脸指纹记录信息
     * 
     * @param infoId 考勤人脸指纹记录主键
     * @return 结果
     */
    public int deleteEduAttendanceBiometricByInfoId(Long infoId);
}
