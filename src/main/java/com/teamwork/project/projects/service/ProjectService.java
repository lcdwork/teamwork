package com.teamwork.project.projects.service;

import com.teamwork.project.projects.domain.Project;

import java.util.List;

public interface ProjectService{


    int deleteByPrimaryKey(Long projectId);

    int insert(Project record);

    int insertSelective(Project record);

    public List<Project> selectProjectList(Project project);

    Project selectByPrimaryKey(Long projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

}
