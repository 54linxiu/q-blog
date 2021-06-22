package com.qht.service;

import com.qht.entity.BlogPost;

/**
 * @ClassName BlogPostService
 * @Author q
 * @Date 2021/6/19 19:13
 * @Version 1.0
 */
public interface BlogPostService {
    /**
     * 查询博客数量
     * @return
     */
    int queryCount();

    /**
     * 插入博客
     * @return
     */
    int insertBlog(BlogPost blogPost);
}
