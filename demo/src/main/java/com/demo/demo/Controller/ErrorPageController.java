package com.demo.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController{
    

    @RequestMapping("/pageLost")
    public String PageLost(){
        return "redirect:404";
    }
}