package com.qht.service;

import com.qht.entity.BlogPost;
import com.qht.entity.BlogSort;
import com.qht.entity.BlogTags;
import com.qht.entity.BlogTagsRelation;

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

    /**
     * 查询分类标签有没有
     * @param sortName
     * @return
     */
    int querySort(String sortName);

    /**
     * 插入分类标签
     * @param sortName
     * @return
     */
    int insertSort(String sortName);

    /**
     * 查询所有分类标签
     * @return
     */
    List<BlogSort> queryAllSort();

    /**
     * 查询所有标签
     * @return
     */
    List<BlogTags> queryAllTags();

    /**
     * 插入标签
     * @param tags
     * @return
     */
    int insertTags(String tags,String title);

    /**
     * 根据标签名查询
     * @param tags
     * @return
     */
    BlogTags queryTagsByName(String tags);

    /**
     * 统计分类数量
     * @return
     */
    int sortCount();

    /**
     * 统计标签数量
     * @return
     */
    int tagsCount();

    /**
     * 删除分类
     * @param sortName
     * @return
     */
    int deleteSort(String sortName);

    /**
     * 删除标签
     * @return
     */
    int deleteTags(String tagsId);


    List<BlogPost> querySort(int start, int size);
}
