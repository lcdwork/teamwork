package com.teamwork.project.projects.service;

import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.system.domain.SysUser;

import java.util.List;

public interface ProjectService{

    public List<Project> selectProjectList(Project project);

    int deleteByPrimaryKey(Long projectId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

}
