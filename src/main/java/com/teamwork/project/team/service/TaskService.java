package com.teamwork.project.team.service;

import com.teamwork.project.team.domain.Task;
import com.teamwork.project.team.domain.TaskList;
import com.teamwork.project.system.domain.SysUser;

import java.util.List;

public interface TaskService{


    int deleteByPrimaryKey(Task task);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> selectTaskList(Task task);

    List<Task> selectTaskListByUser(Task task);

    List<Task> selectTaskRepeat(Task task);

    List<SysUser> selectTaskUsers(Long taskId);

    List<TaskList> selectTaskListByTime(Task task);

    int convertMemo(Task task);

    List<Task> selectConvertRepeat(Task task);
}
