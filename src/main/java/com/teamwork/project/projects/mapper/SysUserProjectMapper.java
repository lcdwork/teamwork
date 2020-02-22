package com.teamwork.project.projects.mapper;

import com.teamwork.project.projects.domain.SysUserProject;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysUserProject)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-21 22:05:49
 */
public interface SysUserProjectMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserProject queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUserProject> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserProject 实例对象
     * @return 对象列表
     */
    List<SysUserProject> queryAll(SysUserProject sysUserProject);

    /**
     * 新增数据
     *
     * @param sysUserProject 实例对象
     * @return 影响行数
     */
    int insert(SysUserProject sysUserProject);

    /**
     * 修改数据
     *
     * @param sysUserProject 实例对象
     * @return 影响行数
     */
    int update(SysUserProject sysUserProject);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int insertList(List<SysUserProject> list);

    void deleteByProjectId(Long projectId);
}