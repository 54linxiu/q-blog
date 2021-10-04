package com.qht.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qht.entity.BlogPost;
import com.qht.entity.BlogSort;
import com.qht.service.BlogPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @ClassName VueController
 * @Author q
 * @Date 2021/6/20 22:30
 * @Version 1.0
 */
@Controller
@RequestMapping("/blogs")
@Api(tags = "博客控制器")
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
    @ApiOperation("编辑接口")
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
    @ApiOperation("删除接口")
    public String delBlog(String id,String action){
        System.out.println(id + action);
        int status = 0;
        //删除博客
        if("blog".equals(action)){
            status = blogPostService.deleteBlog(id);
        }else if ("sort".equals(action)){ //删除分类
            status = blogPostService.deleteSort(id);
        }else if("tags".equals(action)){ //删除标签
            status = blogPostService.deleteTags(id);
        }
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
    @ApiOperation("提交博客保存接口")
    public String saveBlog(BlogPost blogPost, Model model){
        System.out.println(blogPost);
        String sortName = blogPost.getBlogSortName();
        String blogTagsName = blogPost.getBlogTagsName();

        //插入分类标签
        blogPostService.insertSort(sortName);
        blogPostService.insertBlog(blogPost);
        //插入标签
        blogPostService.insertTags(blogTagsName,blogPost.getBlogTitle());


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
    @ApiOperation("修改博客接口")
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
    @ApiOperation("保存图片接口")
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
