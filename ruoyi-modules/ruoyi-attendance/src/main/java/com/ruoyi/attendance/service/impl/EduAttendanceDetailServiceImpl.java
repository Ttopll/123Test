package com.ruoyi.attendance.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.attendance.mapper.EduAttendanceDetailMapper;
import com.ruoyi.attendance.domain.EduAttendanceDetail;
import com.ruoyi.attendance.service.IEduAttendanceDetailService;

/**
 * 考勤详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAttendanceDetailServiceImpl implements IEduAttendanceDetailService 
{
    @Autowired
    private EduAttendanceDetailMapper eduAttendanceDetailMapper;

    /**
     * 查询考勤详情
     * 
     * @param detailId 考勤详情主键
     * @return 考勤详情
     */
    @Override
    public EduAttendanceDetail selectEduAttendanceDetailByDetailId(Long detailId)
    {
        return eduAttendanceDetailMapper.selectEduAttendanceDetailByDetailId(detailId);
    }

    /**
     * 查询考勤详情列表
     * 
     * @param eduAttendanceDetail 考勤详情
     * @return 考勤详情
     */
    @Override
    public List<EduAttendanceDetail> selectEduAttendanceDetailList(EduAttendanceDetail eduAttendanceDetail)
    {
        return eduAttendanceDetailMapper.selectEduAttendanceDetailList(eduAttendanceDetail);
    }

    /**
     * 新增考勤详情
     * 
     * @param eduAttendanceDetail 考勤详情
     * @return 结果
     */
    @Override
    public int insertEduAttendanceDetail(EduAttendanceDetail eduAttendanceDetail)
    {
        eduAttendanceDetail.setCreateTime(DateUtils.getNowDate());
        return eduAttendanceDetailMapper.insertEduAttendanceDetail(eduAttendanceDetail);
    }

    /**
     * 修改考勤详情
     * 
     * @param eduAttendanceDetail 考勤详情
     * @return 结果
     */
    @Override
    public int updateEduAttendanceDetail(EduAttendanceDetail eduAttendanceDetail)
    {
        eduAttendanceDetail.setUpdateTime(DateUtils.getNowDate());
        return eduAttendanceDetailMapper.updateEduAttendanceDetail(eduAttendanceDetail);
    }

    /**
     * 批量删除考勤详情
     * 
     * @param detailIds 需要删除的考勤详情主键
     * @return 结果
     */
    @Override
    public int deleteEduAttendanceDetailByDetailIds(Long[] detailIds)
    {
        return eduAttendanceDetailMapper.deleteEduAttendanceDetailByDetailIds(detailIds);
    }

    /**
     * 删除考勤详情信息
     * 
     * @param detailId 考勤详情主键
     * @return 结果
     */
    @Override
    public int deleteEduAttendanceDetailByDetailId(Long detailId)
    {
        return eduAttendanceDetailMapper.deleteEduAttendanceDetailByDetailId(detailId);
    }
}
