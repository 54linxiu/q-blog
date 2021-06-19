package com.qht.service.impl;

import com.qht.dao.BlogPostMapper;
import com.qht.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BlogPostServiceImp
 * @Author q
 * @Date 2021/6/19 20:30
 * @Version 1.0
 */
@Service
public class BlogPostServiceImp implements BlogPostService {

    @Autowired
    BlogPostMapper blogPostMapper;

    @Override
    public int queryCount() {
        return blogPostMapper.queryCount();
    }
}
