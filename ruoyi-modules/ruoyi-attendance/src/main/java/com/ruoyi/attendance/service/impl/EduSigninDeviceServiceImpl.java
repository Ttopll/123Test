package com.ruoyi.attendance.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.attendance.mapper.EduSigninDeviceMapper;
import com.ruoyi.attendance.domain.EduSigninDevice;
import com.ruoyi.attendance.service.IEduSigninDeviceService;

/**
 * 签到设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduSigninDeviceServiceImpl implements IEduSigninDeviceService 
{
    @Autowired
    private EduSigninDeviceMapper eduSigninDeviceMapper;

    /**
     * 查询签到设备
     * 
     * @param deviceId 签到设备主键
     * @return 签到设备
     */
    @Override
    public EduSigninDevice selectEduSigninDeviceByDeviceId(Long deviceId)
    {
        return eduSigninDeviceMapper.selectEduSigninDeviceByDeviceId(deviceId);
    }

    /**
     * 查询签到设备列表
     * 
     * @param eduSigninDevice 签到设备
     * @return 签到设备
     */
    @Override
    public List<EduSigninDevice> selectEduSigninDeviceList(EduSigninDevice eduSigninDevice)
    {
        return eduSigninDeviceMapper.selectEduSigninDeviceList(eduSigninDevice);
    }

    /**
     * 新增签到设备
     * 
     * @param eduSigninDevice 签到设备
     * @return 结果
     */
    @Override
    public int insertEduSigninDevice(EduSigninDevice eduSigninDevice)
    {
        eduSigninDevice.setCreateTime(DateUtils.getNowDate());
        return eduSigninDeviceMapper.insertEduSigninDevice(eduSigninDevice);
    }

    /**
     * 修改签到设备
     * 
     * @param eduSigninDevice 签到设备
     * @return 结果
     */
    @Override
    public int updateEduSigninDevice(EduSigninDevice eduSigninDevice)
    {
        eduSigninDevice.setUpdateTime(DateUtils.getNowDate());
        return eduSigninDeviceMapper.updateEduSigninDevice(eduSigninDevice);
    }

    /**
     * 批量删除签到设备
     * 
     * @param deviceIds 需要删除的签到设备主键
     * @return 结果
     */
    @Override
    public int deleteEduSigninDeviceByDeviceIds(Long[] deviceIds)
    {
        return eduSigninDeviceMapper.deleteEduSigninDeviceByDeviceIds(deviceIds);
    }

    /**
     * 删除签到设备信息
     * 
     * @param deviceId 签到设备主键
     * @return 结果
     */
    @Override
    public int deleteEduSigninDeviceByDeviceId(Long deviceId)
    {
        return eduSigninDeviceMapper.deleteEduSigninDeviceByDeviceId(deviceId);
    }
}
