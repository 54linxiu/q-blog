package com.qht.dao;

import com.qht.entity.BlogPost;
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
     * @return
     */
    int queryCount();

    /**
     * 插入博客
     * @param blogPost
     * @return
     */
    int insertBlog(BlogPost blogPost);

    /**
     * 查询博客
     * @return
     */
    List<BlogPost> select();

    /**
     * 查询一条
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
