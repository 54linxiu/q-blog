package com.qht.controller.admin;

import com.alibaba.fastjson.JSON;
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

    @PostMapping("/save")
    @ResponseBody
    public String saveBlog(@RequestParam("blogContent") String blogContent, Model model){
        System.out.println(blogContent);
        Map<String,String> map = new HashMap<>();
        map.put("mess", "true");
        String s = JSON.toJSONString(map);
        return s;
    }
}
