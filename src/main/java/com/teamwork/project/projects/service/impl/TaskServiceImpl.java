package com.teamwork.project.projects.service.impl;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.projects.domain.*;
import com.teamwork.project.projects.mapper.SysUserTaskMapper;
import com.teamwork.project.projects.mapper.TaskInfoLogMapper;
import com.teamwork.project.system.domain.SysUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.teamwork.project.projects.mapper.TaskMapper;
import com.teamwork.project.projects.service.TaskService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Resource
    private TaskMapper taskMapper;

    @Resource
    TaskInfoLogMapper taskInfoLogMapper;

    @Resource
    SysUserTaskMapper sysUserTaskMapper;

    @Override
    public int deleteByPrimaryKey(Long taskId) {
        insertTaskInfoLog(taskId, 3);
        return taskMapper.deleteByPrimaryKey(taskId);
    }

    @Override
    public int insert(Task record) {
        int i = taskMapper.insert(record);
        insertTaskInfoLog(record.getTaskId(), 1);
        List<SysUserTask> list = userTaskList(record);
        sysUserTaskMapper.insertList(list);
        return i;
    }

    @Override
    public int insertSelective(Task record) {
        return taskMapper.insertSelective(record);
    }

    @Override
    public Task selectByPrimaryKey(Long taskId) {
        return taskMapper.selectByPrimaryKey(taskId);
    }

    @Override
    public int updateByPrimaryKeySelective(Task record) {
        insertTaskInfoLog(record.getTaskId(), 2);
        return taskMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Task record) {
        return taskMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Task> selectTaskList(Task task) {
        return taskMapper.selectTaskList(task);
    }

    @Override
    public List<Task> selectTaskRepeat(Task task) {
        return taskMapper.selectTaskRepeat(task);
    }

    @Override
    public List<SysUser> selectTaskUsers(Long taskId) {
        return taskMapper.selectTaskUsers(taskId);
    }

    public int insertTaskInfoLog(Long taskId, int status) {
        TaskInfoLog t = new TaskInfoLog();
        t.setTaskId(taskId);
        t.setOperatetime(new Date());
        t.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        t.setStatus((byte) status);
        return taskInfoLogMapper.insert(t);
    }

    public List userTaskList(Task task) {
        List<SysUserTask> list = new ArrayList<>();
        List<SysUser> users = task.getUserList();
        users.forEach(u -> {
            SysUserTask sysUserTask = new SysUserTask();
            sysUserTask.setTaskId(task.getTaskId());
            sysUserTask.setUserId(u.getUserId());
            list.add(sysUserTask);
        });
        return list;
    }

}
