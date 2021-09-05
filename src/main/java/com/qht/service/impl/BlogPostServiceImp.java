package com.qht.service.impl;

import com.qht.dao.BlogPostMapper;
import com.qht.entity.BlogPost;
import com.qht.entity.BlogSort;
import com.qht.entity.BlogTags;
import com.qht.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        //修改博客 需要修改标签 修改标签关系
        String tags =  blogPost.getBlogTagsName();
        String blogTagsName[] = tags.split("\\s+"); //标签分解
        BlogTags blogTags;
        //删除所有关系 嘿嘿
        blogPostMapper.delTags(blogPost.getBlogId());

        if(!"".equals(tags) && tags.length() > 0){

            for(String tag : blogTagsName){
                blogTags = queryTagsByName(tag);
                //没有就插入
                if(null == blogTags){
                    blogPostMapper.insertTags(tag);
                    blogTags = queryTagsByName(tag);
                }
                //插入关系表
                blogPostMapper.insertBlogTagsRelation(blogPost.getBlogId(),blogTags.getTagsId());
            }
        }

        return blogPostMapper.updateBlog(blogPost);
    }

    @Override
    public int deleteBlog(String id) {
        return blogPostMapper.deleteBlog(id);
    }

    @Override
    public int querySort(String sortName) {
        return blogPostMapper.querySort(sortName);
    }

    @Override
    public int insertSort(String sortName) {
        //判断是否有分类标签 没有文字符号限制
        if(!"".equals(sortName) && sortName.length() > 0){
            int count = querySort(sortName);
            if(count == 0){
                return blogPostMapper.insertSort(sortName);
            }
        }
        return 0;
    }

    @Override
    public List<BlogSort> queryAllSort() {
        return blogPostMapper.queryAllSort();
    }

    @Override
    public int insertTags(String tags,String title) {
        String blogTagsName[] = tags.split("\\s+"); //标签分解
        BlogTags blogTags;

        if(!"".equals(tags) && tags.length() > 0){

            for(String tag : blogTagsName){
                blogTags = queryTagsByName(tag);
                //没有就插入
                if(null == blogTags){
                     blogPostMapper.insertTags(tag);
                     blogTags = queryTagsByName(tag);
                }
                //根据标题获取id
                int blogId = blogPostMapper.queryBlogIDByTitle(title);
                //插入关系表
                blogPostMapper.insertBlogTagsRelation(blogId,blogTags.getTagsId());
            }
        }
        return 0;
    }

    @Override
    public BlogTags queryTagsByName(String tags) {
        return blogPostMapper.queryTagsByName(tags);
    }


}
