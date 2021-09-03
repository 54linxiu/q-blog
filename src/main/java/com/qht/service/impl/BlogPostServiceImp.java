package com.qht.service.impl;

import com.qht.dao.BlogPostMapper;
import com.qht.entity.BlogPost;
import com.qht.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BlogPostServiceImp
 * @Author q
 * @Date 2021/6/19 20:30
 * @Version 1.0
 */
@Service
public class BlogPostServiceImp implements BlogPostService {


    private BlogPostMapper blogPostMapper;

    @Autowired
    public void setBlogPostMapper(BlogPostMapper blogPostMapper) {
        this.blogPostMapper = blogPostMapper;
    }

    @Override
    public int queryCount() {
        return blogPostMapper.queryCount();
    }

    @Override
    public int insertBlog(BlogPost blogPost) {
        return blogPostMapper.insertBlog(blogPost);
    }

    @Override
    public List<BlogPost> select() {
        return blogPostMapper.select();
    }

    @Override
    public BlogPost queryOne(String id) {
        return blogPostMapper.queryOne(id);
    }

    @Override
    public int updateBlog(BlogPost blogPost) {
        return blogPostMapper.updateBlog(blogPost);
    }

    @Override
    public int deleteBlog(String id) {
        return blogPostMapper.deleteBlog(id);
    }


}
