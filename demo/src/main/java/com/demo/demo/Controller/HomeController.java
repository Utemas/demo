package com.demo.demo.Controller;

import java.util.HashMap;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Customer;

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

    private String id = null;
    private String identify = null;

    @RequestMapping("/information")
    public String helloHtml(HashMap<String, Object> map) {
        Customer customer = customerMapper.getCustomerById("st","10000001");
        String identity_number = "学号:" + customer.getCustomer_identify()+customer.getCustomer_id();
        map.put("name",customer.getName());
        map.put("age",Utill.caculateAge(customer.getId_number()));
        map.put("identity_number",identity_number);
        map.put("id_number",customer.getId_number());
        map.put("programeName", Contant.ProgrameName);
        map.put("address", Utill.getAddress(customer));
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
        
        String customer_identify = userCode.substring(0,2);
        String customer_id = userCode.substring(2);
        int result = customerMapper.getCustomerToCheckLogin(customer_identify, customer_id, password);
        if(result == 1){
            id = customer_id;
            identify = customer_identify;
            return true;
        }
        return false;
    }

    @RequestMapping("/index")
    public String index(HashMap<String, Object> map){
        if(identify == null && id==null){
            map.put("name","请登录");
            return "index";
        }
        Customer customer = customerMapper.getCustomerById(identify,id);
        map.put("name",customer.getName());
        return "index";
    }

    @RequestMapping("/lost")
    public String lost(){
        return "redirect:/404";
    }

    @RequestMapping("/404")
    public String togo(){
        return "404";
    }
    
}