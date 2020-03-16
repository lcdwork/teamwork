package com.teamwork.project.projects.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.io.Serializable;

/**
 * (MemoInfo)实体类
 *
 * @author makejava
 * @since 2020-03-12 17:40:40
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Memo implements Serializable {
    private static final long serialVersionUID = -97852303138848321L;
    
    private Long memoId;
    /**
    * 创建人
    */
    private String createBy;
    /**
    * 创建者id
    */
    private String createUserId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
     * 备忘录日期
     */
    private Date memoTime;
    /**
    * 备忘录内容
    */
    private String memoContent;
    /**
    * 备忘录标题
    */
    private String memoTitle;

    private String status;

    public Long getMemoId() {
        return memoId;
    }

    public void setMemoId(Long memoId) {
        this.memoId = memoId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMemoTime() {
        return memoTime;
    }

    public void setMemoTime(Date memoTime) {
        this.memoTime = memoTime;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}