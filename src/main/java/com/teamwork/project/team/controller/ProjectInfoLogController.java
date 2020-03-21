package com.teamwork.project.team.controller;

import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.team.domain.ProjectInfoLog;
import com.teamwork.project.team.service.ProjectInfoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projectLog")
public class ProjectInfoLogController extends BaseController {

    @Autowired
    public ProjectInfoLogService projectInfoLogService;

    @GetMapping("/getProjectLog")
    public TableDataInfo list(ProjectInfoLog projectInfoLog)
    {
        startPage();
        List<ProjectInfoLog> list = projectInfoLogService.getProjectLog(projectInfoLog);
        return getDataTable(list);
    }
}
