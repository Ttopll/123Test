package com.ruoyi.affair.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ruoyi.affair.domain.EduAffairType;
import com.ruoyi.affair.mapper.EduAffairTypeMapper;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.affair.mapper.EduAffairApplyMapper;
import com.ruoyi.affair.domain.EduAffairApply;
import com.ruoyi.affair.service.IEduAffairApplyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAffairApplyServiceImpl implements IEduAffairApplyService 
{
    @Autowired
    private EduAffairApplyMapper eduAffairApplyMapper;
    @Autowired
    private EduAffairTypeMapper eduAffairTypeMapper;

    /**
     * 查询事务申请
     * 
     * @param applyId 事务申请主键
     * @return 事务申请
     */
    @Override
    public EduAffairApply selectEduAffairApplyByApplyId(String applyId)
    {
        return eduAffairApplyMapper.selectEduAffairApplyByApplyId(applyId);
    }

    /**
     * 查询事务申请列表
     * 
     * @param eduAffairApply 事务申请
     * @return 事务申请
     */
    @Override
    public List<EduAffairApply> selectEduAffairApplyList(EduAffairApply eduAffairApply)
    {
        return eduAffairApplyMapper.selectEduAffairApplyList(eduAffairApply);
    }

    /**
     * 新增事务申请
     * 
     * @param eduAffairApply 事务申请
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertEduAffairApply(EduAffairApply eduAffairApply) {

        Date now = DateUtils.getNowDate();
        eduAffairApply.setCreateTime(now);
        eduAffairApply.setUpdateTime(now);
        eduAffairApply.setApplyTime(now);

        eduAffairApply.setCurrentStatus(0);
        eduAffairApply.setHandleUserId(null);
        eduAffairApply.setExpireTime(null);
        eduAffairApply.setCancelReason("");
        eduAffairApply.setCancelTime(null);
        eduAffairApply.setIsDeleted(0);

        EduAffairType eduAffairType = eduAffairTypeMapper.selectEduAffairTypeByAffairTypeId(eduAffairApply.getAffairTypeId());
        eduAffairApply.setExpireTime(DateUtils.addDays(DateUtils.getNowDate(), eduAffairType.getHandleLimit().intValue()));

        Long affairTypeId = eduAffairApply.getAffairTypeId();
        if (affairTypeId == null) {
            throw new ServiceException("事务类型ID不能为空，无法校验提交权限");
        }

        EduAffairType affairType = eduAffairTypeMapper.selectEduAffairTypeByAffairTypeId(affairTypeId);
        if (affairType == null) {
            throw new ServiceException("事务类型不存在，无法提交");
        }

        String applyRoleStr = affairType.getApplyRole();
        if (StringUtils.isBlank(applyRoleStr)) {
            throw new ServiceException("该事务类型未配置可提交角色，禁止提交");
        }

        String nowRoles = eduAffairApply.getApplyUserRole();
        if (StringUtils.isBlank(nowRoles)) {
            throw new ServiceException("用户角色为空，无法提交事务请求");
        }

        String[] applyRoles = applyRoleStr.split(",");
        if (!Arrays.asList(applyRoles).contains(nowRoles)) {
            throw new ServiceException("用户角色无权限提交该类型事务请求");
        }

        return eduAffairApplyMapper.insertEduAffairApply(eduAffairApply);
    }

    /**
     * 修改事务申请
     * 
     * @param eduAffairApply 事务申请
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEduAffairApply(EduAffairApply eduAffairApply) {
        eduAffairApply.setUpdateTime(DateUtils.getNowDate());
        eduAffairApply.setHandleUserId(null);
        eduAffairApply.setExpireTime(null);
        eduAffairApply.setApplyTime(null);
        eduAffairApply.setIsDeleted(0);

        if (StringUtils.isNotBlank(eduAffairApply.getCancelReason())
                || (eduAffairApply.getCurrentStatus() != null && eduAffairApply.getCurrentStatus() == 4)) {
            eduAffairApply.setCancelTime(DateUtils.getNowDate());
        }

        Long affairTypeId = eduAffairApply.getAffairTypeId();
        if (affairTypeId == null) {
            throw new ServiceException("事务类型ID不能为空，无法校验修改权限");
        }
        EduAffairType affairType = eduAffairTypeMapper.selectEduAffairTypeByAffairTypeId(affairTypeId);
        if (affairType == null) {
            throw new ServiceException("事务类型不存在，无法修改该事务");
        }
        String applyRoleStr = affairType.getApplyRole();
        if (StringUtils.isBlank(applyRoleStr)) {
            throw new ServiceException("该事务类型未配置可操作角色，禁止修改");
        }
        String nowRoles = eduAffairApply.getApplyUserRole();
        if (StringUtils.isBlank(nowRoles)) {
            throw new ServiceException("用户角色为空，无法修改事务请求");
        }
        String[] applyRoles = applyRoleStr.split(",");
        if (!Arrays.asList(applyRoles).contains(nowRoles)) {
            throw new ServiceException("用户角色无权限修改该类型事务请求");
        }

        return eduAffairApplyMapper.updateEduAffairApply(eduAffairApply);
    }

    /**
     * 批量删除事务申请
     * 
     * @param applyIds 需要删除的事务申请主键
     * @return 结果
     */
    @Override
    public int deleteEduAffairApplyByApplyIds(String[] applyIds, String updateUserId)
    {
        return eduAffairApplyMapper.deleteEduAffairApplyByApplyIds(applyIds, updateUserId);
    }

    /**
     * 删除事务申请信息
     * 
     * @param applyId 事务申请主键
     * @return 结果
     */
    @Override
    public int deleteEduAffairApplyByApplyId(String applyId, String updateUserId)
    {
        return eduAffairApplyMapper.deleteEduAffairApplyByApplyId(applyId, updateUserId);
    }
}
