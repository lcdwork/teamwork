package com.teamwork.project.team.service.impl;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.framework.web.domain.GanttTree;
import com.teamwork.framework.web.domain.TreeSelect;
import com.teamwork.project.team.domain.*;
import com.teamwork.project.team.mapper.*;
import com.teamwork.project.system.domain.SysNotice;
import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.mapper.SysNoticeMapper;
import com.teamwork.project.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.teamwork.project.team.service.ProjectService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ProjectInfoLogMapper projectInfoLogMapper;

    @Resource
    private SysUserProjectMapper sysUserProjectMapper;

    @Resource
    private SysUserNoticeMapper sysUserNoticeMapper;

    @Resource
    private SysNoticeMapper sysNoticeMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private SysUserMapper userMapper;

    @Override
    public List<Project> selectProjectList(Project project)
    {
        List<Project> list = projectMapper.selectProjectList(project);
        list.forEach(p -> {
            List<SysUser> userList = userMapper.getListByProjectId(p);
            p.setUserList(userList);
        });
        return list;
    }

    @Override
    public List<Project> selectProjectRepeat(Project project) {
        return projectMapper.selectProjectRepeat(project);
    }

    @Override
    public int deleteByPrimaryKey(Project project) {
        sysUserProjectMapper.deleteByProjectId(project.getProjectId());
        return projectMapper.deleteByPrimaryKey(project.getProjectId());
    }

    @Override
    public int insert(Project record) {
        record.setCreateUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        record.setCreateBy(SecurityUtils.getUsername());
        record.setCreateTime(new Date());
        int i = projectMapper.insert(record);
        projectInfoLogMapper.insert(insertProjectInfoLog(record, 1));
//        sysUserProjectMapper.deleteByProjectId(record.getProjectId());
        if (record.getUserList() != null && record.getUserList().size() > 0) {
            userNoticwList(record);
            List<SysUserProject> list = userProjectList(record);
            sysUserProjectMapper.insertList(list);
        }
        return i;
    }

    @Override
    public int insertSelective(Project record) {
        return projectMapper.insertSelective(record);
    }

    @Override
    public Project selectByPrimaryKey(Long projectId) {
        return projectMapper.selectByPrimaryKey(projectId);
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        record.setUpdateBy(SecurityUtils.getUsername());
        record.setUpdateTime(new Date());
        int i = projectMapper.updateByPrimaryKeySelective(record);
        projectInfoLogMapper.insert(insertProjectInfoLog(record, 2));
        if (record.getStatus() == 2) {
            projectInfoLogMapper.insert(insertProjectInfoLog(record, 3));
        }
        if (record.getUserList() != null && record.getUserList().size() > 0) {
            List<SysUser> newUserList = record.getUserList();
            List<Long> newUserIds = newUserList.stream().map(m -> m.getUserId()).collect(Collectors.toList());
            SysUserProject sysUserProject = new SysUserProject();
            sysUserProject.setProjectId(record.getProjectId());
            List<SysUserProject> s = sysUserProjectMapper.queryAll(sysUserProject);
            List<Long> userIds = s.stream().map(m -> m.getUserId()).collect(Collectors.toList());
            // 如果项目人员发生变化，重新生成项目人员关系，以及消息通知
            if (!(newUserIds.containsAll(userIds) && userIds.containsAll(newUserIds))) {
                userNoticwList(record);
                sysUserProjectMapper.deleteByProjectId(record.getProjectId());
                List<SysUserProject> list = userProjectList(record);
                sysUserProjectMapper.insertList(list);
            }
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        return projectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysUser> selectProjectUsers(Long projectId) {
        return projectMapper.selectProjectUsers(projectId);
    }

    @Override
    public List<TreeSelect> buildProjectTreeSelect(List<Project> projects) {
        List<Project> projectTrees = buildProjectTree(projects);
        return projectTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<GanttTree> buildProjectGanttTreeSelect(List<Project> projects) {
        List<Project> projectTrees = buildProjectGanttTree(projects);
        return projectTrees.stream().map(GanttTree::new).collect(Collectors.toList());
    }

    public ProjectInfoLog insertProjectInfoLog(Project project, int status) {
        ProjectInfoLog t = new ProjectInfoLog();
        t.setProjectId(project.getProjectId());
        t.setOperateTime(new Date());
        t.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        t.setStatus((byte) status);
        if (status == 1) {
            t.setContent(SecurityUtils.getLoginUser().getUser().getNickName() + "创建了" + project.getProjectName() + "项目");
        }
        if (status == 2) {
            t.setContent(SecurityUtils.getLoginUser().getUser().getNickName() + "编辑了" + project.getProjectName() + "项目");
        }
        if (status == 3) {
            t.setContent(project.getProjectName() + "项目完成");
        }
        return t;
    }

    public List userProjectList(Project project) {
        List<SysUserProject> list = new ArrayList<>();
        List<SysUser> users = project.getUserList();
        SysUser userSelf = userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
        users.add(userSelf);
        users.forEach(u -> {
            SysUserProject sysUserProject = new SysUserProject();
            sysUserProject.setProjectId(project.getProjectId());
            sysUserProject.setUserId(u.getUserId());
            list.add(sysUserProject);
        });
        List<SysUserProject> returnList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(SysUserProject::getUserId))),ArrayList::new));
        return returnList;
    }

    // 生成系统消息并分发给每个人
    public void userNoticwList(Project project) {
        List<SysUser> users = project.getUserList();
        users.forEach(u -> {
            SysNotice sysNotice = new SysNotice();
            sysNotice.setNoticeTitle("项目邀请通知");
            sysNotice.setNoticeContent(SecurityUtils.getUsername() + "邀请" + u.getUserName() + "加入" + project.getProjectName() + "项目");
            sysNotice.setNoticeType((short) 1);
//            sysNotice.setStatus((short) 0);
            sysNotice.setReadStatus((short) 0);
            sysNotice.setCreateBy(SecurityUtils.getUsername());
            sysNotice.setCreateTime(new Date());
            sysNoticeMapper.insertNotice(sysNotice);
            SysUserNotice sysUserNotice = new SysUserNotice();
            sysUserNotice.setUserId(u.getUserId());
            sysUserNotice.setNoticeId(sysNotice.getNoticeId());
            sysUserNotice.setStatus((short) 0);
            sysUserNoticeMapper.insert(sysUserNotice);
            SysUserNotice sysUserNotice2 = new SysUserNotice();
            sysUserNotice2.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            sysUserNotice2.setNoticeId(sysNotice.getNoticeId());
            sysUserNotice2.setStatus((short) 1);
            sysUserNoticeMapper.insert(sysUserNotice2);
        });
    }

    /**
     * 构建前端所需要树结构
     *
     * @param projects 项目列表
     * @return 树结构列表
     */
    @Override
    public List<Project> buildProjectTree(List<Project> projects) {
        List<Project> returnList = new ArrayList<Project>();
        for (Project project : projects)
        {
            Task task = new Task();
            task.setProjectId(project.getProjectId());
            List<Task> tasks = taskMapper.selectTaskList(task);
            project.setTaskList(tasks);
//            if (tasks != null && tasks.size() > 0) {
//                for (Task t : tasks) {
//                    Project p = new Project();
//                    p.setTaskId(t.getTaskId());
//                    p.setTaskName(t.getTaskName());
//                    returnList.add(p);
//                }
//            }
            returnList.add(project);
        }

        if (returnList.isEmpty())
        {
            returnList = projects;
        }
        return returnList;
    }

    @Override
    public List<Project> buildProjectGanttTree(List<Project> projects) {
        List<Project> returnList = new ArrayList<Project>();
        for (Project project : projects)
        {
            Task task = new Task();
            task.setProjectId(project.getProjectId());
            List<Task> tasks = taskMapper.selectTaskList(task);
            project.setTaskList(tasks);
            returnList.add(project);
        }

        if (returnList.isEmpty())
        {
            returnList = projects;
        }
        return returnList;
    }

    @Override
    public List<Project> selectProjectListByUser(Project project) {
        List<Project> list = projectMapper.selectProjectListByUser(project);
        list.forEach(p -> {
            List<SysUser> userList = userMapper.getListByProjectId(p);
            p.setUserList(userList);
        });
        return list;
    }

}
