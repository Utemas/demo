package com.demo.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorPageController{
    

    @RequestMapping("/pageLost")
    public String PageLost(){
        return "404";
    }
}