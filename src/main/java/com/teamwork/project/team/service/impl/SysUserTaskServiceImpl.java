package com.teamwork.project.team.service.impl;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.team.domain.TaskInfoLog;
import com.teamwork.project.team.mapper.TaskInfoLogMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.teamwork.project.team.domain.SysUserTask;
import com.teamwork.project.team.mapper.SysUserTaskMapper;
import com.teamwork.project.team.service.SysUserTaskService;

import java.util.Date;

@Service
public class SysUserTaskServiceImpl implements SysUserTaskService{

    @Resource
    private SysUserTaskMapper sysUserTaskMapper;

    @Resource
    private TaskInfoLogMapper taskInfoLogMapper;

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
        TaskInfoLog t = new TaskInfoLog();
        t.setTaskId(record.getTaskId());
        t.setOperateTime(new Date());
        t.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        if (record.getStatus() == 2) {
            t.setStatus((byte) 4);
            t.setContent(SecurityUtils.getUsername() + "领取了" + record.getTaskName() + "任务");
        }
        if (record.getStatus() == 3) {
            t.setStatus((byte) 5);
            t.setContent(SecurityUtils.getUsername() + "提交了" + record.getTaskName() + "任务");
        }
        taskInfoLogMapper.insert(t);
        return sysUserTaskMapper.updateStatus(record);
    }

}
