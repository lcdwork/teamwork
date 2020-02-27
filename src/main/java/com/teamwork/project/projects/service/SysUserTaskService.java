package com.teamwork.project.projects.service;

import com.teamwork.project.projects.domain.SysUserTask;
public interface SysUserTaskService{


    int deleteByPrimaryKey(Long id);

    int insert(SysUserTask record);

    int insertSelective(SysUserTask record);

    SysUserTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserTask record);

    int updateByPrimaryKey(SysUserTask record);

    int updateStatus(SysUserTask record);

}
