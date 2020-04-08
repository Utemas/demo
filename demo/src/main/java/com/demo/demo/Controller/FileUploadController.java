package com.demo.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import utill.Contant;
import utill.ReadExcel;

import javax.servlet.http.HttpServletRequest;

import com.demo.demo.Mapper.AddMapper;
import com.demo.demo.po.Person;

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
}
