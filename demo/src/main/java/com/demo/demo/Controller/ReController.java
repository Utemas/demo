package com.demo.demo.Controller;

import java.util.List;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.ContextInfo;
import com.demo.demo.po.Customer;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/student")
 public class ReController {
    
    @Autowired
    CustomerMapper customerMapper;

    @RequestMapping("/updateContextInfo")
    public ContextInfo changeContextInfo(String ji_guan) {
        Customer student = (Customer) SecurityUtils.getSubject().getPrincipal();
        ContextInfo cinfo= new ContextInfo();
        cinfo.setCustomer_jiguan(ji_guan);
        int result = customerMapper.updateContextInfo(ji_guan,student.getId_number());
        return cinfo;
    }


    @RequestMapping("/changeYear")
    public List<ClassInfo> changeYear(String year) {
        Customer student = (Customer) SecurityUtils.getSubject().getPrincipal();
        List<ClassInfo> clist = customerMapper.getClassInformation(student.getSt_id(), year);
        return clist;
    } 

    @RequestMapping("/selectContextInfo")
    public ContextInfo seleContextInfo(){
        Customer student = (Customer) SecurityUtils.getSubject().getPrincipal();
        ContextInfo contextInfo = customerMapper.selectContextInfo(student.getId_number());
        return contextInfo;
    }

    
 }