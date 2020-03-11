package com.teamwork.project.system.mapper;

import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.domain.SysUserTeam;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysUserTeam)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-10 17:57:00
 */
public interface SysUserTeamMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserTeam queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUserTeam> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserTeam 实例对象
     * @return 对象列表
     */
    List<SysUserTeam> queryAll(SysUserTeam sysUserTeam);

    /**
     * 新增数据
     *
     * @param sysUserTeam 实例对象
     * @return 影响行数
     */
    int insert(SysUserTeam sysUserTeam);

    /**
     * 修改数据
     *
     * @param sysUserTeam 实例对象
     * @return 影响行数
     */
    int update(SysUserTeam sysUserTeam);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    void delete(SysUserTeam userTeam);
}