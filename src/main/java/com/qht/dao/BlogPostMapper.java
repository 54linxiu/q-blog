package com.qht.dao;

import com.qht.entity.BlogPost;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName BlogPostMapper.xml
 * @Author q
 * @Date 2021/6/19 20:20
 * @Version 1.0
 */
@Mapper
@Repository
public interface BlogPostMapper {
    int queryCount();

    int insertBlog(BlogPost blogPost);
}
