package com.qht.service;

import com.qht.entity.BlogPost;

import java.util.List;

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

    /**
     * 博客查询
     * @return
     */
    List<BlogPost> select();

    /**
     * 查询一条博客
     * @param id
     * @return
     */
    BlogPost queryOne(String id);

    /**
     * 更新博客
     * @param blogPost
     * @return
     */
    int updateBlog(BlogPost blogPost);

    /**
     * 删除博客
     * @param id
     * @return
     */
    int deleteBlog(String id);
}
