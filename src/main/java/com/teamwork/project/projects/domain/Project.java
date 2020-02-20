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
        .toString();
    }
}
