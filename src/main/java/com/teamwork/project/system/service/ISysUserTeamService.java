package com.teamwork.project.system.service;

import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.domain.SysUserTeam;

import java.util.List;

public interface ISysUserTeamService {
    SysUserTeam queryById(Long id);

    List<SysUserTeam> queryAllByLimit(int offset, int limit);

    int insert(SysUserTeam sysUserTeam);

//    int update(SysUserTeam sysUserTeam);

    boolean deleteById(Long id);

    int deleteByUserId(Long[] userIds);
}
