package com.demo.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.Mapper.DeleteMapper;
import com.demo.demo.Mapper.UpdateMapper;
import com.demo.demo.Service.AdminService;
import com.demo.demo.po.Award;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Link;
import com.demo.demo.po.Loginer;
import com.demo.demo.po.Person;
import com.demo.demo.po.Static;
import com.demo.demo.po.Student;
import com.demo.demo.po.Trouble;

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

    @Autowired
    AdminService adminService;

    @Autowired
    UpdateMapper updateMapper;

    @Autowired
    DeleteMapper deleteMapper;

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

    @RequestMapping("/look")
    public String lookStudentPage(@RequestParam(value = "st_id", required = false, defaultValue = "1") String st_id,HashMap<String, Object> map){
        //获取学生的学生信息
        Student student = adminService.getStudentById(st_id);
        Link customer = customerMapper.getCustomerByStid(st_id);
        Person person = customerMapper.getStudentPeronInformation(customer.getId_number());
        //获取学生的课程信息
        List<ClassInfo> clist = adminService.getStudentClassById(st_id);


        map.put("clist",clist);
        map.put("student",student);
        map.put("person",person);
        return "administrator/student";
    }

    @RequestMapping("/delete")
    public String deleteStudent(@RequestParam(value = "st_id", required = false, defaultValue = "1") String st_id,HashMap<String, Object> map){
        adminService.deleteStudent(st_id);
        return "redirect:/admin";
    }



    @RequestMapping("/findStudentById")
    public String findStudentById(String st_id, HashMap<String,Object> map){
        List<Student> slist = customerMapper.finStudentById(st_id);
        map.put("slist",slist);
        map.put("footerinformation","高级搜索可以更精确地搜索到学生");
        return "administrator/admin";
    }
    
    @RequestMapping("/classinfoOpretion")
    public String classinfoOpretion(HashMap<String,Object> map){
        List<ClassInfo> clist = customerMapper.findAllClassInfo();
        map.put("clist",clist);
        return "administrator/classInfo";
    }

    @RequestMapping("/awardinfo")
    public String awardInfo(HashMap<String,Object> map) {
        List<Award> awlist = customerMapper.getAllAward();
        map.put("awlist", awlist);
        return "administrator/awardInfo";
    }


    @RequestMapping("/judgeAward")
    public String punishtextInfo(@RequestParam(value = "id", required = false, defaultValue = "0") int id, HashMap<String,Object> map){
        Award award = customerMapper.getAwardById(id);
        if(award.getAward_condition().equals("未审阅")){
            int result = updateMapper.updateAwardStatus("正在审阅", "#DAA520", id);
            award.setAward_condition("正在审阅");
            award.setCondition_css("#DAA520");
        }
        
        map.put("award",award);
        return "administrator/judgeAward";
    }

    @RequestMapping("/deleteClassInfo")
    public String name(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        int result = customerMapper.deleteClassByClassId(id);
        if(result == 0){
            return "redirect:/classinfoOpretion";
        }
        return "redirect:/classinfoOpretion";
    }

    @RequestMapping("/awardOk")
    public String awardOk(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        updateMapper.updateAwardStatus("已通过审阅", "green", id);
        return "redirect:/awardinfo";
    }

    @RequestMapping("/awardWrong")
    public String awardWrong(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        updateMapper.updateAwardStatus("未通过审阅", "red", id);
        return "redirect:/awardinfo";
    }

    @ResponseBody
    @RequestMapping("/addPunish")
    public String addPunish(String punish_name, String punish_result, String punish_time, String punish_sc, String st_id) {
        int result = adminService.addPunish(punish_name, punish_result, punish_time, punish_sc, st_id);
        if(result == 0){
            return "添加失败";
        }

        return "添加成功";
        
    }

    @ResponseBody
    @RequestMapping("/accountDefault")
    public String account_password(String st_id, String account_password){
        int result = 0;

        Loginer loginer = (Loginer) SecurityUtils.getSubject().getPrincipal();
        if(!loginer.getPassword().equals(account_password)){
            return "管理员密码错误";
        }

        Student st = customerMapper.findStudentById(st_id);
        
        if(st == null){
            return "该学生不存在";
        }

        Link cus = customerMapper.getCustomerByStid(st_id);

        String idNumber =cus.getId_number();


        String newPassword = idNumber.substring(12);

        

        result = updateMapper.updatePassword(newPassword,st_id);
        
        if(result == 0){
            return "恢复默认密码失败";
        }
        return "恢复默认密码成功";
    }

    @RequestMapping("/statistics")
    public String statistics(HashMap<String, Object> map) {

        List<Static> slist = customerMapper.findstaticInfo();
        for(Static s : slist){
            double count = customerMapper.countSt();
            double percent = ((1.0 * s.getCountNumber()) / count) * 100;
            String result = Double.toString(percent) + "%";
            s.setPercent(result);
        }
        map.put("slist",slist);
        return "administrator/statistics";
    }

    @ResponseBody
    @RequestMapping("/sP")
    public List<Static> statisticsP() {
        List<Static> slist = customerMapper.findstaticPInfo();
        for(Static s : slist){
            double count = customerMapper.countPerson();
            double percent = ((1.0 * s.getCountNumber()) / count) * 100;
            String result = Double.toString(percent) + "%";
        }
        return slist;
    }

    @ResponseBody
    @RequestMapping("/updateSinfo")
    public String updateSinfo(String new_st_xueYuan, String new_st_zhuanye, String new_st_nianji, String new_st_class, String new_leave_to, String new_status, String st_id){
        int result = 0;
        if(new_status.equals("正常")){
            result = updateMapper.updteSinfo(new_st_xueYuan, new_st_zhuanye, new_st_nianji, new_st_class, new_leave_to, new_status, "green",st_id);
        }else if(new_status.equals("转入")){
            result = updateMapper.updteSinfo(new_st_xueYuan, new_st_zhuanye, new_st_nianji, new_st_class, new_leave_to, new_status, "#984B4B",st_id);
        }else if(new_status.equals("休学")){
            result = updateMapper.updteSinfo(new_st_xueYuan, new_st_zhuanye, new_st_nianji, new_st_class, new_leave_to, new_status, "#737300",st_id);
        }else if(new_status.equals( "转出") || new_status.equals("退学")){
            result = updateMapper.updteSinfo(new_st_xueYuan, new_st_zhuanye, new_st_nianji, new_st_class, new_leave_to, new_status, "red",st_id);
        }
        if(result == 1){
            return "修改成功";
        }
        return "修改失败";
    }
    
}