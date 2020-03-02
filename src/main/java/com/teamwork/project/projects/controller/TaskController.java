package com.teamwork.project.projects.controller;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.framework.aspectj.lang.annotation.Log;
import com.teamwork.framework.aspectj.lang.enums.BusinessType;
import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.domain.Task;
import com.teamwork.project.projects.service.ProjectService;
import com.teamwork.project.projects.service.TaskService;
import com.teamwork.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

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
        List<Task> list = taskService.selectTaskListByTime(task);
        return getDataTable(list);
    }

    @GetMapping("/taskUsers")
    public TableDataInfo selectTaskUsers(@PathVariable("taskId") Long taskId) {
        List<SysUser> list = taskService.selectTaskUsers(taskId);
        return getDataTable(list);
    }

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
     * 修改项目
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
     * 删除项目
     */
    @PreAuthorize("@ss.hasPermi('system:task:remove')")
    @Log(title = "任务管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskId}")
    public Result remove(@PathVariable("taskId") Long taskId)
    {
        return toAjax(taskService.deleteByPrimaryKey(taskId));
    }
}
