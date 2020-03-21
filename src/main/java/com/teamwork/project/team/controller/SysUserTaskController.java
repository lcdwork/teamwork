package com.teamwork.project.team.controller;

import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.project.team.domain.SysUserTask;
import com.teamwork.project.team.service.SysUserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LCD
 * @date Create in: 22:33 2020/2/27
 * @description：
 * @modify:
 * @see: com.teamwork.project.projects.controller
 */
@RestController
@RequestMapping("/userTask")
public class SysUserTaskController extends BaseController {

    @Autowired
    private SysUserTaskService sysUserTaskService;

    @PutMapping("/updateByStatus")
    public Result edit(@Validated @RequestBody SysUserTask sysUserTask)
    {
        return toAjax(sysUserTaskService.updateStatus(sysUserTask));
    }
}
