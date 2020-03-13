package com.teamwork.project.projects.controller;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.framework.aspectj.lang.annotation.Log;
import com.teamwork.framework.aspectj.lang.enums.BusinessType;
import com.teamwork.framework.web.controller.BaseController;
import com.teamwork.framework.web.domain.Result;
import com.teamwork.framework.web.page.TableDataInfo;
import com.teamwork.project.projects.domain.Memo;
import com.teamwork.project.projects.domain.Project;
import com.teamwork.project.projects.domain.Task;
import com.teamwork.project.projects.service.MemoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MemoInfo)表控制层
 *
 * @author makejava
 * @since 2020-03-12 17:40:42
 */
@RestController
@RequestMapping("/memo")
public class MemoController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private MemoService memoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Memo selectOne(Long id) {
        return memoService.queryById(id);
    }

    @GetMapping("/list")
    public TableDataInfo list(Memo memo)
    {
        startPage();
        memo.setStatus("0");
        List<Memo> list = memoService.queryAll(memo);
        return getDataTable(list);
    }

    /**
     * 新增备忘录
     */
    @Log(title = "备忘录管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public Result add(@Validated @RequestBody Memo memo) {
        memo.setStatus("0");
        return toAjax(memoService.insert(memo));
    }

    /**
     * 修改备忘录
     */
    @Log(title = "备忘录管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody Memo memo)
    {
        return toAjax(memoService.update(memo));
    }

    /**
     * 删除备忘录
     */
    @Log(title = "备忘录管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{memoIds}")
    public Result remove(@PathVariable List<Long> memoIds)
    {
        return toAjax(memoService.delete(memoIds));
    }

}