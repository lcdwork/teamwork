package com.teamwork.project.projects.service;

import com.teamwork.project.projects.domain.Task;
public interface TaskService{


    int deleteByPrimaryKey(Long taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

}
