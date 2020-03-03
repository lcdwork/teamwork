package com.teamwork.project.projects.domain;

import com.teamwork.framework.web.domain.BaseEntity;

public class SysUserTask extends BaseEntity {
    private static final long serialVersionUID = -30447064077908622L;
    private Long id;

    private Long userId;

    private Long taskId;

    private Byte status;

    private String taskName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
