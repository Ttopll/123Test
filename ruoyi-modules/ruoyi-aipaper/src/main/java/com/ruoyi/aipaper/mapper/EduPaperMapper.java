package com.ruoyi.aipaper.mapper;

import java.util.List;
import com.ruoyi.aipaper.domain.EduPaper;

/**
 * 试卷Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduPaperMapper 
{
    /**
     * 查询试卷
     * 
     * @param paperId 试卷主键
     * @return 试卷
     */
    public EduPaper selectEduPaperByPaperId(Long paperId);

    /**
     * 查询试卷列表
     * 
     * @param eduPaper 试卷
     * @return 试卷集合
     */
    public List<EduPaper> selectEduPaperList(EduPaper eduPaper);

    /**
     * 新增试卷
     * 
     * @param eduPaper 试卷
     * @return 结果
     */
    public int insertEduPaper(EduPaper eduPaper);

    /**
     * 修改试卷
     * 
     * @param eduPaper 试卷
     * @return 结果
     */
    public int updateEduPaper(EduPaper eduPaper);

    /**
     * 删除试卷
     * 
     * @param paperId 试卷主键
     * @return 结果
     */
    public int deleteEduPaperByPaperId(Long paperId);

    /**
     * 批量删除试卷
     * 
     * @param paperIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduPaperByPaperIds(Long[] paperIds);
}
