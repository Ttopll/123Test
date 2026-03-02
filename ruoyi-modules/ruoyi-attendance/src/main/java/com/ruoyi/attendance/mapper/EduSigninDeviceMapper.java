package com.ruoyi.attendance.mapper;

import java.util.List;
import com.ruoyi.attendance.domain.EduSigninDevice;

/**
 * 签到设备Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduSigninDeviceMapper 
{
    /**
     * 查询签到设备
     * 
     * @param deviceId 签到设备主键
     * @return 签到设备
     */
    public EduSigninDevice selectEduSigninDeviceByDeviceId(Long deviceId);

    /**
     * 查询签到设备列表
     * 
     * @param eduSigninDevice 签到设备
     * @return 签到设备集合
     */
    public List<EduSigninDevice> selectEduSigninDeviceList(EduSigninDevice eduSigninDevice);

    /**
     * 新增签到设备
     * 
     * @param eduSigninDevice 签到设备
     * @return 结果
     */
    public int insertEduSigninDevice(EduSigninDevice eduSigninDevice);

    /**
     * 修改签到设备
     * 
     * @param eduSigninDevice 签到设备
     * @return 结果
     */
    public int updateEduSigninDevice(EduSigninDevice eduSigninDevice);

    /**
     * 删除签到设备
     * 
     * @param deviceId 签到设备主键
     * @return 结果
     */
    public int deleteEduSigninDeviceByDeviceId(Long deviceId);

    /**
     * 批量删除签到设备
     * 
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduSigninDeviceByDeviceIds(Long[] deviceIds);
}
