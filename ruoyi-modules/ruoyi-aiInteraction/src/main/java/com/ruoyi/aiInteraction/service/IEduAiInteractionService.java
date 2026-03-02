package com.ruoyi.aiInteraction.service;

import java.util.List;
import com.ruoyi.aiInteraction.domain.EduAiInteraction;

/**
 * 智能交互记录Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduAiInteractionService 
{
    /**
     * 查询智能交互记录
     * 
     * @param interactionId 智能交互记录主键
     * @return 智能交互记录
     */
    public EduAiInteraction selectEduAiInteractionByInteractionId(String interactionId);

    /**
     * 查询智能交互记录列表
     * 
     * @param eduAiInteraction 智能交互记录
     * @return 智能交互记录集合
     */
    public List<EduAiInteraction> selectEduAiInteractionList(EduAiInteraction eduAiInteraction);

    /**
     * 新增智能交互记录
     * 
     * @param eduAiInteraction 智能交互记录
     * @return 结果
     */
    public int insertEduAiInteraction(EduAiInteraction eduAiInteraction);

    /**
     * 修改智能交互记录
     * 
     * @param eduAiInteraction 智能交互记录
     * @return 结果
     */
    public int updateEduAiInteraction(EduAiInteraction eduAiInteraction);

    /**
     * 批量删除智能交互记录
     * 
     * @param interactionIds 需要删除的智能交互记录主键集合
     * @return 结果
     */
    public int deleteEduAiInteractionByInteractionIds(String[] interactionIds);

    /**
     * 删除智能交互记录信息
     * 
     * @param interactionId 智能交互记录主键
     * @return 结果
     */
    public int deleteEduAiInteractionByInteractionId(String interactionId);
}
