package com.ruoyi.employ.service;

import java.util.List;
import com.ruoyi.employ.domain.EduResumeDelivery;

/**
 * 简历投递记录Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduResumeDeliveryService 
{
    /**
     * 查询简历投递记录
     * 
     * @param deliveryId 简历投递记录主键
     * @return 简历投递记录
     */
    public EduResumeDelivery selectEduResumeDeliveryByDeliveryId(Long deliveryId);

    /**
     * 查询简历投递记录列表
     * 
     * @param eduResumeDelivery 简历投递记录
     * @return 简历投递记录集合
     */
    public List<EduResumeDelivery> selectEduResumeDeliveryList(EduResumeDelivery eduResumeDelivery);

    /**
     * 新增简历投递记录
     * 
     * @param eduResumeDelivery 简历投递记录
     * @return 结果
     */
    public int insertEduResumeDelivery(EduResumeDelivery eduResumeDelivery);

    /**
     * 修改简历投递记录
     * 
     * @param eduResumeDelivery 简历投递记录
     * @return 结果
     */
    public int updateEduResumeDelivery(EduResumeDelivery eduResumeDelivery);

    /**
     * 批量删除简历投递记录
     * 
     * @param deliveryIds 需要删除的简历投递记录主键集合
     * @return 结果
     */
    public int deleteEduResumeDeliveryByDeliveryIds(Long[] deliveryIds);

    /**
     * 删除简历投递记录信息
     * 
     * @param deliveryId 简历投递记录主键
     * @return 结果
     */
    public int deleteEduResumeDeliveryByDeliveryId(Long deliveryId);
}
