package com.ruoyi.aipaper.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aipaper.mapper.EduPaperTemplateMapper;
import com.ruoyi.aipaper.domain.EduPaperTemplate;
import com.ruoyi.aipaper.service.IEduPaperTemplateService;

/**
 * 组卷模板Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduPaperTemplateServiceImpl implements IEduPaperTemplateService 
{
    @Autowired
    private EduPaperTemplateMapper eduPaperTemplateMapper;

    /**
     * 查询组卷模板
     * 
     * @param templateId 组卷模板主键
     * @return 组卷模板
     */
    @Override
    public EduPaperTemplate selectEduPaperTemplateByTemplateId(Long templateId)
    {
        return eduPaperTemplateMapper.selectEduPaperTemplateByTemplateId(templateId);
    }

    /**
     * 查询组卷模板列表
     * 
     * @param eduPaperTemplate 组卷模板
     * @return 组卷模板
     */
    @Override
    public List<EduPaperTemplate> selectEduPaperTemplateList(EduPaperTemplate eduPaperTemplate)
    {
        return eduPaperTemplateMapper.selectEduPaperTemplateList(eduPaperTemplate);
    }

    /**
     * 新增组卷模板
     * 
     * @param eduPaperTemplate 组卷模板
     * @return 结果
     */
    @Override
    public int insertEduPaperTemplate(EduPaperTemplate eduPaperTemplate)
    {
        eduPaperTemplate.setCreateTime(DateUtils.getNowDate());
        return eduPaperTemplateMapper.insertEduPaperTemplate(eduPaperTemplate);
    }

    /**
     * 修改组卷模板
     * 
     * @param eduPaperTemplate 组卷模板
     * @return 结果
     */
    @Override
    public int updateEduPaperTemplate(EduPaperTemplate eduPaperTemplate)
    {
        eduPaperTemplate.setUpdateTime(DateUtils.getNowDate());
        return eduPaperTemplateMapper.updateEduPaperTemplate(eduPaperTemplate);
    }

    /**
     * 批量删除组卷模板
     * 
     * @param templateIds 需要删除的组卷模板主键
     * @return 结果
     */
    @Override
    public int deleteEduPaperTemplateByTemplateIds(Long[] templateIds)
    {
        return eduPaperTemplateMapper.deleteEduPaperTemplateByTemplateIds(templateIds);
    }

    /**
     * 删除组卷模板信息
     * 
     * @param templateId 组卷模板主键
     * @return 结果
     */
    @Override
    public int deleteEduPaperTemplateByTemplateId(Long templateId)
    {
        return eduPaperTemplateMapper.deleteEduPaperTemplateByTemplateId(templateId);
    }
}
