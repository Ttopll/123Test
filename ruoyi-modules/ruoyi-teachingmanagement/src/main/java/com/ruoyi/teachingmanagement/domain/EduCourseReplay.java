package com.ruoyi.teachingmanagement.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 课程回放对象 edu_course_replay
 * 
 * @author ruoyi
 * @date 2026-01-23
 */
public class EduCourseReplay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 回放记录 ID */
    private Long replayId;

    /** 关联教师课程关联 ID */
    @Excel(name = "关联教师课程关联 ID")
    private Long teacherCourseId;

    /** 课程所属学期（如：2024-2025-1） */
    @Excel(name = "课程所属学期", readConverterExp = "如=：2024-2025-1")
    private String semester;

    /** 回放标题（如：《数据库原理》第 3 课 - 索引设计） */
    @Excel(name = "回放标题", readConverterExp = "如=：《数据库原理》第,3=,课=,-=,索=引设计")
    private String replayTitle;

    /** 视频存储 URL（支持云存储地址） */
    @Excel(name = "视频存储 URL", readConverterExp = "支=持云存储地址")
    private String videoUrl;

    /** 视频时长（秒） */
    @Excel(name = "视频时长", readConverterExp = "秒=")
    private Long videoDuration;

    /** 视频封面图 URL */
    @Excel(name = "视频封面图 URL")
    private String coverUrl;

    /** 课程录制日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "课程录制日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 课程录制开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "课程录制开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 累计播放次数 */
    @Excel(name = "累计播放次数")
    private Long playCount;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 更新用户 */
    @Excel(name = "更新用户")
    private String updateUserId;

    /** 删除状态 */
    @Excel(name = "删除状态")
    private Integer isDeleted;

    public void setReplayId(Long replayId) 
    {
        this.replayId = replayId;
    }

    public Long getReplayId() 
    {
        return replayId;
    }

    public void setTeacherCourseId(Long teacherCourseId) 
    {
        this.teacherCourseId = teacherCourseId;
    }

    public Long getTeacherCourseId() 
    {
        return teacherCourseId;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
    }

    public void setReplayTitle(String replayTitle) 
    {
        this.replayTitle = replayTitle;
    }

    public String getReplayTitle() 
    {
        return replayTitle;
    }

    public void setVideoUrl(String videoUrl) 
    {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() 
    {
        return videoUrl;
    }

    public void setVideoDuration(Long videoDuration) 
    {
        this.videoDuration = videoDuration;
    }

    public Long getVideoDuration() 
    {
        return videoDuration;
    }

    public void setCoverUrl(String coverUrl) 
    {
        this.coverUrl = coverUrl;
    }

    public String getCoverUrl() 
    {
        return coverUrl;
    }

    public void setRecordDate(Date recordDate) 
    {
        this.recordDate = recordDate;
    }

    public Date getRecordDate() 
    {
        return recordDate;
    }

    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
    }

    public void setPlayCount(Long playCount) 
    {
        this.playCount = playCount;
    }

    public Long getPlayCount() 
    {
        return playCount;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setUpdateUserId(String updateUserId) 
    {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserId() 
    {
        return updateUserId;
    }

    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("replayId", getReplayId())
            .append("teacherCourseId", getTeacherCourseId())
            .append("semester", getSemester())
            .append("replayTitle", getReplayTitle())
            .append("videoUrl", getVideoUrl())
            .append("videoDuration", getVideoDuration())
            .append("coverUrl", getCoverUrl())
            .append("recordDate", getRecordDate())
            .append("recordTime", getRecordTime())
            .append("playCount", getPlayCount())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
