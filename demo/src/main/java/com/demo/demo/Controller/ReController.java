package com.demo.demo.Controller;

import java.util.List;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.ContextInfo;
import com.demo.demo.po.Customer;
import com.demo.demo.po.Loginer;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
 public class ReController {
    
    @Autowired
    CustomerMapper customerMapper;

    @RequestMapping("/updateContextInfo")
    public ContextInfo changeContextInfo(String ji_guan,String customer_tel, String customer_email, String customer_youzheng, String customer_start_station, String customer_end_station) {
        Loginer loginer = (Loginer) SecurityUtils.getSubject().getPrincipal();

        Customer customer = customerMapper.getCustomerByStid(loginer.getSt_id());

        ContextInfo cinfo= new ContextInfo();
        cinfo.setCustomer_jiguan(ji_guan);
        cinfo.setCustomer_tel(customer_tel);
        cinfo.setCustomer_email(customer_email);
        cinfo.setCustomer_youzheng(customer_youzheng);
        cinfo.setCustomer_start_station(customer_start_station);
        cinfo.setCustomer_end_station(customer_end_station);
        //更新籍贯
        int result = customerMapper.updateContextInfo(ji_guan,customer.getId_number());
        //更新电话
        result = customerMapper.updateTel(customer_tel,customer.getId_number());
        //更新电子邮箱
        result = customerMapper.updateEmail(customer_email,customer.getId_number());
        //更新邮政编码
        result = customerMapper.updateYouBian(customer_youzheng,customer.getId_number());
        //更新起始站
        result = customerMapper.updateStartStation(customer_start_station, customer.getId_number());

        result = customerMapper.updateEndStation(customer_end_station, customer.getId_number());
        return cinfo;
    }


    @RequestMapping("/changeYear")
    public List<ClassInfo> changeYear(String year) {
        Loginer student = (Loginer) SecurityUtils.getSubject().getPrincipal();
        List<ClassInfo> clist = customerMapper.getClassInformation(student.getSt_id(), year);
        return clist;
    } 

    @RequestMapping("/selectContextInfo")
    public ContextInfo seleContextInfo(){
        Loginer Loginer = (Loginer) SecurityUtils.getSubject().getPrincipal();
        
        Customer customer = customerMapper.getCustomerByStid(Loginer.getSt_id());

        ContextInfo contextInfo = customerMapper.selectContextInfo(customer.getId_number());
        return contextInfo;
    }

    @RequestMapping("/saveTrouble")
    public String saveTrouble(String title,String text){
        Loginer customer = (Loginer) SecurityUtils.getSubject().getPrincipal();
        String message = "反馈失败，请重新提交"; 
        int result = 0;
        if(customer == null){
            result = customerMapper.addTrouble(title, text, "出现登录问题者");    
        }else{
            result = customerMapper.addTrouble(title, text, customer.getSt_id());    
        }
        
        if(result == 1){
            message = "反馈成功，反馈信息请等待管理员进行处理...";
        }
        return message;
    }
    
 }