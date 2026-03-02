package com.ruoyi.attendance.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.attendance.mapper.EduAttendanceBiometricMapper;
import com.ruoyi.attendance.domain.EduAttendanceBiometric;
import com.ruoyi.attendance.service.IEduAttendanceBiometricService;

/**
 * 考勤人脸指纹记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAttendanceBiometricServiceImpl implements IEduAttendanceBiometricService 
{
    @Autowired
    private EduAttendanceBiometricMapper eduAttendanceBiometricMapper;

    /**
     * 查询考勤人脸指纹记录
     * 
     * @param infoId 考勤人脸指纹记录主键
     * @return 考勤人脸指纹记录
     */
    @Override
    public EduAttendanceBiometric selectEduAttendanceBiometricByInfoId(Long infoId)
    {
        return eduAttendanceBiometricMapper.selectEduAttendanceBiometricByInfoId(infoId);
    }

    /**
     * 查询考勤人脸指纹记录列表
     * 
     * @param eduAttendanceBiometric 考勤人脸指纹记录
     * @return 考勤人脸指纹记录
     */
    @Override
    public List<EduAttendanceBiometric> selectEduAttendanceBiometricList(EduAttendanceBiometric eduAttendanceBiometric)
    {
        return eduAttendanceBiometricMapper.selectEduAttendanceBiometricList(eduAttendanceBiometric);
    }

    /**
     * 新增考勤人脸指纹记录
     * 
     * @param eduAttendanceBiometric 考勤人脸指纹记录
     * @return 结果
     */
    @Override
    public int insertEduAttendanceBiometric(EduAttendanceBiometric eduAttendanceBiometric)
    {
        eduAttendanceBiometric.setCreateTime(DateUtils.getNowDate());
        return eduAttendanceBiometricMapper.insertEduAttendanceBiometric(eduAttendanceBiometric);
    }

    /**
     * 修改考勤人脸指纹记录
     * 
     * @param eduAttendanceBiometric 考勤人脸指纹记录
     * @return 结果
     */
    @Override
    public int updateEduAttendanceBiometric(EduAttendanceBiometric eduAttendanceBiometric)
    {
        eduAttendanceBiometric.setUpdateTime(DateUtils.getNowDate());
        return eduAttendanceBiometricMapper.updateEduAttendanceBiometric(eduAttendanceBiometric);
    }

    /**
     * 批量删除考勤人脸指纹记录
     * 
     * @param infoIds 需要删除的考勤人脸指纹记录主键
     * @return 结果
     */
    @Override
    public int deleteEduAttendanceBiometricByInfoIds(Long[] infoIds)
    {
        return eduAttendanceBiometricMapper.deleteEduAttendanceBiometricByInfoIds(infoIds);
    }

    /**
     * 删除考勤人脸指纹记录信息
     * 
     * @param infoId 考勤人脸指纹记录主键
     * @return 结果
     */
    @Override
    public int deleteEduAttendanceBiometricByInfoId(Long infoId)
    {
        return eduAttendanceBiometricMapper.deleteEduAttendanceBiometricByInfoId(infoId);
    }
}
