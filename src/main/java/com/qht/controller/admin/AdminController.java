package com.qht.controller.admin;

import com.alibaba.fastjson.JSON;
import com.qht.entity.*;
import com.qht.service.BlogPostService;
import com.qht.service.UserAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Author linxiu
 * @Date 2021/6/15 18:23
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    private UserAdminService userAdminService;
    private BlogPostService blogPostService;
    private BackstagePage<BlogPost> page;
    @Autowired
    public void setUserAdminService(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }
    @Autowired
    public void setBlogPostService(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }
    @Autowired
    public void setPage(BackstagePage<BlogPost> page) {
        this.page = page;
    }

    /**
     * 转发 标签管理页面
     * @return
     */
    @GetMapping("sort")
    public String toSort(Model model){
        List<BlogSort> blogSorts = blogPostService.queryAllSort();
        model.addAttribute("blogSorts",blogSorts);
        return "admin/sort";
    }

    @GetMapping("tags")
    public String toTags(Model model){
        List<BlogTags> blogTags = blogPostService.queryAllTags();
        model.addAttribute("blogTags",blogTags);
        return "admin/tags";
    }

    /**
     * 转发 管理博客页面
     * @return
     */
    @GetMapping("/manage")
    public String toManage(@RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,Model model,HttpSession session){
//        blogPostService.select()

        List<BlogPost> blogPosts = blogPostService.querySort((pageNum-1)*10, 10);

        page.setRows(blogPosts);
        page.setCurrentPage(pageNum);
        page.setTotalCount(blogPostService.queryCount());
        model.addAttribute("blogInfo", page);
        return "admin/administration";
    }

    /**
     * 转发 编辑博客页面
     * @return
     */
    @GetMapping("/edit")
    public String edit(HttpSession session){
        //进入博客管理页面 之前可能修改博客信息遗留 需要清除
        session.removeAttribute("blog");
        session.setAttribute("sortAll",blogPostService.queryAllSort());

        return "admin/edit";
    }

    @GetMapping("/modify")
    public String modify(HttpSession session){
        return "admin/edit";
    }

    /**
     * 转发 个人信息页面
     * @return
     */
    @GetMapping("/userProfile")
    public String userProfile(){
        return "admin/user";
    }

    /**
     * 返回首页
     * @return
     */
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("blogCount", blogPostService.queryCount());
        model.addAttribute("sortCount", blogPostService.sortCount());
        model.addAttribute("tagsCount", blogPostService.tagsCount());
        return "index";
    }

    /**
     * 转发登录页
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    /**
     * 修改用户信息
     * @param userAdmin
     * @return
     */
    @PostMapping("updateUser")
    @ResponseBody
    public String updateUser(UserAdmin userAdmin, HttpSession session){

        int status = userAdminService.update(userAdmin);

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

    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadPhoto(MultipartFile photo,HttpServletRequest request){
        Map<String, String> ret = new HashMap<String, String>();
        if (photo == null) {
            ret.put("type", "error");
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }

        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
            return ret;
        }
        //获取项目根目录加上图片目录 webapp/static/imgages/upload/
        String savePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\faces\\";
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        String filename = new Date().getTime() + "." + suffix;
        try {
            //将文件保存指定目录
            photo.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "上传图片成功！");
        ret.put("filepath", request.getSession().getServletContext().getContextPath() + "/img/faces/");
        ret.put("filename", filename);
        //获取session中的用户对象
        UserAdmin uid = (UserAdmin) request.getSession().getAttribute("uid");
        //修改数据库中的图片路径
        userAdminService.savaPhoto(filename,String.valueOf(uid.getUserId()));
        //更新图片路径
        request.getSession().setAttribute("uid", userAdminService.queryUser(uid.getUserAccount()));
        return ret;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam("account") String username, String password, Model model, HttpSession session){
        //获取一个用户
//        System.out.println(username);
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登陆数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Map<String,Object> map = new HashMap<>();
        try {
            subject.login(token);
            map.put("flag", 1);
            UserAdmin userAdmin = userAdminService.queryUser(username);
            session.setAttribute("uid", userAdmin);
        } catch (UnknownAccountException e) {//用户不存在
            map.put("flag", 0);
            map.put("msg","用户名错误");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){//密码不存在
            map.put("flag", 0);
            map.put("msg", "密码错误");
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    /**
     * 未授权 提示
     * @return
     */
    @RequestMapping("/noauto")
    @ResponseBody
    public String unauthorized() {
        return "未经授权，无法访问此页面";
    }

    /**
     * 注销 没有对接此功能
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        //获取一个用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();


        return "redirect:/admin/login";
    }
}
