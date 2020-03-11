package com.teamwork.project.projects.controller;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.framework.aspectj.lang.annotation.Log;
import com.teamwork.framework.aspectj.lang.enums.BusinessType;
import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.domain.GanttTreeList;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.domain.Task;
import com.teamwork.project.projects.service.ProjectService;
import com.teamwork.project.projects.service.TaskService;
import com.teamwork.project.system.domain.SysDept;
import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    public TaskService taskService;

    @Autowired
    private ISysUserService userService;


    @GetMapping("/list")
    public TableDataInfo list(Project project)
    {
        startPage();
        List<Project> list = projectService.selectProjectList(project);
        return getDataTable(list);
    }

    @GetMapping("/listProjectByUser")
    public TableDataInfo selectProjectListByUser(Project project) {
        List<Project> list = projectService.selectProjectListByUser(project);
        return getDataTable(list);
    }

    /**
     * 获取项目任务下拉树列表
     */
    @GetMapping("/treeselect")
    public Result treeselect(Project project)
    {
        List<Project> projects = projectService.selectProjectListByUser(project);
        return Result.success(projectService.buildProjectTreeSelect(projects));
    }

    /**
     * 获取项目甘特图列表
     */
    @PostMapping("/ganttTree")
    public Result ganttTree(@Validated @RequestBody Project project)
    {
        List<Project> projects = projectService.selectProjectList(project);
        return Result.success(projectService.buildProjectGanttTreeSelect(projects));
    }

    /**
     * 获取人员甘特图
     */
//    @PreAuthorize("@ss.hasPermi('system:user:ganttTree')")
    @GetMapping("/userGanttTree")
    public Result userGanttTree(SysUser user) {
//        List<SysUser> list = userService.listUserByUserId(user);
        List<SysUser> list = new ArrayList<>();
        SysUser u = userService.selectUserById(user.getUserId());
        list.add(u);
        GanttTreeList tree = userService.buildUserGanttTreeSelect(list);
        return Result.success(tree);
    }

    @GetMapping("/projectUsers")
    public TableDataInfo selectProjectUsers(@PathVariable("projectId") Long projectId) {
        List<SysUser> list = projectService.selectProjectUsers(projectId);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:project:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public Result add(@Validated @RequestBody Project project) {
        List<Project> list = projectService.selectProjectRepeat(project);
        if (list.size() > 0) {
            return Result.error("项目已经存在，新增失败！");
        }
        project.setStatus((byte) 1);
        return toAjax(projectService.insert(project));
    }

    /**
     * 修改项目
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody Project project)
    {
        List<Project> list = projectService.selectProjectRepeat(project);
        if (list.size() > 0) {
            return Result.error("项目名重复，修改失败！");
        }
        project.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(projectService.updateByPrimaryKeySelective(project));
    }

    /**
     * 删除项目
     */
    @PreAuthorize("@ss.hasPermi('system:project:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
    @DeleteMapping
    public Result remove(@Validated @RequestBody Project project)
    {
        Task task = new Task();
        task.setProjectId(project.getProjectId());
        List<Task> list = taskService.selectTaskList(task);
        if (list.size() > 0) {
            return Result.error("此项目下有任务，无法删除！");
        }
        return toAjax(projectService.deleteByPrimaryKey(project));
    }

}
