package com.teamwork.project.projects.domain;

import com.teamwork.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class Project extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
    * 项目id
    */
    private Long projectId;

    /**
    * 项目名称
    */
    private String projectName;

    /**
     * 项目开始时间
     */
    private Date startDate;

    /**
     * 项目结束时间
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("projectId", getProjectId())
        .append("projectName", getProjectName())
        .append("beginTime", getBeginTime())
        .append("endTime", getEndTime())
        .append("createBy", getCreateBy())
        .append("createTime", getCreateTime())
        .append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime())
        .append("remark", getRemark())
                .append("startDate", getStartDate())
                .append("endDate", getEndDate())
        .toString();
    }

}
