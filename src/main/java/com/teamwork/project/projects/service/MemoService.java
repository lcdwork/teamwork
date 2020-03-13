package com.teamwork.project.projects.service;

import com.teamwork.project.projects.domain.Memo;
import com.teamwork.project.projects.domain.Project;

import java.util.List;

/**
 * (Memo)表服务接口
 *
 * @author makejava
 * @since 2020-03-12 17:40:42
 */
public interface MemoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Memo queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Memo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    int insert(Memo memo);

    /**
     * 修改数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    int update(Memo memo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Long id);

    int delete(List<Long> memoIds);

    List<Memo> queryAll(Memo memo);
}