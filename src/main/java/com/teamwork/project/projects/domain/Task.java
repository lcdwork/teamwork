package com.teamwork.project.projects.domain;

import com.teamwork.framework.web.domain.BaseEntity;
import java.util.Date;

public class Task extends BaseEntity {
    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 所属项目
     */
    private Long projectid;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务标签
     */
    private Short taskTag;

    /**
     * 参与人员
     */
    private String usersGroup;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date stopTime;

    /**
     * 任务状态
     */
    private Short status;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Short getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(Short taskTag) {
        this.taskTag = taskTag;
    }

    public String getUsersGroup() {
        return usersGroup;
    }

    public void setUsersGroup(String usersGroup) {
        this.usersGroup = usersGroup;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}