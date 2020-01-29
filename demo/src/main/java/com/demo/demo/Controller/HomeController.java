package com.demo.demo.Controller;

import java.io.IOException;
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
    private Customer customer = new Customer();

    @RequestMapping("/information")
    public String helloHtml(HashMap<String, Object> map) {
        if (id == null || identify == null) {
            return "redirect:/login";
        }
        customer = customerMapper.getCustomerById(identify, id);
        String identity_number = "学号:" + customer.getCustomer_identify() + customer.getCustomer_id();
        map.put("name", customer.getName());
        map.put("age", Utill.caculateAge(customer.getId_number()));
        map.put("identity_number", identity_number);
        map.put("id_number", customer.getId_number());
        map.put("programeName", Contant.ProgrameName);
        map.put("address", Utill.getAddress(customer));
        return "information";
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
        if(Utill.nameIsNull(map, identify, id)){
            return "index";
        }
        customer = customerMapper.getCustomerById(identify,id);
        map.put("name",customer.getName());
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HashMap<String, Object> map){
        customer = null;
        map.put("name","请登录");
        id = null;
        identify = null;
        return "index";
    }

    @RequestMapping("/writing")
    public String writing(HashMap<String, Object> map){
        map.put("programeName", Contant.ProgrameName);
        if(Utill.nameIsNull(map, identify, id)){
            return "writing";
        }
        return "writing";
    }

}