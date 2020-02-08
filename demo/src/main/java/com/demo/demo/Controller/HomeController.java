package com.demo.demo.Controller;

import java.io.IOException;
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
import utill.Utill;

@Controller
public class HomeController {

    @Autowired
    private CustomerMapper customerMapper;
    private Customer customer = new Customer();

    @RequestMapping("/information")
    public String helloHtml(HashMap<String, Object> map) {
        String identity_number = "学号:" + customer.getCustomer_identify() + customer.getCustomer_id();
        map.put("name", customer.getName());
        map.put("age", Utill.caculateAge(customer.getId_number()));
        map.put("identity_number", identity_number);
        map.put("id_number", customer.getId_number());
        map.put("programeName", Contant.ProgrameName);
        map.put("address", Utill.getAddress(customer));
        return "/customer/information";
    }

    @RequestMapping("/login")
    public String login(HashMap<String, Object> map) throws IOException {
        String adminMail = "2562817565@qq.com";
        map.put("programeName", Contant.ProgrameName);
        map.put("adminMail",adminMail);
        return "login";
    }
    @ResponseBody
    @RequestMapping("/checklogin")
    public String checklogin(HashMap<String,Object> map,String userCode,String password){
        
        // 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userCode, password);
        // 3.执行登录方法
        try {
            subject.login(token);
            customer = (Customer) SecurityUtils.getSubject().getPrincipal();
            map.put("name",customer.getName());
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

    @RequestMapping("/index")
    public String index(HashMap<String, Object> map){
        map.put("programeName", Contant.ProgrameName);
        
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HashMap<String, Object> map){
        customer = null;
        map.put("name","请登录");
        return "index";
    }
    @RequestMapping("/writing")
    public String writing(HashMap<String, Object> map){
        map.put("programeName", Contant.ProgrameName);
        return "/customer/writing";
    }

    @ResponseBody
    @RequestMapping("/draft")
    public boolean draft(String article,String title,String author){
        int result = 0;
        result = customerMapper.addDraft(title, article, author);
        if(result == 1){
            return true;
        }
        return false;
    }

    @RequestMapping("/introduction")
    public String introduction(){
        return "introduction";
    }

    


}