package com.teamwork.framework.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.domain.Task;

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
    private String id;

    /** 父节点ID */
    private String pid;

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
        this.id = project.getProjectId().toString();
        this.label = project.getProjectName();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        if(project.getTaskList() != null && project.getTaskList().size() > 0) {
            this.children = project.getTaskList().stream().map(GanttTree::new).collect(Collectors.toList());
        }
    }
    public GanttTree(Task task) {
        this.id = task.getProjectId() + "-" + task.getTaskId();
        this.pid = task.getProjectId().toString();
        this.label = task.getTaskName();
        this.startDate = task.getStartTime();
        this.endDate = task.getStopTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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
