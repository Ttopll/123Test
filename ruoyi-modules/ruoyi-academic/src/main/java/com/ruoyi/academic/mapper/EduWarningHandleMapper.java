package com.ruoyi.academic.mapper;

import java.util.List;
import com.ruoyi.academic.domain.EduWarningHandle;

/**
 * 预警处理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduWarningHandleMapper 
{
    /**
     * 查询预警处理
     * 
     * @param handleId 预警处理主键
     * @return 预警处理
     */
    public EduWarningHandle selectEduWarningHandleByHandleId(Long handleId);

    /**
     * 查询预警处理列表
     * 
     * @param eduWarningHandle 预警处理
     * @return 预警处理集合
     */
    public List<EduWarningHandle> selectEduWarningHandleList(EduWarningHandle eduWarningHandle);

    /**
     * 新增预警处理
     * 
     * @param eduWarningHandle 预警处理
     * @return 结果
     */
    public int insertEduWarningHandle(EduWarningHandle eduWarningHandle);

    /**
     * 修改预警处理
     * 
     * @param eduWarningHandle 预警处理
     * @return 结果
     */
    public int updateEduWarningHandle(EduWarningHandle eduWarningHandle);

    /**
     * 删除预警处理
     * 
     * @param handleId 预警处理主键
     * @return 结果
     */
    public int deleteEduWarningHandleByHandleId(Long handleId);

    /**
     * 批量删除预警处理
     * 
     * @param handleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduWarningHandleByHandleIds(Long[] handleIds);
}
