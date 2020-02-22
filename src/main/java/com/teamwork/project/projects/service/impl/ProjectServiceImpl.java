package com.teamwork.project.projects.service.impl;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.projects.domain.ProjectInfoLog;
import com.teamwork.project.projects.domain.SysUserProject;
import com.teamwork.project.projects.domain.TaskInfoLog;
import com.teamwork.project.projects.mapper.ProjectInfoLogMapper;
import com.teamwork.project.projects.mapper.SysUserProjectMapper;
import com.teamwork.project.system.domain.SysUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.mapper.ProjectMapper;
import com.teamwork.project.projects.service.ProjectService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ProjectInfoLogMapper projectInfoLogMapper;

    @Resource
    SysUserProjectMapper sysUserProjectMapper;

    @Override
    public List<Project> selectProjectList(Project project)
    {
        return projectMapper.selectProjectList(project);
    }

    @Override
    public List<Project> selectProjectRepeat(Project project) {
        return projectMapper.selectProjectRepeat(project);
    }

    @Override
    public int deleteByPrimaryKey(Long projectId) {
        insertProjectInfoLog(projectId, 3);
        sysUserProjectMapper.deleteByProjectId(projectId);
        return projectMapper.deleteByPrimaryKey(projectId);
    }

    @Override
    public int insert(Project record) {
        insertProjectInfoLog(record.getProjectId(), 1);
        List<SysUserProject> list = userProjectList(record);
        sysUserProjectMapper.deleteByProjectId(record.getProjectId());
        sysUserProjectMapper.insertList(list);
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
        insertProjectInfoLog(record.getProjectId(), 2);
        List<SysUserProject> list = userProjectList(record);
        sysUserProjectMapper.deleteByProjectId(record.getProjectId());
        sysUserProjectMapper.insertList(list);
        return projectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        return projectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysUser> selectProjectUsers(Long projectId) {
        return projectMapper.selectProjectUsers(projectId);
    }

    public int insertProjectInfoLog(Long projectId, int status) {
        ProjectInfoLog t = new ProjectInfoLog();
        t.setProjectId(projectId);
        t.setOperatetime(new Date());
        t.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        t.setStatus((byte) status);
        return projectInfoLogMapper.insert(t);
    }

    public List userProjectList(Project project) {
        List<SysUserProject> list = new ArrayList<>();
        List<Long> users = project.getUsers();
        users.forEach(u -> {
            SysUserProject sysUserProject = new SysUserProject();
            sysUserProject.setProjectId(project.getProjectId());
            sysUserProject.setUserId(u);
            list.add(sysUserProject);
        });
        return list;
    }

}
