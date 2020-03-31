package com.demo.demo.Controller;

import java.util.HashMap;
import java.util.List;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Customer;
import com.demo.demo.po.Person;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import utill.Contant;
import utill.Utill;

@Controller
public class HomeController {

    @Autowired
    private CustomerMapper customerMapper;

    // 初始界面
    @RequestMapping("/login")
    public String login(HashMap<String, Object> map) {
        Customer administrator = customerMapper.getAdministrator();
        map.put("programeName", Contant.ProgrameName);
        map.put("adminMail", administrator.getCustomer_email());
        return "login";
    }

    // 登录验证
    @ResponseBody
    @RequestMapping("/checklogin")
    public String checklogin(HashMap<String, Object> map, String userCode, String password) {

        // 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userCode, password);
        // 3.执行登录方法
        try {
            subject.login(token);
            //
            map.put("name", "f");
            return "";
            // 登录成功
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            // 用户名不存在
            return "用户不存在";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return "用户密码不正确";
        }
    }

    // 注销登录状态
    @RequestMapping("/logout")
    public String logout(HashMap<String, Object> map) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        map.put("name", "请登录");
        return "login";
    }

    // 连接系统注册页面
    @RequestMapping("/introduction")
    public String introduction() {
        return "introduction";
    }

    // 调转到学生的总页面
    @RequestMapping("/student")
    public String student(HashMap<String, Object> map) {
        Customer customer = (Customer) SecurityUtils.getSubject().getPrincipal();
        Person personInformation = customerMapper.getStudentPeronInformation(customer.getId_number());
        // 查询学校管理人员的电子邮箱
        Customer administrator = customerMapper.getAdministrator();
        map.put("adminMail", administrator.getCustomer_email());
        //
        // 查询学生成绩数量
        int class_count = customerMapper.theNumberOfClass(customer.getSt_id());
        map.put("class_count",class_count);
        //
        map.put("student", customer);
        map.put("baseinformation", personInformation);
        String[] birthday = Utill.getBirth(personInformation.getId_number());
        String birth = birthday[0] + "-" + birthday[1] + "-" + birthday[2];
        map.put("birthday", birth);
        map.put("address", Utill.getAddress(personInformation));
        //
        //查询学生成绩信息
        List<ClassInfo> clist = customerMapper.getClassInformation(customer.getSt_id());
        map.put("clist",clist);
        //
        //计算这个同学的总学分是多少
        int xueFenTotal = 0;
        for(ClassInfo stu : clist){
            xueFenTotal += stu.getClass_xuefen();
        }
        map.put("xueFenTotal",xueFenTotal);
        //

        //计算优秀率85分以上占比
        String youxiulv;
        double youxiubi;

        int youxiuNumber = customerMapper.youxiuNumber(customer.getSt_id());
        if(class_count == 0){
            map.put("youxiulv","0");
            return "student.total";
        }
        youxiubi = (double)youxiuNumber/(double)class_count;
        youxiubi = youxiubi * 100;
        youxiulv =Double.toString(youxiubi) + "%";
        map.put("youxiulv",youxiulv);
        //
        System.out.println(xueFenTotal);
        return "student/total";
    }

    @RequestMapping("/trouble")
    public String writing(@RequestParam(value = "type", required = false, defaultValue = "1") int type,HashMap<String, Object> map){
        map.put("programeName",Contant.ProgrameName);
        if(type == 1){
            map.put("reason","登录问题");
            map.put("lastPage","登录");
        }else if(type == 2){
            map.put("reason","个人基本信息问题");
            map.put("lastPage","个人详情页");
        }
        return "/trouble";
    }

    //反馈提交方法
    @ResponseBody
    @RequestMapping("/saveTrouble")
    public String saveTrouble(String title,String text){
        Customer customer = (Customer) SecurityUtils.getSubject().getPrincipal();
        String message = "反馈失败，请重新提交"; 
        int result = 0;
        if(customer == null){
            result = customerMapper.addTrouble(title, text, "出现登录问题者");    
        }else{
            result = customerMapper.addTrouble(title, text, customer.getSt_id());    
        }
        
        if(result == 1){
            message = "反馈成功，反馈信息请等待管理员进行处理";
        }
        return message;
    }

}