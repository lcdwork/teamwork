package com.teamwork.project.projects.service.impl;

import com.teamwork.project.projects.domain.ProjectInfoLog;
import com.teamwork.project.projects.mapper.ProjectInfoLogMapper;
import com.teamwork.project.projects.service.ProjectInfoLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ProjectInfoLog)表服务实现类
 *
 * @author makejava
 * @since 2020-02-21 20:25:44
 */
@Service("projectInfoLogService")
public class ProjectInfoLogServiceImpl implements ProjectInfoLogService {
    @Resource
    private ProjectInfoLogMapper projectInfoLogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProjectInfoLog queryById(Long id) {
        return this.projectInfoLogMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ProjectInfoLog> queryAllByLimit(int offset, int limit) {
        return this.projectInfoLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param projectInfoLog 实例对象
     * @return 实例对象
     */
    @Override
    public ProjectInfoLog insert(ProjectInfoLog projectInfoLog) {
        this.projectInfoLogMapper.insert(projectInfoLog);
        return projectInfoLog;
    }

    /**
     * 修改数据
     *
     * @param projectInfoLog 实例对象
     * @return 实例对象
     */
    @Override
    public ProjectInfoLog update(ProjectInfoLog projectInfoLog) {
        this.projectInfoLogMapper.update(projectInfoLog);
        return this.queryById(projectInfoLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.projectInfoLogMapper.deleteById(id) > 0;
    }

    @Override
    public List<ProjectInfoLog> getProjectLog(Long projectId) {
        return this.projectInfoLogMapper.getProjectLog(projectId);
    }
}