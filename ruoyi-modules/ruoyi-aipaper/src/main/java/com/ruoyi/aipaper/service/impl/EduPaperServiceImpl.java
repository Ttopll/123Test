package com.ruoyi.aipaper.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aipaper.mapper.EduPaperMapper;
import com.ruoyi.aipaper.domain.EduPaper;
import com.ruoyi.aipaper.service.IEduPaperService;

/**
 * 试卷Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduPaperServiceImpl implements IEduPaperService 
{
    @Autowired
    private EduPaperMapper eduPaperMapper;

    /**
     * 查询试卷
     * 
     * @param paperId 试卷主键
     * @return 试卷
     */
    @Override
    public EduPaper selectEduPaperByPaperId(Long paperId)
    {
        return eduPaperMapper.selectEduPaperByPaperId(paperId);
    }

    /**
     * 查询试卷列表
     * 
     * @param eduPaper 试卷
     * @return 试卷
     */
    @Override
    public List<EduPaper> selectEduPaperList(EduPaper eduPaper)
    {
        return eduPaperMapper.selectEduPaperList(eduPaper);
    }

    /**
     * 新增试卷
     * 
     * @param eduPaper 试卷
     * @return 结果
     */
    @Override
    public int insertEduPaper(EduPaper eduPaper)
    {
        eduPaper.setCreateTime(DateUtils.getNowDate());
        return eduPaperMapper.insertEduPaper(eduPaper);
    }

    /**
     * 修改试卷
     * 
     * @param eduPaper 试卷
     * @return 结果
     */
    @Override
    public int updateEduPaper(EduPaper eduPaper)
    {
        eduPaper.setUpdateTime(DateUtils.getNowDate());
        return eduPaperMapper.updateEduPaper(eduPaper);
    }

    /**
     * 批量删除试卷
     * 
     * @param paperIds 需要删除的试卷主键
     * @return 结果
     */
    @Override
    public int deleteEduPaperByPaperIds(Long[] paperIds)
    {
        return eduPaperMapper.deleteEduPaperByPaperIds(paperIds);
    }

    /**
     * 删除试卷信息
     * 
     * @param paperId 试卷主键
     * @return 结果
     */
    @Override
    public int deleteEduPaperByPaperId(Long paperId)
    {
        return eduPaperMapper.deleteEduPaperByPaperId(paperId);
    }
}
