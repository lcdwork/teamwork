package com.teamwork.project.projects.service;

import com.teamwork.project.projects.domain.Task;

public interface TaskService {


    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

}

