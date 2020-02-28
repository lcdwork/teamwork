package com.teamwork.project.projects.service;

import com.teamwork.framework.web.domain.TreeSelect;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.system.domain.SysUser;

import java.util.List;

public interface ProjectService{


    List<Project> selectProjectRepeat(Project project);

    int deleteByPrimaryKey(Long projectId);

    int insert(Project record);

    int insertSelective(Project record);

    public List<Project> selectProjectList(Project project);

    Project selectByPrimaryKey(Long projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<SysUser> selectProjectUsers(Long projectId);

    List<TreeSelect> buildProjectTreeSelect(List<Project> projects);

    List<Project> buildProjectTree(List<Project> projects);

    List<Project> selectProjectListByUser(Project project);
}
