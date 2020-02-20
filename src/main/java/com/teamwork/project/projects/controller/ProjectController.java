package com.teamwork.project.projects.controller;

import com.teamwork.common.constant.UserConstants;
import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.framework.aspectj.lang.annotation.Log;
import com.teamwork.framework.aspectj.lang.enums.BusinessType;
import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.service.ProjectService;
import com.teamwork.project.system.domain.SysMenu;
import com.teamwork.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: LCD
 * @date Create in: 18:42 2020/2/19
 * @description：
 * @modify:
 * @see: com.teamwork.project.projects.controller
 */
@RestController
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;


    @GetMapping("/list")
    public TableDataInfo list(Project project)
    {
        startPage();
        List<Project> list = projectService.selectProjectList(project);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:project:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping("/addProject")
    public Result add(@Validated @RequestBody Project project) {
        return toAjax(projectService.insert(project));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody Project project)
    {
        project.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(projectService.updateByPrimaryKeySelective(project));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@ss.hasPermi('system:project:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectId}")
    public Result remove(@PathVariable("projectId") Long projectId)
    {
        return toAjax(projectService.deleteByPrimaryKey(projectId));
    }

}
