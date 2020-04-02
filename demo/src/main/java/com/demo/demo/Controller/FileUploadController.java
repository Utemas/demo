package com.demo.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import utill.Contant;

import javax.servlet.http.HttpServletRequest;

import com.demo.demo.Service.ImportService;

import java.io.File;

@RestController
public class FileUploadController {

    @Autowired
    ImportService importService;

    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws Exception {
        //1.定义上传文件目录
        //上传文件的路径，可以在 Contat类 里进行修改
        String path = Contant.uploadPath;
        System.out.println(path);
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = file.getOriginalFilename();

        return "admin";
    }
}
