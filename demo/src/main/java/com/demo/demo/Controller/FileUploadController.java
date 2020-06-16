package com.demo.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import utill.Contant;
import utill.ReadExcel;

import javax.servlet.http.HttpServletRequest;

import com.demo.demo.Mapper.AddMapper;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Enter;
import com.demo.demo.po.Person;
import com.demo.demo.po.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileUploadController {

    @Autowired
    AddMapper addMapper;

    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws Exception {
        List<Person> plist = new ArrayList<>();
        //1.定义上传文件目录
        //上传文件的路径，可以在 Contat类 里进行修改
        ReadExcel re = new ReadExcel();
        String path = Contant.uploadPath;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(folder, fileName));
             plist = re.Read(path+"\\"+fileName);

            for(Person person : plist){
                addMapper.addPersonInfo(person.getId_number(), person.getName(), person.getAddr_province(), person.getAddr_city(), person.getAddr_community(), person.getAddr_street(), person.getAddr_block(), person.getAddr_unit(), person.getAddr_floor(), person.getAddr_room(), person.getSex(), person.getCountry(), person.getNation(), person.getCard_type());
            }    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "导入成功";
    }

    @PostMapping("/uploadStudentInfo")
    public String uploadStudentInfo(MultipartFile file, HttpServletRequest request) throws Exception {
        List<Student> slist = new ArrayList<>();
        //1.定义上传文件目录
        //上传文件的路径，可以在 Contat类 里进行修改
        ReadExcel re = new ReadExcel();
        String path = Contant.uploadPath;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        try {
            
            file.transferTo(new File(folder, fileName));
            slist = re.ReadStInfo(path+"\\"+fileName);
            for(Student s : slist){
                System.out.println(s);
                switch (s.getSt_status()) {
                    case "正常":
                        s.setSt_status_stiker("green");
                        break;
                    case "转入":
                        s.setSt_status_stiker("yellow");
                        break;
                    default:
                        break;
                }
                int result = addMapper.addLogin(s.getSt_id(), s.getId_number().substring(12));
                result = addMapper.addStInfo(s.getId_number(), s.getSt_id(), s.getSt_xueYuan(), s.getSt_zhuanye(), s.getSt_nianji(), s.getSt_class(), s.getSt_type(), s.getSt_leave_date(), s.getSt_peiyangfangshi(), s.getSt_leave_to(), s.getSt_entertime(), s.getSt_xuejibiao_number(), s.getSt_status(), s.getSt_status_stiker());
                result = addMapper.addCustomer(s.getId_number(), s.getSt_id(), "中国共产主义共青团团员", "空", "空");
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "导入成功";
    }
    

    @PostMapping("/uploadScore")
    public String uploadScore(MultipartFile file, HttpServletRequest request) throws Exception{
        List<ClassInfo> clist = new ArrayList<>();
        ReadExcel re = new ReadExcel();
        String path = Contant.uploadPath;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(folder, fileName));
            clist = re.ReadScore(path+"\\"+fileName);

            for(ClassInfo classInfo : clist){
                addMapper.addClassInfo(classInfo.getClass_id(), classInfo.getClass_name(), classInfo.getClass_score(), classInfo.getClass_xuefen(), classInfo.getClass_teacher(), classInfo.getSt_id(), classInfo.getClass_year(), classInfo.getClass_xueqi());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "导入成功";
    }

    @PostMapping("/uploadXueJiInfo")
    public String uploadXueJiInfo(MultipartFile file, HttpServletRequest request) throws Exception{
        List<Enter> elist = new ArrayList<>();
        ReadExcel re = new ReadExcel();
        String path = Contant.uploadPath;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(folder, fileName));
            elist = re.ReadEnterInfo(path+"\\"+fileName);
            for(Enter enterInfo : elist){
                addMapper.addEnterInfo(enterInfo.getEnter_idnumber(), enterInfo.getEnter_speak(), enterInfo.getEnter_luquid(), enterInfo.getEnter_type(), enterInfo.getEnter_score(), enterInfo.getEnter_kaoqu(), enterInfo.getId_number(), enterInfo.getEnter_source(), enterInfo.getEnter_from());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "导入成功";
    }

    
}
