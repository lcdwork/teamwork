package com.teamwork.project.team.service.impl;

import com.teamwork.common.utils.MonUtils;
import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.team.domain.*;
import com.teamwork.project.team.mapper.*;
import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.teamwork.project.team.service.TaskService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TaskInfoLogMapper taskInfoLogMapper;

    @Resource
    private ProjectInfoLogMapper projectInfoLogMapper;

    @Resource
    private SysUserTaskMapper sysUserTaskMapper;

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private MemoMapper memoMapper;

    @Override
    public int deleteByPrimaryKey(Task task) {
        projectInfoLogMapper.insert(insertProjectInfoLog(task, 5));
        sysUserTaskMapper.deleteByTaskId(task.getTaskId());
        return taskMapper.deleteByPrimaryKey(task.getTaskId());
    }

    @Override
    public int insert(Task record) {
        record.setCreateBy(SecurityUtils.getUsername());
        record.setCreateTime(new Date());
        record.setCreateUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        int i = taskMapper.insert(record);
        taskInfoLogMapper.insert(insertTaskInfoLog(record, 1));
        projectInfoLogMapper.insert(insertProjectInfoLog(record, 4));
        sysUserTaskMapper.deleteByTaskId(record.getTaskId());
        if (record.getUserList() != null &&record.getUserList().size() > 0) {
            List<SysUserTask> list = userTaskList(record);
            sysUserTaskMapper.insertList(list);
        }
        return i;
    }

    @Override
    public int convertMemo(Task task) {
        int i = 0;
        if (task.getConvertMemoList().size() > 0) {
            for (Memo memo : task.getConvertMemoList()) {
                Task t = new Task();
                t.setProjectId(task.getProjectId());
                t.setStartTime(task.getStartTime());
                t.setStopTime(task.getStopTime());
                t.setTaskName(memo.getMemoTitle());
                t.setRemark(memo.getMemoContent());
                t.setTaskTag((short) 2);
                t.setStatus((byte) 1);
                t.setCreateBy(SecurityUtils.getUsername());
                t.setCreateTime(new Date());
                t.setCreateUserId(SecurityUtils.getLoginUser().getUser().getUserId());
                taskMapper.insert(t);
                i++;
                taskInfoLogMapper.insert(insertTaskInfoLog(t, 1));
                projectInfoLogMapper.insert(insertProjectInfoLog(t, 4));
                if (task.getUserList() != null &&task.getUserList().size() > 0) {
                    t.setUserList(task.getUserList());
                    List<SysUserTask> list = userTaskList(t);
                    sysUserTaskMapper.insertList(list);
                }
                memo.setStatus((short) 1);
                memoMapper.update(memo);
            }
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
        taskInfoLogMapper.insert(insertTaskInfoLog(record, 2));
        if (record.getStatus() == 2) {
            taskInfoLogMapper.insert(insertTaskInfoLog(record, 3));
            projectInfoLogMapper.insert(insertProjectInfoLog(record, 6));
        }
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
    public List<Task> selectConvertRepeat(Task task) {
        List<Task> list = new ArrayList<>();
        if (task.getConvertMemoList().size() > 0) {
            for (Memo memo : task.getConvertMemoList()) {
                Task t = new Task();
                t.setProjectId(task.getProjectId());
                t.setTaskName(memo.getMemoTitle());
                List<Task> l = taskMapper.selectTaskRepeat(t);
                list.addAll(l);
            }
        }
        return list;
    }

    @Override
    public List<SysUser> selectTaskUsers(Long taskId) {
        return taskMapper.selectTaskUsers(taskId);
    }

    @Override
    public List<TaskList> selectTaskListByTime(Task task) {
        String startDate = task.getStartDate();
        String endDate = task.getEndDate();
        List<TaskList> lists = new ArrayList<>();
        return getTaskList(lists, startDate, endDate, task.getTaskUserId(), task.getTaskUserStatus());
    }

    public TaskInfoLog insertTaskInfoLog(Task task, int status) {
        TaskInfoLog t = new TaskInfoLog();
        t.setTaskId(task.getTaskId());
        t.setOperateTime(new Date());
        t.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        t.setStatus((byte) status);
        if (status == 1) {
            t.setContent(SecurityUtils.getLoginUser().getUser().getNickName() + "创建了" + task.getTaskName() + "任务");
        }
        if (status == 2) {
            t.setContent(SecurityUtils.getLoginUser().getUser().getNickName() + "编辑了" + task.getTaskName() + "任务");
        }
        if (status == 3) {
            t.setContent(task.getTaskName() + "任务完成了");
        }
        return t;
    }

    public ProjectInfoLog insertProjectInfoLog(Task task, int status) {
        ProjectInfoLog t = new ProjectInfoLog();
        t.setProjectId(task.getProjectId());
        t.setOperateTime(new Date());
        t.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        t.setStatus((byte) status);
        if (status == 4) {
            t.setContent(SecurityUtils.getLoginUser().getUser().getNickName() + "创建了" + task.getTaskName() + "任务");
        }
        if (status == 5) {
            t.setContent(SecurityUtils.getLoginUser().getUser().getNickName() + "删除了" + task.getTaskName() + "任务");
        }
        if (status == 6) {
            t.setContent(task.getTaskName() + "任务完成");
        }
        return t;
    }

    public List userTaskList(Task task) {
        List<SysUserTask> list = new ArrayList<>();
        List<SysUser> users = task.getUserList();
        users.forEach(u -> {
            SysUserTask sysUserTask = new SysUserTask();
            sysUserTask.setTaskId(task.getTaskId());
            sysUserTask.setUserId(u.getUserId());
            sysUserTask.setStatus((byte) 1);
            list.add(sysUserTask);
        });
        List<SysUserTask> returnList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(SysUserTask::getUserId))),ArrayList::new));
        return returnList;
    }

    public List<TaskList> getTaskList(List<TaskList> lists, String startDate, String endDate, Long taskUserId, Short taskUserStatus) {
        TaskList taskList = new TaskList();
        taskList.setTime(startDate);
        Task task = new Task();
        task.setTime(startDate);
        task.setTaskUserId(taskUserId);
        task.setTaskUserStatus(taskUserStatus);
        taskList.setList(taskMapper.selectTaskListByTime(task));
        lists.add(taskList);
        String s = MonUtils.getNextDay(startDate);
        if (!MonUtils.getNextDay(startDate).equals(endDate)) {
            getTaskList(lists, MonUtils.getNextDay(startDate), endDate, taskUserId, taskUserStatus);
        } else {
            TaskList tasks = new TaskList();
            tasks.setTime(endDate);
            Task t = new Task();
            t.setTime(endDate);
            t.setTaskUserId(taskUserId);
            t.setTaskUserStatus(taskUserStatus);
            tasks.setList(taskMapper.selectTaskListByTime(t));
            lists.add(tasks);
        }
        return lists;
    }

}
