package com.ruoyi.teachingmanagement.mapper;

import com.ruoyi.teachingmanagement.domain.EduMajor;

import java.util.List;


/**
 * 专业管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduMajorMapper 
{
    /**
     * 查询专业管理
     * 
     * @param majorId 专业管理主键
     * @return 专业管理
     */
    public EduMajor selectEduMajorByMajorId(Long majorId);

    /**
     * 查询专业管理列表
     * 
     * @param eduMajor 专业管理
     * @return 专业管理集合
     */
    public List<EduMajor> selectEduMajorList(EduMajor eduMajor);

    /**
     * 新增专业管理
     * 
     * @param eduMajor 专业管理
     * @return 结果
     */
    public int insertEduMajor(EduMajor eduMajor);

    /**
     * 修改专业管理
     * 
     * @param eduMajor 专业管理
     * @return 结果
     */
    public int updateEduMajor(EduMajor eduMajor);

    /**
     * 删除专业管理
     * 
     * @param majorId 专业管理主键
     * @return 结果
     */
    public int deleteEduMajorByMajorId(Long majorId);

    /**
     * 批量删除专业管理
     * 
     * @param majorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduMajorByMajorIds(Long[] majorIds);
}
