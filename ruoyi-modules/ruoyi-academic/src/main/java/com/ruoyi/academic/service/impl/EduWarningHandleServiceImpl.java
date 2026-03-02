package com.ruoyi.academic.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.academic.mapper.EduWarningHandleMapper;
import com.ruoyi.academic.domain.EduWarningHandle;
import com.ruoyi.academic.service.IEduWarningHandleService;

/**
 * 预警处理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduWarningHandleServiceImpl implements IEduWarningHandleService 
{
    @Autowired
    private EduWarningHandleMapper eduWarningHandleMapper;

    /**
     * 查询预警处理
     * 
     * @param handleId 预警处理主键
     * @return 预警处理
     */
    @Override
    public EduWarningHandle selectEduWarningHandleByHandleId(Long handleId)
    {
        return eduWarningHandleMapper.selectEduWarningHandleByHandleId(handleId);
    }

    /**
     * 查询预警处理列表
     * 
     * @param eduWarningHandle 预警处理
     * @return 预警处理
     */
    @Override
    public List<EduWarningHandle> selectEduWarningHandleList(EduWarningHandle eduWarningHandle)
    {
        return eduWarningHandleMapper.selectEduWarningHandleList(eduWarningHandle);
    }

    /**
     * 新增预警处理
     * 
     * @param eduWarningHandle 预警处理
     * @return 结果
     */
    @Override
    public int insertEduWarningHandle(EduWarningHandle eduWarningHandle)
    {
        return eduWarningHandleMapper.insertEduWarningHandle(eduWarningHandle);
    }

    /**
     * 修改预警处理
     * 
     * @param eduWarningHandle 预警处理
     * @return 结果
     */
    @Override
    public int updateEduWarningHandle(EduWarningHandle eduWarningHandle)
    {
        eduWarningHandle.setUpdateTime(DateUtils.getNowDate());
        return eduWarningHandleMapper.updateEduWarningHandle(eduWarningHandle);
    }

    /**
     * 批量删除预警处理
     * 
     * @param handleIds 需要删除的预警处理主键
     * @return 结果
     */
    @Override
    public int deleteEduWarningHandleByHandleIds(Long[] handleIds)
    {
        return eduWarningHandleMapper.deleteEduWarningHandleByHandleIds(handleIds);
    }

    /**
     * 删除预警处理信息
     * 
     * @param handleId 预警处理主键
     * @return 结果
     */
    @Override
    public int deleteEduWarningHandleByHandleId(Long handleId)
    {
        return eduWarningHandleMapper.deleteEduWarningHandleByHandleId(handleId);
    }
}
