package com.teamwork.project.projects.mapper;

import com.teamwork.project.projects.domain.SysUserNotice;
import com.teamwork.project.system.domain.SysNotice;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysUserNotice)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-24 21:03:49
 */
public interface SysUserNoticeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserNotice queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUserNotice> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserNotice 实例对象
     * @return 对象列表
     */
    List<SysUserNotice> queryAll(SysUserNotice sysUserNotice);

    /**
     * 新增数据
     *
     * @param sysUserNotice 实例对象
     * @return 影响行数
     */
    int insert(SysUserNotice sysUserNotice);

    /**
     * 修改数据
     *
     * @param sysUserNotice 实例对象
     * @return 影响行数
     */
    int update(SysUserNotice sysUserNotice);

    /**
     * 通过userId和noticeId修改数据
     *
     * @param sysUserNotice 实例对象
     * @return 影响行数
     */
    int updateRead(SysUserNotice sysUserNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int deleteByNoticeId(Long noticeId);

    int deleteByNoticeIds(List<Long> noticeIds);

    int delete(SysNotice notice);
}