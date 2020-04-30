
package com.demo.demo.Service;

import java.util.List;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    CustomerMapper customerMapper;


    public Student getStudentById(String st_id) {

        Student student = customerMapper.findStudentById(st_id);
        
        return student;
    }

    public List<ClassInfo> getStudentClassById(String st_id){

        List<ClassInfo> clist = customerMapper.getALLClass(st_id);

        return clist;
    }


    public int deleteStudentClassById(int id){

        customerMapper.deleteClassByClassId(id);

        return id;
    }


}