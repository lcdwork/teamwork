package com.teamwork.project.system.controller;

import com.teamwork.common.constant.Constants;
import com.teamwork.common.utils.ServletUtils;
import com.teamwork.framework.security.LoginUser;
import com.teamwork.framework.security.service.SysLoginService;
import com.teamwork.framework.security.service.SysPermissionService;
import com.teamwork.framework.security.service.TokenService;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.project.system.domain.SysMenu;
import com.teamwork.project.system.domain.SysUser;
import com.teamwork.project.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param uuid 唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public Result login(String username, String password, String uuid)
    {
        Result result = Result.success();
        // 生成令牌
        String token = loginService.login(username, password, uuid);
        result.put(Constants.TOKEN, token);
        return result;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public Result getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Result result = Result.success();
        result.put("user", user);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return result;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public Result getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return Result.success(menuService.buildMenus(menus));
    }
}
