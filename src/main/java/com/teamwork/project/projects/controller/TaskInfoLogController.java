package com.teamwork.project.projects.controller;

import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.projects.domain.ProjectInfoLog;
import com.teamwork.project.projects.domain.TaskInfoLog;
import com.teamwork.project.projects.service.ProjectInfoLogService;
import com.teamwork.project.projects.service.TaskInfoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taskLog")
public class TaskInfoLogController extends BaseController {

    @Autowired
    public TaskInfoLogService taskInfoLogService;

    @GetMapping("getTaskLog")
    public TableDataInfo getTaskLog(@PathVariable("taskId") Long taskId) {
        List<TaskInfoLog> list = taskInfoLogService.getTaskLog(taskId);
        return getDataTable(list);
    }
}
