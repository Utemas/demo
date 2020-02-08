package com.demo.demo.Controller;

import java.util.Map;
import java.util.UUID;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Customer;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;

import utill.SendEmailUtil;

@Controller
public class AdminController {

    @Autowired
    CustomerMapper customerMapper;
    SendEmailUtil sendEmailUtil;

    Customer administrator = new Customer(); 

    @RequestMapping("/admin")
    public String a(Map<String,Object> map){
        map.put("project", "demo");
        map.put("author", "yimcarson");
        map.put("code", "text");
        return "/administrator/admin";
    }

    @RequestMapping("/ae")
    public String admin() {
        String to = "2562817585@qq.com";
        String title="adadfafd";
        sendEmailUtil.send(to, title, UUID.randomUUID().toString().toUpperCase());
        return "/administrator/admin";
    }
}