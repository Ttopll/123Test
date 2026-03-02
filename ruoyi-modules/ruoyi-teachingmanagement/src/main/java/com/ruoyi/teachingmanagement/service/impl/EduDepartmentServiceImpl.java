package com.ruoyi.teachingmanagement.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.teachingmanagement.domain.EduDepartment;
import com.ruoyi.teachingmanagement.mapper.EduDepartmentMapper;
import com.ruoyi.teachingmanagement.service.IEduDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 院系管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
@Service
public class EduDepartmentServiceImpl implements IEduDepartmentService
{
    @Autowired
    private EduDepartmentMapper eduDepartmentMapper;

    /**
     * 查询院系管理
     * 
     * @param deptId 院系管理主键
     * @return 院系管理
     */
    @Override
    public EduDepartment selectEduDepartmentByDeptId(Long deptId)
    {
        return eduDepartmentMapper.selectEduDepartmentByDeptId(deptId);
    }

    /**
     * 查询院系管理列表
     * 
     * @param eduDepartment 院系管理
     * @return 院系管理
     */
    @Override
    public List<EduDepartment> selectEduDepartmentList(EduDepartment eduDepartment)
    {
        return eduDepartmentMapper.selectEduDepartmentList(eduDepartment);
    }

    /**
     * 新增院系管理
     * 
     * @param eduDepartment 院系管理
     * @return 结果
     */
    @Override
    public int insertEduDepartment(EduDepartment eduDepartment)
    {
        eduDepartment.setCreateTime(DateUtils.getNowDate());
        return eduDepartmentMapper.insertEduDepartment(eduDepartment);
    }

    /**
     * 修改院系管理
     * 
     * @param eduDepartment 院系管理
     * @return 结果
     */
    @Override
    public int updateEduDepartment(EduDepartment eduDepartment)
    {
        eduDepartment.setUpdateTime(DateUtils.getNowDate());
        return eduDepartmentMapper.updateEduDepartment(eduDepartment);
    }

    /**
     * 批量删除院系管理
     * 
     * @param deptIds 需要删除的院系管理主键
     * @return 结果
     */
    @Override
    public int deleteEduDepartmentByDeptIds(Long[] deptIds)
    {
        return eduDepartmentMapper.deleteEduDepartmentByDeptIds(deptIds);
    }

    /**
     * 删除院系管理信息
     * 
     * @param deptId 院系管理主键
     * @return 结果
     */
    @Override
    public int deleteEduDepartmentByDeptId(Long deptId)
    {
        return eduDepartmentMapper.deleteEduDepartmentByDeptId(deptId);
    }
}
