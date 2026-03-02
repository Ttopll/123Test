package com.ruoyi.aipaper.mapper;

import java.util.List;
import com.ruoyi.aipaper.domain.EduPaperTemplate;

/**
 * 组卷模板Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduPaperTemplateMapper 
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
     * 删除组卷模板
     * 
     * @param templateId 组卷模板主键
     * @return 结果
     */
    public int deleteEduPaperTemplateByTemplateId(Long templateId);

    /**
     * 批量删除组卷模板
     * 
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduPaperTemplateByTemplateIds(Long[] templateIds);
}
