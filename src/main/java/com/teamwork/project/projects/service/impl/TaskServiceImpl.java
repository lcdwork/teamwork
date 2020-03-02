package com.teamwork.project.projects.service.impl;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.projects.domain.*;
import com.teamwork.project.projects.mapper.SysUserTaskMapper;
import com.teamwork.project.projects.mapper.TaskInfoLogMapper;
import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.teamwork.project.projects.mapper.TaskMapper;
import com.teamwork.project.projects.service.TaskService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TaskInfoLogMapper taskInfoLogMapper;

    @Resource
    private SysUserTaskMapper sysUserTaskMapper;

    @Resource
    private SysUserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Long taskId) {
        taskInfoLogMapper.insert(insertTaskInfoLog(taskId, 3));
        sysUserTaskMapper.deleteByTaskId(taskId);
        return taskMapper.deleteByPrimaryKey(taskId);
    }

    @Override
    public int insert(Task record) {
        record.setCreateBy(SecurityUtils.getUsername());
        record.setCreateTime(new Date());
        int i = taskMapper.insert(record);
        taskInfoLogMapper.insert(insertTaskInfoLog(record.getTaskId(), 1));
        sysUserTaskMapper.deleteByTaskId(record.getTaskId());
        if (record.getUserList() != null &&record.getUserList().size() > 0) {
            List<SysUserTask> list = userTaskList(record);
            sysUserTaskMapper.insertList(list);
        }
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
        taskInfoLogMapper.insert(insertTaskInfoLog(record.getTaskId(), 2));
        record.setUpdateBy(SecurityUtils.getUsername());
        record.setUpdateTime(new Date());
        int i = taskMapper.updateByPrimaryKeySelective(record);
        sysUserTaskMapper.deleteByTaskId(record.getTaskId());
        if (record.getUserList() != null &&record.getUserList().size() > 0) {
            List<SysUserTask> list = userTaskList(record);
            sysUserTaskMapper.insertList(list);
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Task record) {
        return taskMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Task> selectTaskList(Task task) {
        List<Task> list = taskMapper.selectTaskList(task);
        list.forEach(t -> {
            List<SysUser> userList = userMapper.getListByTaskId(t);
            t.setUserList(userList);
        });
        return list;
    }

    @Override
    public List<Task> selectTaskListByUser(Task task) {
        return taskMapper.selectTaskListByUser(task);
    }

    @Override
    public List<Task> selectTaskRepeat(Task task) {
        return taskMapper.selectTaskRepeat(task);
    }

    @Override
    public List<SysUser> selectTaskUsers(Long taskId) {
        return taskMapper.selectTaskUsers(taskId);
    }

    @Override
    public List<Task> selectTaskListByTime(Task task) {
        return null;
    }

    public TaskInfoLog insertTaskInfoLog(Long taskId, int status) {
        TaskInfoLog t = new TaskInfoLog();
        t.setTaskId(taskId);
        t.setOperateTime(new Date());
        t.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        t.setStatus((byte) status);
        return t;
    }

    public List userTaskList(Task task) {
        List<SysUserTask> list = new ArrayList<>();
        List<SysUser> users = task.getUserList();
        users.forEach(u -> {
            SysUserTask sysUserTask = new SysUserTask();
            sysUserTask.setTaskId(task.getTaskId());
            sysUserTask.setUserId(u.getUserId());
            sysUserTask.setStatus((short) 1);
            list.add(sysUserTask);
        });
        List<SysUserTask> returnList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(SysUserTask::getUserId))),ArrayList::new));
        return returnList;
    }

}
