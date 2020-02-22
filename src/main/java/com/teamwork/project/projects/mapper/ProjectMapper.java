package com.teamwork.project.projects.mapper;

import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.system.domain.SysUser;

import java.util.List;

public interface ProjectMapper {

    List<Project> selectProjectList(Project record);

    int deleteByPrimaryKey(Long projectId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> selectProjectRepeat(Project project);

    List<SysUser> selectProjectUsers(Long projectId);
}
