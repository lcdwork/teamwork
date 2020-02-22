package com.teamwork.project.projects.mapper;

import com.teamwork.project.projects.domain.TaskInfoLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TaskInfoLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-21 20:26:07
 */
public interface TaskInfoLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TaskInfoLog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TaskInfoLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param taskInfoLog 实例对象
     * @return 对象列表
     */
    List<TaskInfoLog> queryAll(TaskInfoLog taskInfoLog);

    /**
     * 新增数据
     *
     * @param taskInfoLog 实例对象
     * @return 影响行数
     */
    int insert(TaskInfoLog taskInfoLog);

    /**
     * 修改数据
     *
     * @param taskInfoLog 实例对象
     * @return 影响行数
     */
    int update(TaskInfoLog taskInfoLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<TaskInfoLog> getTaskLog(Long taskId);
}