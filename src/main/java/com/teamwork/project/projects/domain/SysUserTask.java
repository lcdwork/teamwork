package com.teamwork.project.projects.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * (SysUserTask)实体类
 *
 * @author makejava
 * @since 2020-02-21 22:05:25
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserTask implements Serializable {
    private static final long serialVersionUID = -30447064077908622L;
    
    private Long id;
    
    private Long userId;
    
    private Long taskId;


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

}