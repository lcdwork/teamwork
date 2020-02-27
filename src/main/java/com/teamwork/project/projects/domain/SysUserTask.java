package com.teamwork.project.projects.domain;

import com.teamwork.framework.web.domain.BaseEntity;

public class SysUserTask extends BaseEntity {
    private static final long serialVersionUID = -30447064077908622L;
    private Long id;

    private Long userId;

    private Long taskId;

    private Short status;

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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
