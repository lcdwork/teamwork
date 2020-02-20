package com.teamwork.project.projects.domain;

import com.teamwork.framework.web.domain.BaseEntity;
import java.util.Date;

public class Project extends BaseEntity {
    /**
    * 项目id
    */
    private Long projectId;

    /**
    * 项目名称
    */
    private String projectName;

    /**
    * 开始时间
    */
    private Date startDate;

    /**
    * 结束时间
    */
    private Date endDate;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}