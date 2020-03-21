package com.teamwork.project.team.service;

import com.teamwork.project.team.domain.TaskInfoLog;
import java.util.List;

/**
 * (TaskInfoLog)表服务接口
 *
 * @author makejava
 * @since 2020-02-21 20:26:07
 */
public interface TaskInfoLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TaskInfoLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TaskInfoLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param taskInfoLog 实例对象
     * @return 实例对象
     */
    TaskInfoLog insert(TaskInfoLog taskInfoLog);

    /**
     * 修改数据
     *
     * @param taskInfoLog 实例对象
     * @return 实例对象
     */
    TaskInfoLog update(TaskInfoLog taskInfoLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<TaskInfoLog> getTaskLog(Long taskId);
}