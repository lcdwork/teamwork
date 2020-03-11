package com.teamwork.project.system.domain;

import java.io.Serializable;
import java.util.List;

/**
 * (SysUserTeam)实体类
 *
 * @author makejava
 * @since 2020-03-10 17:56:58
 */
public class SysUserTeam implements Serializable {
    private static final long serialVersionUID = -78794598576029574L;
    
    private Long id;
    
    private Long pUserId;
    
    private Long userId;
    
    private Object status;

    private List<Long> userIds;

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

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Long getpUserId() {
        return pUserId;
    }

    public void setpUserId(Long pUserId) {
        this.pUserId = pUserId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}