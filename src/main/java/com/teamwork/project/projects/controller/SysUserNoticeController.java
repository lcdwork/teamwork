package com.teamwork.project.projects.controller;

import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.project.projects.domain.SysUserNotice;
import com.teamwork.project.projects.service.SysUserNoticeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysUserNotice)表控制层
 *
 * @author makejava
 * @since 2020-03-05 20:56:55
 */
@RestController
@RequestMapping("/userNotice")
public class SysUserNoticeController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserNoticeService userNoticeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public SysUserNotice selectOne(Long id) {
        return this.userNoticeService.queryById(id);
    }

//    @PutMapping
//    public Result updateRead(@Validated @RequestBody SysUserNotice userNotice) {
//        return toAjax(userNoticeService.updateRead(userNotice));
//    }

}