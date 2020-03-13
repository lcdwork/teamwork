package com.teamwork.project.projects.service.impl;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.projects.domain.Memo;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.mapper.MemoMapper;
import com.teamwork.project.projects.service.MemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (MemoInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-03-12 17:40:42
 */
@Service("memoService")
public class MemoServiceImpl implements MemoService {
    @Resource
    private MemoMapper memoMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Memo queryById(Long id) {
        return this.memoMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Memo> queryAllByLimit(int offset, int limit) {
        return this.memoMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param memo 实例对象
     * @return int
     */
    @Override
    public int insert(Memo memo) {
        memo.setCreateUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        memo.setCreateBy(SecurityUtils.getUsername());
        memo.setCreateTime(new Date());
        if (memo.getMemoTime() == null) {
            memo.setMemoTime(new Date());
        }
        return memoMapper.insert(memo);
    }

    /**
     * 修改数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Memo memo) {
        return memoMapper.update(memo);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long id) {
        return memoMapper.deleteById(id);
    }

    @Override
    public int delete(List<Long> memoIds) {
        return memoMapper.deleteByIds(memoIds);
    }

    @Override
    public List<Memo> queryAll(Memo memo) {
        return memoMapper.queryAll(memo);
    }
}