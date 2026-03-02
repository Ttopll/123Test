package com.ruoyi.aipaper.service;

import java.util.List;
import com.ruoyi.aipaper.domain.EduPaperTemplate;

/**
 * 组卷模板Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduPaperTemplateService 
{
    /**
     * 查询组卷模板
     * 
     * @param templateId 组卷模板主键
     * @return 组卷模板
     */
    public EduPaperTemplate selectEduPaperTemplateByTemplateId(Long templateId);

    /**
     * 查询组卷模板列表
     * 
     * @param eduPaperTemplate 组卷模板
     * @return 组卷模板集合
     */
    public List<EduPaperTemplate> selectEduPaperTemplateList(EduPaperTemplate eduPaperTemplate);

    /**
     * 新增组卷模板
     * 
     * @param eduPaperTemplate 组卷模板
     * @return 结果
     */
    public int insertEduPaperTemplate(EduPaperTemplate eduPaperTemplate);

    /**
     * 修改组卷模板
     * 
     * @param eduPaperTemplate 组卷模板
     * @return 结果
     */
    public int updateEduPaperTemplate(EduPaperTemplate eduPaperTemplate);

    /**
     * 批量删除组卷模板
     * 
     * @param templateIds 需要删除的组卷模板主键集合
     * @return 结果
     */
    public int deleteEduPaperTemplateByTemplateIds(Long[] templateIds);

    /**
     * 删除组卷模板信息
     * 
     * @param templateId 组卷模板主键
     * @return 结果
     */
    public int deleteEduPaperTemplateByTemplateId(Long templateId);
}
