package com.teamwork.project.projects.service;

import com.teamwork.project.projects.domain.SysUserNotice;

import java.util.List;

/**
 * (SysUserNotice)表服务接口
 *
 * @author makejava
 * @since 2020-03-05 20:56:48
 */
public interface SysUserNoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserNotice queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUserNotice> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUserNotice 实例对象
     * @return 实例对象
     */
    SysUserNotice insert(SysUserNotice sysUserNotice);

//    /**
//     * 修改数据
//     *
//     * @param sysUserNotice 实例对象
//     * @return 实例对象
//     */
//    int updateRead(SysUserNotice sysUserNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}