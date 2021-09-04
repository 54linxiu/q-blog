package com.qht.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qht.entity.BlogPost;
import com.qht.entity.BlogSort;
import com.qht.service.BlogPostService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 修改博客 重定向
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/edit")
    public String editBlog(String id, HttpSession session){
        BlogPost blog = blogPostService.queryOne(id);
        List<BlogSort> sortAll = blogPostService.queryAllSort();
        System.out.println(blog);
        System.out.println(sortAll);
        session.setAttribute("blog", blog);
        session.setAttribute("sortAll", sortAll);
        return "redirect:/admin/modify";
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @PostMapping("/del")
    @ResponseBody
    public String delBlog(String id){
        System.out.println(id);
        int status = blogPostService.deleteBlog(id);
        Map<String,String> map = new HashMap<>();
        String msg = "";
        if(status > 0){
            map.put("msg", "true");
            msg   = JSON.toJSONString(map);
        }else if(status == 0){
            map.put("msg", "false");
            msg   = JSON.toJSONString(map);
        }
        return msg;
    }

    /**
     * 保存博客
     * @param blogPost
     * @param model
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public String saveBlog(BlogPost blogPost, Model model){
        System.out.println(blogPost);
        String sortName = blogPost.getBlogSortName();
        //判断是否有分类标签 没有文字符号限制
        if(!"".equals(sortName) && sortName.length() > 0){
            int count = blogPostService.querySort(sortName);
            System.out.println(count);
            if(count == 0){
                blogPostService.insertSort(sortName);
            }
        }
        blogPostService.insertBlog(blogPost);

        Map<String,String> map = new HashMap<>();
        map.put("msg", "true");
        String str = JSON.toJSONString(map);
        return str;
    }

    /**
     * 博客修改
     * @param blogPost
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/modify")
    @ResponseBody
    public String modifyBlog(BlogPost blogPost, Model model,HttpSession session){
        System.out.println(blogPost);
        blogPostService.updateBlog(blogPost);

        Map<String,String> map = new HashMap<>();
        map.put("msg", "true");
        String str = JSON.toJSONString(map);
        //修改 需要清除之前的博客信息
        session.removeAttribute("blog");
        session.removeAttribute("sortAll");
        return str;
    }

    /**
     * 图片上传
     * @param request
     * @param response
     * @param attach
     * @return
     */
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
            jsonObject.put("url", "/img/"+attach.getOriginalFilename());
            System.out.println("/img/"+attach.getOriginalFilename());
        } catch (Exception e) {
            jsonObject.put("success", "0");
        }



        return jsonObject;
    }


}
