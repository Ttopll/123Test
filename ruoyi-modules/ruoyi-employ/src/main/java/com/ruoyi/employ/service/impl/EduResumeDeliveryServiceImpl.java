package com.ruoyi.employ.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employ.mapper.EduResumeDeliveryMapper;
import com.ruoyi.employ.domain.EduResumeDelivery;
import com.ruoyi.employ.service.IEduResumeDeliveryService;

/**
 * 简历投递记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduResumeDeliveryServiceImpl implements IEduResumeDeliveryService 
{
    @Autowired
    private EduResumeDeliveryMapper eduResumeDeliveryMapper;

    /**
     * 查询简历投递记录
     * 
     * @param deliveryId 简历投递记录主键
     * @return 简历投递记录
     */
    @Override
    public EduResumeDelivery selectEduResumeDeliveryByDeliveryId(Long deliveryId)
    {
        return eduResumeDeliveryMapper.selectEduResumeDeliveryByDeliveryId(deliveryId);
    }

    /**
     * 查询简历投递记录列表
     * 
     * @param eduResumeDelivery 简历投递记录
     * @return 简历投递记录
     */
    @Override
    public List<EduResumeDelivery> selectEduResumeDeliveryList(EduResumeDelivery eduResumeDelivery)
    {
        return eduResumeDeliveryMapper.selectEduResumeDeliveryList(eduResumeDelivery);
    }

    /**
     * 新增简历投递记录
     * 
     * @param eduResumeDelivery 简历投递记录
     * @return 结果
     */
    @Override
    public int insertEduResumeDelivery(EduResumeDelivery eduResumeDelivery)
    {
        eduResumeDelivery.setCreateTime(DateUtils.getNowDate());
        return eduResumeDeliveryMapper.insertEduResumeDelivery(eduResumeDelivery);
    }

    /**
     * 修改简历投递记录
     * 
     * @param eduResumeDelivery 简历投递记录
     * @return 结果
     */
    @Override
    public int updateEduResumeDelivery(EduResumeDelivery eduResumeDelivery)
    {
        eduResumeDelivery.setUpdateTime(DateUtils.getNowDate());
        return eduResumeDeliveryMapper.updateEduResumeDelivery(eduResumeDelivery);
    }

    /**
     * 批量删除简历投递记录
     * 
     * @param deliveryIds 需要删除的简历投递记录主键
     * @return 结果
     */
    @Override
    public int deleteEduResumeDeliveryByDeliveryIds(Long[] deliveryIds)
    {
        return eduResumeDeliveryMapper.deleteEduResumeDeliveryByDeliveryIds(deliveryIds);
    }

    /**
     * 删除简历投递记录信息
     * 
     * @param deliveryId 简历投递记录主键
     * @return 结果
     */
    @Override
    public int deleteEduResumeDeliveryByDeliveryId(Long deliveryId)
    {
        return eduResumeDeliveryMapper.deleteEduResumeDeliveryByDeliveryId(deliveryId);
    }
}
