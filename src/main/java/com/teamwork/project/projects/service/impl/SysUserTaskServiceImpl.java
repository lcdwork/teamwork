package com.teamwork.project.projects.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.teamwork.project.projects.domain.SysUserTask;
import com.teamwork.project.projects.mapper.SysUserTaskMapper;
import com.teamwork.project.projects.service.SysUserTaskService;
@Service
public class SysUserTaskServiceImpl implements SysUserTaskService{

    @Resource
    private SysUserTaskMapper sysUserTaskMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysUserTaskMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUserTask record) {
        return sysUserTaskMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUserTask record) {
        return sysUserTaskMapper.insertSelective(record);
    }

    @Override
    public SysUserTask selectByPrimaryKey(Long id) {
        return sysUserTaskMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUserTask record) {
        return sysUserTaskMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysUserTask record) {
        return sysUserTaskMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatus(SysUserTask record) {
        return sysUserTaskMapper.updateStatus(record);
    }

}
