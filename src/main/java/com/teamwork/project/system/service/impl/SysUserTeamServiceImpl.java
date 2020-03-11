package com.teamwork.project.system.service.impl;


import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.domain.SysUserTeam;
import com.teamwork.project.system.mapper.SysUserTeamMapper;
import com.teamwork.project.system.service.ISysUserTeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUserTeam)表服务实现类
 *
 * @author makejava
 * @since 2020-03-10 17:57:03
 */
@Service("sysUserTeamService")
public class SysUserTeamServiceImpl implements ISysUserTeamService {
    @Resource
    private SysUserTeamMapper userTeamMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserTeam queryById(Long id) {
        return this.userTeamMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUserTeam> queryAllByLimit(int offset, int limit) {
        return this.userTeamMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUserTeam 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SysUserTeam sysUserTeam) {
        List<Long> userIds = sysUserTeam.getUserIds();
        int i = 0;
        for (Long u : userIds) {
            SysUserTeam userTeam = new SysUserTeam();
            userTeam.setpUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            userTeam.setUserId(u);
            List<SysUserTeam> list = userTeamMapper.queryAll(userTeam);
            if (list.size() == 0) {
                userTeamMapper.insert(userTeam);
                i++;
            }
        }
        return i;
    }

//    /**
//     * 修改数据
//     *
//     * @param sysUserTeam 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public int update(SysUserTeam sysUserTeam) {
//        return userTeamMapper.update(sysUserTeam);
//    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userTeamMapper.deleteById(id) > 0;
    }

    @Override
    public int deleteByUserId(Long[] userIds) {
        int i = 0;
        for (Long u : userIds) {
            SysUserTeam userTeam = new SysUserTeam();
            userTeam.setpUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            userTeam.setUserId(u);
            userTeamMapper.delete(userTeam);
            i++;
        }
        return i;
    }

}