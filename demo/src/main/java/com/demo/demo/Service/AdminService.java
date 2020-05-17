
package com.demo.demo.Service;

import java.util.List;

import com.demo.demo.Mapper.AddMapper;
import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    AddMapper addMapper;


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

    public int addPunish(String punish_name, String punish_result, String punish_time, String punish_sc, String st_id)
    {
        int result = addMapper.addPunish(punish_name, punish_result, punish_time, punish_sc, st_id);

        return result;
    }
}