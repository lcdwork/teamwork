package com.teamwork.project.team.service;

import com.teamwork.project.team.domain.ProjectInfoLog;
import java.util.List;

/**
 * (ProjectInfoLog)表服务接口
 *
 * @author makejava
 * @since 2020-02-21 20:25:44
 */
public interface ProjectInfoLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectInfoLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProjectInfoLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param projectInfoLog 实例对象
     * @return 实例对象
     */
    ProjectInfoLog insert(ProjectInfoLog projectInfoLog);

    /**
     * 修改数据
     *
     * @param projectInfoLog 实例对象
     * @return 实例对象
     */
    ProjectInfoLog update(ProjectInfoLog projectInfoLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<ProjectInfoLog> getProjectLog(ProjectInfoLog projectInfoLog);
}
