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
    private int publishingUsers;//发布账号
    private String blogTitle;
    private String blogContentHtml;
    private String blogContentMd;
    private int replies;//回复数

    public BlogPost() {
    }

    public BlogPost(int blogId, Date releaseTime, int publishingUsers, String blogTitle, String blogContentHtml, String blogContentMd, int replies) {
        this.blogId = blogId;
        this.releaseTime = releaseTime;
        this.publishingUsers = publishingUsers;
        this.blogTitle = blogTitle;
        this.blogContentHtml = blogContentHtml;
        this.blogContentMd = blogContentMd;
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

    public int getPublishingUsers() {
        return publishingUsers;
    }

    public void setPublishingUsers(int publishingUsers) {
        this.publishingUsers = publishingUsers;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContentHtml() {
        return blogContentHtml;
    }

    public void setBlogContentHtml(String blogContentHtml) {
        this.blogContentHtml = blogContentHtml;
    }

    public String getBlogContentMd() {
        return blogContentMd;
    }

    public void setBlogContentMd(String blogContentMd) {
        this.blogContentMd = blogContentMd;
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
                ", publishingUsers=" + publishingUsers +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContentHtml='" + blogContentHtml + '\'' +
                ", blogContentMd='" + blogContentMd + '\'' +
                ", replies=" + replies +
                '}';
    }
}