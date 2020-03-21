package com.teamwork.project.team.service;

import com.teamwork.project.team.domain.SysUserTask;
public interface SysUserTaskService{


    int deleteByPrimaryKey(Long id);

    int insert(SysUserTask record);

    int insertSelective(SysUserTask record);

    SysUserTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserTask record);

    int updateByPrimaryKey(SysUserTask record);

    int updateStatus(SysUserTask record);

}
