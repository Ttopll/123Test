package com.ruoyi.aiInteraction.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aiInteraction.mapper.EduAiInteractionMapper;
import com.ruoyi.aiInteraction.domain.EduAiInteraction;
import com.ruoyi.aiInteraction.service.IEduAiInteractionService;

/**
 * 智能交互记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduAiInteractionServiceImpl implements IEduAiInteractionService 
{
    @Autowired
    private EduAiInteractionMapper eduAiInteractionMapper;

    /**
     * 查询智能交互记录
     * 
     * @param interactionId 智能交互记录主键
     * @return 智能交互记录
     */
    @Override
    public EduAiInteraction selectEduAiInteractionByInteractionId(String interactionId)
    {
        return eduAiInteractionMapper.selectEduAiInteractionByInteractionId(interactionId);
    }

    /**
     * 查询智能交互记录列表
     * 
     * @param eduAiInteraction 智能交互记录
     * @return 智能交互记录
     */
    @Override
    public List<EduAiInteraction> selectEduAiInteractionList(EduAiInteraction eduAiInteraction)
    {
        return eduAiInteractionMapper.selectEduAiInteractionList(eduAiInteraction);
    }

    /**
     * 新增智能交互记录
     * 
     * @param eduAiInteraction 智能交互记录
     * @return 结果
     */
    @Override
    public int insertEduAiInteraction(EduAiInteraction eduAiInteraction)
    {
        eduAiInteraction.setCreateTime(DateUtils.getNowDate());
        return eduAiInteractionMapper.insertEduAiInteraction(eduAiInteraction);
    }

    /**
     * 修改智能交互记录
     * 
     * @param eduAiInteraction 智能交互记录
     * @return 结果
     */
    @Override
    public int updateEduAiInteraction(EduAiInteraction eduAiInteraction)
    {
        eduAiInteraction.setUpdateTime(DateUtils.getNowDate());
        return eduAiInteractionMapper.updateEduAiInteraction(eduAiInteraction);
    }

    /**
     * 批量删除智能交互记录
     * 
     * @param interactionIds 需要删除的智能交互记录主键
     * @return 结果
     */
    @Override
    public int deleteEduAiInteractionByInteractionIds(String[] interactionIds)
    {
        return eduAiInteractionMapper.deleteEduAiInteractionByInteractionIds(interactionIds);
    }

    /**
     * 删除智能交互记录信息
     * 
     * @param interactionId 智能交互记录主键
     * @return 结果
     */
    @Override
    public int deleteEduAiInteractionByInteractionId(String interactionId)
    {
        return eduAiInteractionMapper.deleteEduAiInteractionByInteractionId(interactionId);
    }
}
