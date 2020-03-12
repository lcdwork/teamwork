package com.teamwork.project.projects.domain;

import java.io.Serializable;

/**
 * (SysUserNotice)实体类
 *
 * @author makejava
 * @since 2020-02-24 21:02:10
 */
public class SysUserNotice implements Serializable {
    private static final long serialVersionUID = 970743379110256945L;
    
    private Long id;
    
    private Long userId;
    
    private Long noticeId;

    private String status;

    private String readStatus;

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

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }
}