package com.demo.demo.Controller;

import java.util.Map;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {

    @Autowired
    CustomerMapper customerMapper;

    Customer administrator = new Customer(); 

    @RequestMapping("/admin")
    public String a(Map<String,Object> map){
        return "/administrator/admin";
    }

    //发送邮件操作
    @RequestMapping("/ae")
    public String mp(){
        return null;
    }
}