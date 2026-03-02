package com.ruoyi.teachingmanagement.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.teachingmanagement.domain.EduMajor;
import com.ruoyi.teachingmanagement.mapper.EduMajorMapper;
import com.ruoyi.teachingmanagement.service.IEduMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 专业管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduMajorServiceImpl implements IEduMajorService
{
    @Autowired
    private EduMajorMapper eduMajorMapper;

    /**
     * 查询专业管理
     * 
     * @param majorId 专业管理主键
     * @return 专业管理
     */
    @Override
    public EduMajor selectEduMajorByMajorId(Long majorId)
    {
        return eduMajorMapper.selectEduMajorByMajorId(majorId);
    }

    /**
     * 查询专业管理列表
     * 
     * @param eduMajor 专业管理
     * @return 专业管理
     */
    @Override
    public List<EduMajor> selectEduMajorList(EduMajor eduMajor)
    {
        return eduMajorMapper.selectEduMajorList(eduMajor);
    }

    /**
     * 新增专业管理
     * 
     * @param eduMajor 专业管理
     * @return 结果
     */
    @Override
    public int insertEduMajor(EduMajor eduMajor)
    {
        eduMajor.setCreateTime(DateUtils.getNowDate());
        return eduMajorMapper.insertEduMajor(eduMajor);
    }

    /**
     * 修改专业管理
     * 
     * @param eduMajor 专业管理
     * @return 结果
     */
    @Override
    public int updateEduMajor(EduMajor eduMajor)
    {
        eduMajor.setUpdateTime(DateUtils.getNowDate());
        return eduMajorMapper.updateEduMajor(eduMajor);
    }

    /**
     * 批量删除专业管理
     * 
     * @param majorIds 需要删除的专业管理主键
     * @return 结果
     */
    @Override
    public int deleteEduMajorByMajorIds(Long[] majorIds)
    {
        return eduMajorMapper.deleteEduMajorByMajorIds(majorIds);
    }

    /**
     * 删除专业管理信息
     * 
     * @param majorId 专业管理主键
     * @return 结果
     */
    @Override
    public int deleteEduMajorByMajorId(Long majorId)
    {
        return eduMajorMapper.deleteEduMajorByMajorId(majorId);
    }
}
