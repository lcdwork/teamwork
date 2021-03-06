package com.teamwork.project.team.mapper;

import com.teamwork.project.team.domain.Task;
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

    List<Task> selectTaskListByTime(Task task);
}
