package com.teamwork.project.projects.mapper;

import com.teamwork.project.projects.domain.Task;
import com.teamwork.project.system.domain.SysUser;

import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Long taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> selectTaskList(Task task);

    List<Task> selectTaskListByUser(Task task);

    List<Task> selectTaskRepeat(Task task);

    List<SysUser> selectTaskUsers(Long taskId);
}
