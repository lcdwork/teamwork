package com.teamwork.project.team.controller;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.framework.aspectj.lang.annotation.Log;
import com.teamwork.framework.aspectj.lang.enums.BusinessType;
import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.domain.GanttTreeList;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.team.domain.Task;
import com.teamwork.project.team.domain.TaskList;
import com.teamwork.project.team.service.TaskService;
import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ISysUserService userService;

    @GetMapping("/list")
    public TableDataInfo list(Task task)
    {
        startPage();
        List<Task> list = taskService.selectTaskList(task);
        return getDataTable(list);
    }

    @GetMapping("/listByUser")
    public TableDataInfo listByUser(Task task)
    {
        startPage();
        List<Task> list = taskService.selectTaskListByUser(task);
        return getDataTable(list);
    }

    @GetMapping("/listByTime")
    public TableDataInfo listByTime(Task task)
    {
        List<TaskList> list = taskService.selectTaskListByTime(task);
        return getDataTable(list);
    }

    @GetMapping("/taskUsers")
    public TableDataInfo selectTaskUsers(@PathVariable("taskId") Long taskId) {
        List<SysUser> list = taskService.selectTaskUsers(taskId);
        return getDataTable(list);
    }

    /**
     * 获取甘特图
     */
//    @PreAuthorize("@ss.hasPermi('system:user:ganttTree')")
    @GetMapping("/ganttTree")
    public Result userGanttTree(Task task) {
        List<SysUser> list = new ArrayList<>();
        SysUser u = userService.selectUserById(task.getTaskUserId());
        list.add(u);
        GanttTreeList tree = userService.buildUserGanttTreeSelect(list, task);
        return Result.success(tree);
    }

    /**
     * 新增任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:add')")
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public Result add(@Validated @RequestBody Task task) {
        List<Task> list = taskService.selectTaskRepeat(task);
        if (list.size() > 0) {
            return Result.error("任务已经存在，新增失败！");
        }
        task.setStatus((byte) 1);
        return toAjax(taskService.insert(task));
    }

    /**
     * 修改任务
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody Task task)
    {
        List<Task> list = taskService.selectTaskRepeat(task);
        if (list.size() > 0) {
            return Result.error("任务名重复，修改失败！");
        }
        task.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(taskService.updateByPrimaryKeySelective(task));
    }

    /**
     * 删除任务
     */
    @PreAuthorize("@ss.hasPermi('system:task:remove')")
    @Log(title = "任务管理", businessType = BusinessType.DELETE)
    @DeleteMapping
    public Result remove(@Validated @RequestBody Task task)
    {
        return toAjax(taskService.deleteByPrimaryKey(task));
    }

    /**
     * 备忘录转待办任务
     */
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/convertMemo")
    public Result convertMemo(@Validated @RequestBody Task task) {
        List<Task> list = taskService.selectConvertRepeat(task);
        if (list.size() > 0) {
            return Result.error("任务已经存在，新增失败！");
        }
        return toAjax(taskService.convertMemo(task));
    }
}
