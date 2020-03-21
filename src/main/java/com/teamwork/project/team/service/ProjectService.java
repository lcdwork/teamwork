package com.teamwork.project.team.service;

import com.teamwork.framework.web.domain.GanttTree;
import com.teamwork.framework.web.domain.TreeSelect;
import com.teamwork.project.team.domain.Project;
import com.teamwork.project.system.domain.SysUser;

import java.util.List;

public interface ProjectService{


    List<Project> selectProjectRepeat(Project project);

    int deleteByPrimaryKey(Project project);

    int insert(Project record);

    int insertSelective(Project record);

    public List<Project> selectProjectList(Project project);

    Project selectByPrimaryKey(Long projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<SysUser> selectProjectUsers(Long projectId);

    List<TreeSelect> buildProjectTreeSelect(List<Project> projects);

    List<Project> buildProjectTree(List<Project> projects);

    List<GanttTree> buildProjectGanttTreeSelect(List<Project> projects);

    List<Project> buildProjectGanttTree(List<Project> projects);

    List<Project> selectProjectListByUser(Project project);
}
