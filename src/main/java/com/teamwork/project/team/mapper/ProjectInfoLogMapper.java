package com.teamwork.project.team.mapper;

import com.teamwork.project.team.domain.ProjectInfoLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ProjectInfoLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-21 20:25:43
 */
public interface ProjectInfoLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectInfoLog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProjectInfoLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectInfoLog 实例对象
     * @return 对象列表
     */
    List<ProjectInfoLog> queryAll(ProjectInfoLog projectInfoLog);

    /**
     * 新增数据
     *
     * @param projectInfoLog 实例对象
     * @return 影响行数
     */
    int insert(ProjectInfoLog projectInfoLog);

    /**
     * 修改数据
     *
     * @param projectInfoLog 实例对象
     * @return 影响行数
     */
    int update(ProjectInfoLog projectInfoLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<ProjectInfoLog> getProjectLog(ProjectInfoLog projectInfoLog);
}
