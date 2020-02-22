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
        projectInfoLogMapper.insert(insertProjectInfoLog(projectId, 3));
        sysUserProjectMapper.deleteByProjectId(projectId);
        return projectMapper.deleteByPrimaryKey(projectId);
    }

    @Override
    public int insert(Project record) {
        record.setCreateBy(SecurityUtils.getUsername());
        record.setCreateTime(new Date());
        int i = projectMapper.insert(record);
        projectInfoLogMapper.insert(insertProjectInfoLog(record.getProjectId(), 1));
        sysUserProjectMapper.deleteByProjectId(record.getProjectId());
        if (record.getUserList() != null && record.getUserList().size() > 0) {
            List<SysUserProject> list = userProjectList(record);
            sysUserProjectMapper.insertList(list);
        }
        return i;
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
        record.setUpdateBy(SecurityUtils.getUsername());
        record.setUpdateTime(new Date());
        int i = projectMapper.updateByPrimaryKeySelective(record);
        projectInfoLogMapper.insert(insertProjectInfoLog(record.getProjectId(), 2));
        sysUserProjectMapper.deleteByProjectId(record.getProjectId());
        if (record.getUserList() != null && record.getUserList().size() > 0) {
            List<SysUserProject> list = userProjectList(record);
            sysUserProjectMapper.insertList(list);
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        return projectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysUser> selectProjectUsers(Long projectId) {
        return projectMapper.selectProjectUsers(projectId);
    }

    public ProjectInfoLog insertProjectInfoLog(Long projectId, int status) {
        ProjectInfoLog t = new ProjectInfoLog();
        t.setProjectId(projectId);
        t.setOperatetime(new Date());
        t.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        t.setStatus((byte) status);
        return t;
    }

    public List userProjectList(Project project) {
        List<SysUserProject> list = new ArrayList<>();
        List<SysUser> users = project.getUserList();
        users.forEach(u -> {
            SysUserProject sysUserProject = new SysUserProject();
            sysUserProject.setProjectId(project.getProjectId());
            sysUserProject.setUserId(u.getUserId());
            list.add(sysUserProject);
        });
        return list;
    }

}
