package com.ruoyi.affair.mapper;

import java.util.List;
import com.ruoyi.affair.domain.EduAffairHandleLog;
import org.apache.ibatis.annotations.Param;

/**
 * 事务处理日志Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public interface EduAffairHandleLogMapper 
{
    /**
     * 查询事务处理日志
     * 
     * @param logId 事务处理日志主键
     * @return 事务处理日志
     */
    public EduAffairHandleLog selectEduAffairHandleLogByLogId(Long logId);

    /**
     * 查询事务处理日志列表
     * 
     * @param eduAffairHandleLog 事务处理日志
     * @return 事务处理日志集合
     */
    public List<EduAffairHandleLog> selectEduAffairHandleLogList(EduAffairHandleLog eduAffairHandleLog);

    /**
     * 新增事务处理日志
     * 
     * @param eduAffairHandleLog 事务处理日志
     * @return 结果
     */
    public int insertEduAffairHandleLog(EduAffairHandleLog eduAffairHandleLog);

    /**
     * 删除事务处理日志
     * 
     * @param logId 事务处理日志主键
     * @return 结果
     */
    public int deleteEduAffairHandleLogByLogId(@Param("array") Long logId, @Param("updateUserId") String updateUserId);

    /**
     * 批量删除事务处理日志
     * 
     * @param logIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAffairHandleLogByLogIds(@Param("array") Long[] logIds, @Param("updateUserId") String updateUserId);
}
