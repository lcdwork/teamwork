package com.teamwork.framework.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.domain.Task;
import com.teamwork.project.system.domain.SysUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: LCD
 * @date Create in: 09:54 2020/3/5
 * @description：
 * @modify:
 * @see: com.teamwork.framework.web.domain
 */
public class GanttTree implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<GanttTree> children;

    public GanttTree()
    {

    }

    public GanttTree(Project project) {
        this.id = project.getProjectId();
        this.label = project.getProjectName();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        if(project.getTaskList() != null && project.getTaskList().size() > 0) {
            this.children = project.getTaskList().stream().map(GanttTree::new).collect(Collectors.toList());
        }
    }
    public GanttTree(Task task) {
        this.id = task.getTaskId();
        this.label = task.getTaskName();
        this.startDate = task.getStartTime();
        this.endDate = task.getStopTime();
    }

    public GanttTree(SysUser user) {
        this.id = user.getUserId();
        this.label = user.getUserName();
        if (user.getTaskList() != null && user.getTaskList().size() > 0) {
            this.children = user.getTaskList().stream().map(GanttTree::new).collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public List<GanttTree> getChildren() {
        return children;
    }

    public void setChildren(List<GanttTree> children) {
        this.children = children;
    }
}
