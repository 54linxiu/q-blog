package com.qht.controller.client;

import com.alibaba.fastjson.JSON;
import com.qht.service.BlogPostService;
import com.qht.service.VueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName VueController
 * @Author q
 * @Date 2021/9/27 10:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/blog")
public class VueController {

    private VueService vueService;

    private BlogPostService blogPostService;

    @Autowired
    public void setBlogPostService(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @Autowired
    public void setBlogPostMapper(VueService vueService) {
        this.vueService = vueService;
    }

    @GetMapping("test")
    public String testVue(){
        return "test";
    }

    @PostMapping("/index")
    public String getBlogSimpleInformation(){
        return JSON.toJSONString(vueService.selectIndex());
    }

    @GetMapping("/p/{blogId}")
    public String getBlogSimple(@PathVariable String blogId){
        return JSON.toJSONString(this.blogPostService.queryOne(blogId));
    }
}
