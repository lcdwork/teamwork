package com.teamwork.project.projects.domain;

import java.util.Date;
import java.io.Serializable;

/**
 * (TaskInfoLog)实体类
 *
 * @author makejava
 * @since 2020-02-21 20:26:07
 */
public class TaskInfoLog implements Serializable {
    private static final long serialVersionUID = -55767164526430501L;
    
    private Long id;
    
    private Long taskId;
    
    private Long userId;
    
    private Date operatetime;
    
    private String content;
    /**
    * 1新增 2修改 3删除
    */
    private Byte status;
    
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}