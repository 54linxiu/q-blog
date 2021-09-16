package com.qht.dao;

import com.qht.entity.BlogPost;
import com.qht.entity.BlogSort;
import com.qht.entity.BlogTags;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName BlogPostMapper.xml
 * @Author q
 * @Date 2021/6/19 20:20
 * @Version 1.0
 */
@Mapper
@Repository
public interface BlogPostMapper {
    /**
     * 查询博文总数
     *
     * @return
     */
    int queryCount();

    /**
     * 插入博客
     *
     * @param blogPost
     * @return
     */
    int insertBlog(BlogPost blogPost);

    /**
     * 查询博客
     *
     * @return
     */
    List<BlogPost> select();

    /**
     * 根据Id查询一条
     *
     * @return
     */
    BlogPost queryOne(String id);

    /**
     * 根据标题查询id
     * @return
     */
    int queryBlogIDByTitle(String title);

    /**
     * 更新博客
     *
     * @param blogPost
     * @return
     */
    int updateBlog(BlogPost blogPost);

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    int deleteBlog(String id);

    /**
     * 查询标签有没有
     *
     * @param sortName
     * @return
     */
    int querySort(String sortName);

    /**
     * 插入分类标签
     *
     * @param sortName
     * @return
     */
    int insertSort(String sortName);

    /**
     * 查询所有分类标签
     *
     * @return
     */
    List<BlogSort> queryAllSort();

    /**
     * 查询所有标签
     *
     * @return
     */
    List<BlogTags> queryAllTags();

    /**
     * 插入标签
     * @param tags
     * @return
     */
    int insertTags(String tags);

    /**
     * 根据标签名查询
     * @param tags
     * @return
     */
    BlogTags queryTagsByName(String tags);

    /**
     * 将博客id 和 标签 id 关系对应
     * @param blogId
     * @param tagId
     * @return
     */
    int insertBlogTagsRelation(int blogId,int tagId);


    /**
     * 删除指定博客ID的所有关系
     * @param blogId
     * @return
     */
    int delTagsRelationship(int blogId);

    /**
     * 分类数量
     * @return
     */
    int sortCount();

    /**
     * 标签数量
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
    int deleteTags(String blogId);

    /**
     * 查询博客与标签之前关系数量
     * @param tagsId
     * @return
     */
    int queryTagsRelationCount(String tagsId);

    /**
     * 查询 此分类的博客数量
     * @param sort
     * @return
     */
    int queryBlogCountBySort(String sort);

    List<BlogPost> queryBlogPage(int start, int size);
}
