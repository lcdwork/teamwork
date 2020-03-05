package com.teamwork.project.system.service.impl;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.projects.domain.SysUserNotice;
import com.teamwork.project.projects.mapper.SysUserNoticeMapper;
import com.teamwork.project.system.domain.SysNotice;
import com.teamwork.project.system.mapper.SysNoticeMapper;
import com.teamwork.project.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公告 服务层实现
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Resource
    private SysNoticeMapper noticeMapper;

    @Resource
    private SysUserNoticeMapper userNoticeMapper;

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        notice.setNoticeType("2");
        notice.setCreateByUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        int i = noticeMapper.insertNotice(notice);
        insertUserNotice(notice);
        return i;
    }

    private void insertUserNotice(SysNotice notice) {
        if (notice.getUserList() != null && notice.getUserList().size() > 0) {
            List<Long> userIds = notice.getUserList();
            userIds.add(SecurityUtils.getLoginUser().getUser().getUserId());
            userIds = userIds.stream().distinct().collect(Collectors.toList());
            for (Long userId : userIds) {
                SysUserNotice s = new SysUserNotice();
                s.setNoticeId(notice.getNoticeId());
                s.setUserId(userId);
                s.setStatus((byte) 0);
                userNoticeMapper.insert(s);
            }
            SysUserNotice un = new SysUserNotice();
            un.setNoticeId(notice.getNoticeId());
            un.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            un.setStatus((byte) 1);
            userNoticeMapper.updateRead(un);
        }
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        userNoticeMapper.deleteByNoticeId(notice.getNoticeId());
        int i = noticeMapper.updateNotice(notice);
        List<Long> userIds = notice.getUserList();
        SysUserNotice userNotice = new SysUserNotice();
        userNotice.setNoticeId(notice.getNoticeId());
        List<SysUserNotice> userNoticeList = userNoticeMapper.queryAll(userNotice);
        List<Long> list = new ArrayList<>();
        userNoticeList.forEach(u -> {
            list.add(u.getUserId());
        });
        if (!(userIds.containsAll(list) && list.containsAll(userIds))) {
            insertUserNotice(notice);
        }
        return i;
    }

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        userNoticeMapper.deleteByNoticeId(noticeId);
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(List<Long> noticeIds)
    {
        userNoticeMapper.deleteByNoticeIds(noticeIds);
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }

    @Override
    public List<SysNotice> selectNoticeByUserId(SysNotice notice) {
        notice.setStatus("0");
        List<SysNotice> list = noticeMapper.selectNoticeByUserId(notice);
        return list;
    }
}
