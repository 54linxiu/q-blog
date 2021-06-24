package com.qht.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qht.entity.BlogPost;
import com.qht.service.BlogPostService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    @PostMapping("/saveImg")
    @ResponseBody
    public JSONObject saveImg(HttpServletRequest request, HttpServletResponse response, @RequestParam("editormd-image-file") MultipartFile attach){
        JSONObject jsonObject=new JSONObject();
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String rootPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\";

            System.out.println("editormd上传图片："+rootPath);

            /**
             * 文件路径不存在则需要创建文件路径
             */
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            // 最终文件名
            File realFile = new File(rootPath + File.separator + attach.getOriginalFilename());
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);


            // 下面response返回的json格式是editor.md所限制的，规范输出就OK
            //此处不要写 字符串的"1"，只是写为数字不要带引号*/
            jsonObject.put("success", 1 );
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", "http://localhost:10515/img/"+attach.getOriginalFilename());
            System.out.println("/img/"+attach.getOriginalFilename());
        } catch (Exception e) {
            jsonObject.put("success", "0");
        }



        return jsonObject;
    }
}
