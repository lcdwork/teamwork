package com.teamwork.project.projects.domain;

import java.util.Date;
import java.io.Serializable;

/**
 * (ProjectInfoLog)实体类
 *
 * @author makejava
 * @since 2020-02-21 20:25:40
 */
public class ProjectInfoLog implements Serializable {
    private static final long serialVersionUID = -92015033458003627L;
    
    private Long id;
    
    private Long projectId;
    
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