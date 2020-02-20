package com.teamwork.project.projects.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.mapper.ProjectMapper;
import com.teamwork.project.projects.service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public List<Project> selectProjectList(Project project)
    {
        return projectMapper.selectProjectList(project);
    }

    @Override
    public int deleteByPrimaryKey(Long projectId) {
        return projectMapper.deleteByPrimaryKey(projectId);
    }

    @Override
    public int insert(Project record) {
        return projectMapper.insert(record);
    }

    @Override
    public int insertSelective(Project record) {
        return projectMapper.insertSelective(record);
    }

    @Override
    public Project selectByPrimaryKey(Long projectId) {
        return projectMapper.selectByPrimaryKey(projectId);
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        return projectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        return projectMapper.updateByPrimaryKey(record);
    }

}
