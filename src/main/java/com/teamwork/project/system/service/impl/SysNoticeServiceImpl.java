package com.teamwork.project.system.service.impl;

import com.teamwork.common.utils.SecurityUtils;
import com.teamwork.project.team.domain.SysUserNotice;
import com.teamwork.project.team.mapper.SysUserNoticeMapper;
import com.teamwork.project.system.domain.SysNotice;
import com.teamwork.project.system.mapper.SysNoticeMapper;
import com.teamwork.project.system.service.ISysNoticeService;
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
        notice.setNoticeType((short) 2);
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
                s.setStatus((short) 0);
                userNoticeMapper.insert(s);
            }
            SysUserNotice un = new SysUserNotice();
            un.setNoticeId(notice.getNoticeId());
            un.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            un.setReadStatus((short) 1);
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
        if (userIds.size() > 0) {
            if (!(userIds.containsAll(list) && list.containsAll(userIds))) {
                insertUserNotice(notice);
            }
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
     * @param notice 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int remove(SysNotice notice)
    {
        int i = 0;
        List<Long> noticeIds = new ArrayList<>();
        if (notice.getNoticeIds() != null && notice.getNoticeIds().size() > 0) {
            noticeIds = notice.getNoticeIds();
        } else {
            SysUserNotice un = new SysUserNotice();
            un.setUserId(notice.getUserId());
            un.setStatus(notice.getReadStatus());
            List<SysUserNotice> list = userNoticeMapper.queryAll(un);
            for (SysUserNotice sun : list) {
                noticeIds.add(sun.getNoticeId());
            }
        }
        if (noticeIds.size() > 0) {
            i = noticeMapper.deleteNoticeByIds(noticeIds);
            userNoticeMapper.deleteByNoticeIds(noticeIds);
        }
        return i;
    }

    @Override
    public List<SysNotice> selectNoticeByUserId(SysNotice notice) {
//        notice.setStatus((short) 0);
        List<SysNotice> list = noticeMapper.selectNoticeByUserId(notice);
        return list;
    }
}
