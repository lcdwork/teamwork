package com.teamwork.project.team.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * (SysUserProject)实体类
 *
 * @author makejava
 * @since 2020-02-21 22:05:49
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserProject implements Serializable {
    private static final long serialVersionUID = 350965240300101667L;
    
    private Long id;
    
    private Long projectId;
    
    private Long userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}