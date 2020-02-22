package com.teamwork.project.projects.controller;

import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.projects.domain.ProjectInfoLog;
import com.teamwork.project.projects.service.ProjectInfoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projectLog")
public class ProjectInfoLogController extends BaseController {

    @Autowired
    public ProjectInfoLogService projectInfoLogService;

    @GetMapping("getProjectLog")
    public TableDataInfo getProjectLog(@PathVariable("projectId") Long projectId) {
        List<ProjectInfoLog> list = projectInfoLogService.getProjectLog(projectId);
        return getDataTable(list);
    }
}
