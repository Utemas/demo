package com.demo.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import com.demo.demo.Mapper.AddMapper;
import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.Mapper.UpdateMapper;
import com.demo.demo.po.Award;
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
    
    @Autowired
    AddMapper addMapper;

    @Autowired
    UpdateMapper updateMapper;

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
        int result = updateMapper.updateContextInfo(ji_guan,customer.getId_number());
        //更新电话
        result = updateMapper.updateTel(customer_tel,customer.getId_number());
        //更新电子邮箱
        result = updateMapper.updateEmail(customer_email,customer.getId_number());
        //更新邮政编码
        result = updateMapper.updateYouBian(customer_youzheng,customer.getId_number());
        //更新起始站
        result = updateMapper.updateStartStation(customer_start_station, customer.getId_number());

        result = updateMapper.updateEndStation(customer_end_station, customer.getId_number());
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
            result = addMapper.addTrouble(title, text, "出现登录问题者");    
        }else{
            result = addMapper.addTrouble(title, text, customer.getSt_id());    
        }
        
        if(result == 1){
            message = "反馈成功，反馈信息请等待管理员进行处理...";
        }
        return message;
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(String newPassword,String surePassword) {
        String msg = "修改失败";
        Loginer loginer = (Loginer) SecurityUtils.getSubject().getPrincipal();
        if(newPassword.equals(surePassword)){
            updateMapper.updatePassword(newPassword, loginer.getSt_id());
            msg = "修改成功";
        }else{
            msg = "新密码与确认密码不一致";
        }
        return msg;
    }
    
 }