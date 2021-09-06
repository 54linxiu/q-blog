package com.qht.controller.admin;

import com.alibaba.fastjson.JSON;
import com.qht.entity.BlogSort;
import com.qht.entity.BlogTags;
import com.qht.entity.UserAdmin;
import com.qht.service.BlogPostService;
import com.qht.service.UserAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @Autowired
    public void setUserAdminService(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }
    @Autowired
    public void setBlogPostService(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
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
    public String toManage(Model model){
//        blogPostService.select()
        model.addAttribute("blogInfo", blogPostService.select());
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
    public String modify(){
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
