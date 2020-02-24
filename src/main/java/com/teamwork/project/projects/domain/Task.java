package com.teamwork.project.projects.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teamwork.framework.web.domain.BaseEntity;
import com.teamwork.project.system.domain.SysUser;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    * 开始时间
    */
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
    * 结束时间
    */
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date stopTime;

    /**
    * 任务状态
    */
    private Byte status;

    private List<SysUser> userList;

    private String projectName;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public List<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
