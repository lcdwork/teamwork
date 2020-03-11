package com.teamwork.project.system.controller;


import com.teamwork.common.constant.UserConstants;
import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.framework.aspectj.lang.annotation.Log;
import com.teamwork.framework.aspectj.lang.enums.BusinessType;
import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.domain.SysUserTeam;
import com.teamwork.project.system.service.ISysUserTeamService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUserTeam)表控制层
 *
 * @author makejava
 * @since 2020-03-10 17:57:04
 */
@RestController
@RequestMapping("/system/userTeam")
public class SysUserTeamController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ISysUserTeamService userTeamService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUserTeam selectOne(Long id) {
        return this.userTeamService.queryById(id);
    }

    /**
     * 新增用户
     */
    @Log(title = "人员管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysUserTeam userTeam)
    {
        return toAjax(userTeamService.insert(userTeam));
    }

//    /**
//     * 修改用户
//     */
//    @Log(title = "人员管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public Result edit(@Validated @RequestBody SysUserTeam userTeam)
//    {
//        return toAjax(userTeamService.update(userTeam));
//    }

    /**
     * 删除用户
     */
    @Log(title = "人员管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public Result remove(@PathVariable Long[] userIds)
    {
        return toAjax(userTeamService.deleteByUserId(userIds));
    }

}