package com.demo.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Customer;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminController {

    @Autowired
    CustomerMapper customerMapper;


    @RequestMapping("/admin")
    public String adminLog(Map<String,Object> map){
        Customer adminCustomer = (Customer) SecurityUtils.getSubject().getPrincipal();

        //将所有学生的学籍信息List返回给前台
        List<Customer> clist = customerMapper.findAllStudent();
        map.put("clist", clist);
        //默认的底部信息:  
        map.put("footerinformation","高级搜索可以更精确地搜索到学生");
        return "administrator/admin";

    }

    @RequestMapping("/update")
    public String updateStudent(@RequestParam(value = "st_id", required = false, defaultValue = "1") String st_id,HashMap<String, Object> map){
        return "administrator/student";
    }


    @RequestMapping("/findStudentById")
    public String findStudentById(@RequestParam(value = "st_id", required = false, defaultValue = "1") String st_id,HashMap<String,Object> map){
        List<Customer> clist = customerMapper.finStudentById(st_id);
        map.put("clist",clist);
        map.put("footerinformation","高级搜索可以更精确地搜索到学生");
        return "administrator/admin";
    }
}