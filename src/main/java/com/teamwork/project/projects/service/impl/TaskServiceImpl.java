package com.teamwork.project.projects.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.teamwork.project.projects.mapper.TaskMapper;
import com.teamwork.project.projects.domain.Task;
import com.teamwork.project.projects.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public int deleteByPrimaryKey(Integer taskId) {
        return taskMapper.deleteByPrimaryKey(taskId);
    }

    @Override
    public int insert(Task record) {
        return taskMapper.insert(record);
    }

    @Override
    public int insertSelective(Task record) {
        return taskMapper.insertSelective(record);
    }

    @Override
    public Task selectByPrimaryKey(Integer taskId) {
        return taskMapper.selectByPrimaryKey(taskId);
    }

    @Override
    public int updateByPrimaryKeySelective(Task record) {
        return taskMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Task record) {
        return taskMapper.updateByPrimaryKey(record);
    }

}

