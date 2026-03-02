package com.ruoyi.employ.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employ.mapper.EduEmploymentEnterpriseMapper;
import com.ruoyi.employ.domain.EduEmploymentEnterprise;
import com.ruoyi.employ.service.IEduEmploymentEnterpriseService;

/**
 * 就业合作企业Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduEmploymentEnterpriseServiceImpl implements IEduEmploymentEnterpriseService 
{
    @Autowired
    private EduEmploymentEnterpriseMapper eduEmploymentEnterpriseMapper;

    /**
     * 查询就业合作企业
     * 
     * @param enterpriseId 就业合作企业主键
     * @return 就业合作企业
     */
    @Override
    public EduEmploymentEnterprise selectEduEmploymentEnterpriseByEnterpriseId(String enterpriseId)
    {
        return eduEmploymentEnterpriseMapper.selectEduEmploymentEnterpriseByEnterpriseId(enterpriseId);
    }

    /**
     * 查询就业合作企业列表
     * 
     * @param eduEmploymentEnterprise 就业合作企业
     * @return 就业合作企业
     */
    @Override
    public List<EduEmploymentEnterprise> selectEduEmploymentEnterpriseList(EduEmploymentEnterprise eduEmploymentEnterprise)
    {
        return eduEmploymentEnterpriseMapper.selectEduEmploymentEnterpriseList(eduEmploymentEnterprise);
    }

    /**
     * 新增就业合作企业
     * 
     * @param eduEmploymentEnterprise 就业合作企业
     * @return 结果
     */
    @Override
    public int insertEduEmploymentEnterprise(EduEmploymentEnterprise eduEmploymentEnterprise)
    {
        eduEmploymentEnterprise.setCreateTime(DateUtils.getNowDate());
        return eduEmploymentEnterpriseMapper.insertEduEmploymentEnterprise(eduEmploymentEnterprise);
    }

    /**
     * 修改就业合作企业
     * 
     * @param eduEmploymentEnterprise 就业合作企业
     * @return 结果
     */
    @Override
    public int updateEduEmploymentEnterprise(EduEmploymentEnterprise eduEmploymentEnterprise)
    {
        eduEmploymentEnterprise.setUpdateTime(DateUtils.getNowDate());
        return eduEmploymentEnterpriseMapper.updateEduEmploymentEnterprise(eduEmploymentEnterprise);
    }

    /**
     * 批量删除就业合作企业
     * 
     * @param enterpriseIds 需要删除的就业合作企业主键
     * @return 结果
     */
    @Override
    public int deleteEduEmploymentEnterpriseByEnterpriseIds(String[] enterpriseIds)
    {
        return eduEmploymentEnterpriseMapper.deleteEduEmploymentEnterpriseByEnterpriseIds(enterpriseIds);
    }

    /**
     * 删除就业合作企业信息
     * 
     * @param enterpriseId 就业合作企业主键
     * @return 结果
     */
    @Override
    public int deleteEduEmploymentEnterpriseByEnterpriseId(String enterpriseId)
    {
        return eduEmploymentEnterpriseMapper.deleteEduEmploymentEnterpriseByEnterpriseId(enterpriseId);
    }
}
