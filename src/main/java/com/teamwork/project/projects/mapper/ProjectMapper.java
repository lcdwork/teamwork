package com.teamwork.project.projects.mapper;

import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.system.domain.SysConfig;

import java.util.List;

public interface ProjectMapper {

    public List<Project> selectProjectList(Project project);

    public int deleteByPrimaryKey(Long projectId);

    public int insert(Project record);

    public int insertSelective(Project record);

    public Project selectByPrimaryKey(Long projectId);

    public int updateByPrimaryKeySelective(Project record);

    public int updateByPrimaryKey(Project record);
}
