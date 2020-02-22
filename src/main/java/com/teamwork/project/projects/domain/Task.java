package com.teamwork.project.projects.domain;

import com.teamwork.framework.web.domain.BaseEntity;
import com.teamwork.project.system.domain.SysUser;

import java.util.Date;
import java.util.List;

public class Task extends BaseEntity {
    /**
    * 任务id
    */
    private Long taskId;

    /**
    * 所属项目
    */
    private Long projectId;

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
    private Byte status;

    private List<Long> users;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectid(Long projectid) {
        this.projectId = projectid;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }
}
