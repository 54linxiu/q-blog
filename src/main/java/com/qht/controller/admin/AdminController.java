package com.qht.controller.admin;

import com.alibaba.fastjson.JSON;
import com.qht.service.BlogPostService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @Autowired
    BlogPostService blogPostService;
    /**
     * 转发 编辑博客页面
     * @return
     */
    @GetMapping("/edit")
    public String edit(){
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
    public String login(@RequestParam("account") String username, String password, Model model){
        //获取一个用户
        System.out.println(username);
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登陆数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Map<String,Object> map = new HashMap<>();
        try {
            subject.login(token);
            map.put("flag", 1);

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
