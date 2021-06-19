package com.qht.entity;

import java.util.Date;

/**
 * @ClassName BlogPost
 * @Author q
 * @Date 2021/6/19 13:41
 * @Version 1.0
 */
public class BlogPost {
    private int blogId;
    private Date releaseTime;
    private int publishing_users;//发布账号
    private String blogTitle;
    private String blogContent;
    private int replies;//回复数

    public BlogPost() {
    }

    public BlogPost(int blogId, Date releaseTime, int publishing_users, String blogTitle, String blogContent, int replies) {
        this.blogId = blogId;
        this.releaseTime = releaseTime;
        this.publishing_users = publishing_users;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.replies = replies;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getPublishing_users() {
        return publishing_users;
    }

    public void setPublishing_users(int publishing_users) {
        this.publishing_users = publishing_users;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "blogId=" + blogId +
                ", releaseTime=" + releaseTime +
                ", publishing_users=" + publishing_users +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", replies=" + replies +
                '}';
    }
}
