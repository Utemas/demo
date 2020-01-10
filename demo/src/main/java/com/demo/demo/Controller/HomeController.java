package com.demo.demo.Controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import utill.Contant;

@Controller
public class HomeController {
    
    @RequestMapping("/information")
    public String helloHtml(HashMap<String, Object> map) {
        String identity_number = "学号:" + "st10000001";
        String id_number="111111111111111111";
        String name = "渡边太郎";
        map.put("name",name);
        map.put("age","23");
        map.put("identity_number",identity_number);
        map.put("id_number",id_number);
        map.put("programeName", Contant.ProgrameName);
        return "information";
    }

    @RequestMapping("/login")
    public String login(HashMap<String, Object> map) {

        String adminMail = "2562817565@qq.com";
        
        map.put("programeName", Contant.ProgrameName);
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