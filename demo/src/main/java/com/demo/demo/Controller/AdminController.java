package com.demo.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.Service.AdminService;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Student;
import com.demo.demo.po.Trouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdminController {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    AdminService adminService;

    @RequestMapping("/admin")
    public String adminLog(Map<String,Object> map){

        //将所有学生的学籍信息List返回给前台
        List<Student> slist = customerMapper.findAllStudent();
        map.put("slist", slist);
        //默认的底部信息:  
        map.put("footerinformation","高级搜索可以更精确地搜索到学生");
        
        List<Trouble> tlist = customerMapper.getTrouble();
        
        map.put("tlist",tlist);
        return "administrator/admin";

    }

    @RequestMapping("/update")
    public String updateStudentPage(@RequestParam(value = "st_id", required = false, defaultValue = "1") String st_id,HashMap<String, Object> map){
        //获取学生的学生信息
        Student student = adminService.getStudentById(st_id);

        //获取学生的课程信息
        List<ClassInfo> clist = adminService.getStudentClassById(st_id);


        map.put("clist",clist);
        map.put("student",student);
        return "administrator/student";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String deleteStudent(String st_id,HashMap<String, Object> map){
        
        String message = "删除失败";
        List<Student> customer = customerMapper.finStudentById(st_id);
        int result = customerMapper.deleteCustomerById(customer.get(0).getSt_id());
        result = customerMapper.deleteClassById(st_id);
        result = customerMapper.deletePersonById(customer.get(0).getSt_id());
        result = customerMapper.deleteStudentById(st_id);
        result = customerMapper.delteeTrouble(st_id);
        if(result != 0 ){
            System.out.println("删除成功!");
        }
        return message;
    }



    @RequestMapping("/findStudentById")
    public String findStudentById(String st_id,HashMap<String,Object> map){
        List<Student> slist = customerMapper.finStudentById(st_id);
        map.put("slist",slist);
        map.put("footerinformation","高级搜索可以更精确地搜索到学生");
        return "administrator/admin";
    }
    
}