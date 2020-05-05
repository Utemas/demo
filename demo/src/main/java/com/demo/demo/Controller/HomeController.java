package com.demo.demo.Controller;

import java.util.HashMap;
import java.util.List;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Award;
import com.demo.demo.po.Customer;
import com.demo.demo.po.Enter;
import com.demo.demo.po.Loginer;
import com.demo.demo.po.Person;
import com.demo.demo.po.Punish;
import com.demo.demo.po.Student;
import com.demo.demo.po.Urgent;

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

    // 连接系统介绍页面
    @RequestMapping("/introduction")
    public String introduction() {
        return "introduction";
    }

    // 调转到学生的总页面
    @RequestMapping("/student")
    public String student(HashMap<String, Object> map) {
        //获取登录对象
        Loginer loginer = (Loginer) SecurityUtils.getSubject().getPrincipal();
        //获取中间连接类
        Customer customer = customerMapper.getCustomerByStid(loginer.getSt_id());
        //获取学生学籍信息
        Student student = customerMapper.getStudentInfo(loginer.getSt_id());

        Person person = customerMapper.getStudentPeronInformation(customer.getId_number());
        map.put("person",person);

        // 查询学生成绩数量
        int class_count = customerMapper.theNumberOfClass(loginer.getSt_id());
        map.put("class_count",class_count);
        map.put("english_name", customer.getCustomer_english_name());
        //
        map.put("student", student);

        //生成生日
        String[] birthday = Utill.getBirth(customer.getId_number());
        String birth = birthday[0] + "-" + birthday[1] + "-" + birthday[2];
        map.put("birthday", birth);
        map.put("address", Utill.getAddress(person));
        

        //获取学生的在学校的年份
        student.setSt_entertime(Utill.formatTime(student.getSt_entertime()));
        int enterYear = Integer.parseInt(student.getSt_entertime().substring(0, 4));
        List<String> enterYears = Utill.yearsInSchool(enterYear);
        map.put("enterYears",enterYears);
        //
    
        //查询学生的紧急联系人
        List<Urgent> uList = customerMapper.getUrgents(customer.getSt_id());
        map.put("ulist", uList);
        //
        //计算这个同学的总学分是多少
        int xueFenTotal = customerMapper.getTotalXueFen(loginer.getSt_id());
        
        map.put("xueFenTotal",xueFenTotal);
        //

        //计算优秀率85分以上占比
        String youxiulv;
        double youxiubi;

        int youxiuNumber = customerMapper.youxiuNumber(customer.getSt_id());
        if(class_count == 0){
            map.put("youxiulv","0.0%");
            return "student/total";
        }
        youxiubi = (double)youxiuNumber/(double)class_count;
        youxiubi = youxiubi * 100;
        youxiulv =Double.toString(youxiubi) + "%";
        map.put("youxiulv",youxiulv);

        //计算学生不及格的科目数量
        String bujigelv;
        double bujigebi;

        int bujigeNumber = customerMapper.getBujigeNumber(customer.getSt_id());
        map.put("bujigeNumber", bujigeNumber);
        if(class_count == 0){
            map.put("bujuigelv","0-.0%");
            return "student/total";
        }
        bujigebi = (double)bujigeNumber/(double)class_count;
        bujigebi = bujigebi * 100;
        bujigelv = Double.toString(bujigebi) + "%";
        map.put("bujuigelv",bujigelv);

        //查询学生的入学信息
        Enter enterInfo = customerMapper.getEnterInfo(customer.getId_number());
        map.put("enter",enterInfo);

        //查询学生获奖情况的数量
        int awardCount = customerMapper.getAwardCount(customer.getSt_id());
        map.put("awardCount",awardCount);
        
        //查询学生获奖情况
        List<Award> awlist = customerMapper.getAward(customer.getSt_id());
        map.put("awlist",awlist);

        //查询学生惩罚情况的数量
        int punishCount = customerMapper.getPunishCount(customer.getSt_id());
        map.put("punishCount",punishCount);

        //查询学生惩罚情况
        List<Punish> pulist = customerMapper.getPunish(customer.getSt_id());
        map.put("pulist",pulist);



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

    @RequestMapping("/punishtextInfo")
    public String punishtextInfo(@RequestParam(value = "punish_id", required = false, defaultValue = "0") int punish_id, HashMap<String,Object> map){

        if(punish_id == 0){
            return "/500";
        }
        Punish punish = customerMapper.getPunishById(punish_id);
        Customer customer = customerMapper.getCustomerByStid(punish.getSt_id());
        Person person = customerMapper.getStudentPeronInformation(customer.getId_number());
        map.put("person",person);
        map.put("punish",punish);
        return "common/punishInfo";
    }

    @RequestMapping("/awardRefresh")
    public String ddd(HashMap<String,Object> map){
        Loginer loginer = (Loginer) SecurityUtils.getSubject().getPrincipal();
        List<Award> awlist = customerMapper.getAward(loginer.getSt_id());
        map.put("awlist",awlist);
        return "/student";
    }

    @RequestMapping("/tologin")
    public String tologin(HashMap<String,Object> map){
        String a = "不要回答！不要回答！不要回答！";
		Person person = new Person();
		person.setName("张三");
		map.put("firstMessage",a);
		map.put("person",person);
		return "tologin";
    }

    @RequestMapping("/pdfOutput")
    public String test() {
        
        return null;
    }
}