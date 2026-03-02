package com.ruoyi.teachingmanagement.service;

import com.ruoyi.teachingmanagement.domain.EduDepartment;

import java.util.List;


/**
 * 院系管理Service接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface IEduDepartmentService 
{
    /**
     * 查询院系管理
     * 
     * @param deptId 院系管理主键
     * @return 院系管理
     */
    public EduDepartment selectEduDepartmentByDeptId(Long deptId);

    /**
     * 查询院系管理列表
     * 
     * @param eduDepartment 院系管理
     * @return 院系管理集合
     */
    public List<EduDepartment> selectEduDepartmentList(EduDepartment eduDepartment);

    /**
     * 新增院系管理
     * 
     * @param eduDepartment 院系管理
     * @return 结果
     */
    public int insertEduDepartment(EduDepartment eduDepartment);

    /**
     * 修改院系管理
     * 
     * @param eduDepartment 院系管理
     * @return 结果
     */
    public int updateEduDepartment(EduDepartment eduDepartment);

    /**
     * 批量删除院系管理
     * 
     * @param deptIds 需要删除的院系管理主键集合
     * @return 结果
     */
    public int deleteEduDepartmentByDeptIds(Long[] deptIds);

    /**
     * 删除院系管理信息
     * 
     * @param deptId 院系管理主键
     * @return 结果
     */
    public int deleteEduDepartmentByDeptId(Long deptId);
}
