package com.teamwork.project.team.controller;

import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.team.domain.TaskInfoLog;
import com.teamwork.project.team.service.TaskInfoLogService;
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

    @GetMapping("/getTaskLog/{taskId}")
    public TableDataInfo getTaskLog(@PathVariable("taskId") Long taskId) {
        List<TaskInfoLog> list = taskInfoLogService.getTaskLog(taskId);
        return getDataTable(list);
    }
}
