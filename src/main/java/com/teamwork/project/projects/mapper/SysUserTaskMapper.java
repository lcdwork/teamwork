package com.teamwork.project.projects.mapper;

import com.teamwork.project.projects.domain.SysUserTask;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface SysUserTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserTask record);

    int insertSelective(SysUserTask record);

    SysUserTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserTask record);

    int updateByPrimaryKey(SysUserTask record);

    int updateStatus(SysUserTask record);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserTask queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUserTask> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserTask 实例对象
     * @return 对象列表
     */
    List<SysUserTask> queryAll(SysUserTask sysUserTask);

    /**
     * 修改数据
     *
     * @param sysUserTask 实例对象
     * @return 影响行数
     */
    int update(SysUserTask sysUserTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    void insertList(List<SysUserTask> list);

    void deleteByTaskId(Long taskId);
}
