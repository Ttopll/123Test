package com.ruoyi.affair.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.affair.domain.EduAffairApply;
import com.ruoyi.affair.domain.EduAffairUserCurrentStatus;
import com.ruoyi.affair.mapper.EduAffairApplyMapper;
import com.ruoyi.affair.mapper.EduAffairTypeMapper;
import com.ruoyi.affair.mapper.EduAffairUserCurrentStatusMapper;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.affair.mapper.EduAffairHandleLogMapper;
import com.ruoyi.affair.domain.EduAffairHandleLog;
import com.ruoyi.affair.service.IEduAffairHandleLogService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务处理日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAffairHandleLogServiceImpl implements IEduAffairHandleLogService 
{
    @Autowired
    private EduAffairHandleLogMapper eduAffairHandleLogMapper;
    @Autowired
    private EduAffairApplyMapper eduAffairApplyMapper;
    @Autowired
    private EduAffairUserCurrentStatusMapper eduAffairUserCurrentStatusMapper;
    @Autowired
    private EduAffairTypeMapper eduAffairTypeMapper;


    /**
     * 查询事务处理日志
     * 
     * @param logId 事务处理日志主键
     * @return 事务处理日志
     */
    @Override
    public EduAffairHandleLog selectEduAffairHandleLogByLogId(Long logId)
    {
        return eduAffairHandleLogMapper.selectEduAffairHandleLogByLogId(logId);
    }

    /**
     * 查询事务处理日志列表
     * 
     * @param eduAffairHandleLog 事务处理日志
     * @return 事务处理日志
     */
    @Override
    public List<EduAffairHandleLog> selectEduAffairHandleLogList(EduAffairHandleLog eduAffairHandleLog)
    {
        return eduAffairHandleLogMapper.selectEduAffairHandleLogList(eduAffairHandleLog);
    }

    /**
     * 新增事务处理日志
     * 
     * @param eduAffairHandleLog 事务处理日志
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int handleEduAffairApply(EduAffairHandleLog eduAffairHandleLog)
    {
        Long applyId = eduAffairHandleLog.getApplyId();
        EduAffairApply eduAffairApply = eduAffairApplyMapper.selectEduAffairApplyByApplyId(applyId.toString());

        Integer updatedStatus = eduAffairHandleLog.getAfterStatus();
        Long handleUserId = eduAffairHandleLog.getHandleUserId();
        eduAffairApply.setCurrentStatus(updatedStatus);
        eduAffairApply.setHandleUserId(handleUserId.toString());

        eduAffairApplyMapper.updateEduAffairApply(eduAffairApply);

        EduAffairUserCurrentStatus eduAffairUserCurrentStatus = new EduAffairUserCurrentStatus();

        eduAffairUserCurrentStatus.setUserId(Long.valueOf(eduAffairApply.getApplyUserId()));
        eduAffairUserCurrentStatus.setStatusType(eduAffairHandleLog.getAfterStatus());
        eduAffairUserCurrentStatus.setStatusDesc(eduAffairHandleLog.getHandleContent());
        eduAffairUserCurrentStatus.setStartTime(eduAffairApply.getStartTime());
        eduAffairUserCurrentStatus.setEndTime(eduAffairApply.getEndTime());
        eduAffairUserCurrentStatus.setSourceApplyId(applyId);
        eduAffairUserCurrentStatus.setCreateTime(DateUtils.getNowDate());
        eduAffairUserCurrentStatus.setUpdateTime(DateUtils.getNowDate());
        eduAffairUserCurrentStatus.setUpdateUserId(handleUserId);
        eduAffairUserCurrentStatus.setUpdateUserName(eduAffairHandleLog.getHandleUserName());

        if (eduAffairUserCurrentStatusMapper.selectEduAffairUserCurrentStatusByUserId(Long.valueOf(eduAffairApply.getApplyUserId())) != null) {
            eduAffairUserCurrentStatusMapper.updateEduAffairUserCurrentStatus(eduAffairUserCurrentStatus);
        }else {
            eduAffairUserCurrentStatusMapper.insertEduAffairUserCurrentStatus(eduAffairUserCurrentStatus);
        }

        return eduAffairHandleLogMapper.insertEduAffairHandleLog(eduAffairHandleLog);
    }

    /**
     * 批量删除事务处理日志
     * 
     * @param logIds 需要删除的事务处理日志主键
     * @return 结果
     */
    @Override
    public int deleteEduAffairHandleLogByLogIds(Long[] logIds, String updateUserId)
    {
        return eduAffairHandleLogMapper.deleteEduAffairHandleLogByLogIds(logIds, updateUserId);
    }

    /**
     * 删除事务处理日志信息
     * 
     * @param logId 事务处理日志主键
     * @return 结果
     */
    @Override
    public int deleteEduAffairHandleLogByLogId(Long logId, String updateUserId)
    {
        return eduAffairHandleLogMapper.deleteEduAffairHandleLogByLogId(logId, updateUserId);
    }
}
