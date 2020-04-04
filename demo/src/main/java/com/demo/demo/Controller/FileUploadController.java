package com.demo.demo.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import utill.Contant;
import utill.ReadExcel;

import javax.servlet.http.HttpServletRequest;
import com.demo.demo.po.Person;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws Exception {
        //1.定义上传文件目录
        //上传文件的路径，可以在 Contat类 里进行修改
        ReadExcel re = new ReadExcel();
        String path = Contant.uploadPath;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        System.out.println(path+"\\"+fileName);
        try {
            file.transferTo(new File(folder, fileName));
            Person person = re.Read(path+"\\"+fileName);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "admin";
    }
}
