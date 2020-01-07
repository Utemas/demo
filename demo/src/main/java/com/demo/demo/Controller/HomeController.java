package com.demo.demo.Controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "/index";
    }

    @RequestMapping("/login")
    public String login(HashMap<String, Object> map) {

        String adminMail = "2562817565@qq.com";
        
        map.put("programeName", "学生管理系统");
        map.put("adminMail",adminMail);
        return "login";
    }
    @ResponseBody
    @RequestMapping("/checklogin")
    public boolean checklogin(HashMap<String,Object> map,String userCode,String password){
        System.out.println(userCode + password);
        if(userCode.equals("st10000001")  && password.equals("1234567")){
            return true;
        }else{
            return false;
        }
    }
    
}