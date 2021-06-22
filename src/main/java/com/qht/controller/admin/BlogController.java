package com.qht.controller.admin;

import com.alibaba.fastjson.JSON;
import com.qht.entity.BlogPost;
import com.qht.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BlogController
 * @Author q
 * @Date 2021/6/20 22:30
 * @Version 1.0
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

    private BlogPostService blogPostService;

    @Autowired
    public void setBlogPostService(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveBlog(BlogPost blogPost, Model model){
        System.out.println(blogPost);
        blogPostService.insertBlog(blogPost);

        Map<String,String> map = new HashMap<>();
        map.put("msg", "true");
        String str = JSON.toJSONString(map);
        return str;
    }
}
