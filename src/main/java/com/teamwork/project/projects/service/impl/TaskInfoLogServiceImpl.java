package com.teamwork.project.projects.service.impl;

import com.teamwork.project.projects.domain.TaskInfoLog;
import com.teamwork.project.projects.mapper.TaskInfoLogMapper;
import com.teamwork.project.projects.service.TaskInfoLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TaskInfoLog)表服务实现类
 *
 * @author makejava
 * @since 2020-02-21 20:26:07
 */
@Service("taskInfoLogService")
public class TaskInfoLogServiceImpl implements TaskInfoLogService {
    @Resource
    private TaskInfoLogMapper taskInfoLogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TaskInfoLog queryById(Long id) {
        return this.taskInfoLogMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TaskInfoLog> queryAllByLimit(int offset, int limit) {
        return this.taskInfoLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param taskInfoLog 实例对象
     * @return 实例对象
     */
    @Override
    public TaskInfoLog insert(TaskInfoLog taskInfoLog) {
        this.taskInfoLogMapper.insert(taskInfoLog);
        return taskInfoLog;
    }

    /**
     * 修改数据
     *
     * @param taskInfoLog 实例对象
     * @return 实例对象
     */
    @Override
    public TaskInfoLog update(TaskInfoLog taskInfoLog) {
        this.taskInfoLogMapper.update(taskInfoLog);
        return this.queryById(taskInfoLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.taskInfoLogMapper.deleteById(id) > 0;
    }

    @Override
    public List<TaskInfoLog> getTaskLog(Long taskId) {
        return this.taskInfoLogMapper.getTaskLog(taskId);
    }
}