package com.demo.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController{
    

    @RequestMapping("/lostpage")
    public String PageLost(){
        return "404";
    }

    @RequestMapping("/NoAuth")
    public String NoAuth(){
        return "401";
    }
}