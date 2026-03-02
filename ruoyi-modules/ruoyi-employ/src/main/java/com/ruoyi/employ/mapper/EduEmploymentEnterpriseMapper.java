package com.ruoyi.employ.mapper;

import java.util.List;
import com.ruoyi.employ.domain.EduEmploymentEnterprise;

/**
 * 就业合作企业Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduEmploymentEnterpriseMapper 
{
    /**
     * 查询就业合作企业
     * 
     * @param enterpriseId 就业合作企业主键
     * @return 就业合作企业
     */
    public EduEmploymentEnterprise selectEduEmploymentEnterpriseByEnterpriseId(String enterpriseId);

    /**
     * 查询就业合作企业列表
     * 
     * @param eduEmploymentEnterprise 就业合作企业
     * @return 就业合作企业集合
     */
    public List<EduEmploymentEnterprise> selectEduEmploymentEnterpriseList(EduEmploymentEnterprise eduEmploymentEnterprise);

    /**
     * 新增就业合作企业
     * 
     * @param eduEmploymentEnterprise 就业合作企业
     * @return 结果
     */
    public int insertEduEmploymentEnterprise(EduEmploymentEnterprise eduEmploymentEnterprise);

    /**
     * 修改就业合作企业
     * 
     * @param eduEmploymentEnterprise 就业合作企业
     * @return 结果
     */
    public int updateEduEmploymentEnterprise(EduEmploymentEnterprise eduEmploymentEnterprise);

    /**
     * 删除就业合作企业
     * 
     * @param enterpriseId 就业合作企业主键
     * @return 结果
     */
    public int deleteEduEmploymentEnterpriseByEnterpriseId(String enterpriseId);

    /**
     * 批量删除就业合作企业
     * 
     * @param enterpriseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduEmploymentEnterpriseByEnterpriseIds(String[] enterpriseIds);
}
