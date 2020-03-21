package com.teamwork.project.team.mapper;

import com.teamwork.project.team.domain.Memo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (MemoInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-12 17:40:42
 */
public interface MemoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Memo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Memo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param memo 实例对象
     * @return 对象列表
     */
    List<Memo> queryAll(Memo memo);

    /**
     * 新增数据
     *
     * @param memo 实例对象
     * @return 影响行数
     */
    int insert(Memo memo);

    /**
     * 修改数据
     *
     * @param memo 实例对象
     * @return 影响行数
     */
    int update(Memo memo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int deleteByIds(List<Long> memoIds);
}