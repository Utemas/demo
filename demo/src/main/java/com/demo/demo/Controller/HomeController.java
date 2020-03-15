package com.demo.demo.Controller;

import java.util.HashMap;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Customer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import utill.Contant;

@Controller
public class HomeController {

    @Autowired
    private CustomerMapper customerMapper;
    //private Customer customer = new Customer();

    //个人信息查询和显示
    
    //初始界面
    @RequestMapping("/login")
    public String login(HashMap<String, Object> map){
        Customer administrator = customerMapper.getAdministrator();
        map.put("programeName", Contant.ProgrameName);
        map.put("adminMail",administrator.getCustomer_email());
        return "login";
    }

    //登录验证
    @ResponseBody
    @RequestMapping("/checklogin")
    public String checklogin(HashMap<String,Object> map,String userCode,String password){
        Customer customer = new Customer();
        // 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userCode, password);
        // 3.执行登录方法
        try {
            subject.login(token);
            customer = (Customer) SecurityUtils.getSubject().getPrincipal();
            map.put("name","customer.getName()");
            return "";
            // 登录成功
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            // 用户名不存在
            return "用户不存在";
        } catch(IncorrectCredentialsException e){
            e.printStackTrace();
            return "用户密码不正确";
        }
    }


    //注销登录状态
    @RequestMapping("/logout")
    public String logout(HashMap<String, Object> map){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        map.put("name","请登录");
        return "index";
    }


    //连接系统注册页面
    @RequestMapping("/introduction")
    public String introduction(){
        return "introduction";
    }

    //调转到学生的总页面
    @RequestMapping("/student")
    public String student(){
        return "student/total";
    }


}