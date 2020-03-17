package com.teamwork.project.projects.service.impl;

import com.teamwork.project.projects.domain.SysUserNotice;
import com.teamwork.project.projects.mapper.SysUserNoticeMapper;
import com.teamwork.project.projects.service.SysUserNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUserNotice)表服务实现类
 *
 * @author makejava
 * @since 2020-03-05 20:56:52
 */
@Service
public class SysUserNoticeServiceImpl implements SysUserNoticeService {

    @Resource
    private SysUserNoticeMapper userNoticeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserNotice queryById(Long id) {
        return this.userNoticeMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUserNotice> queryAllByLimit(int offset, int limit) {
        return this.userNoticeMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUserNotice 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserNotice insert(SysUserNotice sysUserNotice) {
        this.userNoticeMapper.insert(sysUserNotice);
        return sysUserNotice;
    }

    /**
     * 修改数据
     *
     * @param sysUserNotice 实例对象
     * @return 实例对象
     */
    @Override
    public int updateRead(SysUserNotice sysUserNotice) {
        return userNoticeMapper.updateRead(sysUserNotice);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userNoticeMapper.deleteById(id) > 0;
    }
}