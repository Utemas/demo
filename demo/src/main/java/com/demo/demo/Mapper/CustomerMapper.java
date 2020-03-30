package com.demo.demo.Mapper;

import java.util.List;

import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Customer;
import com.demo.demo.po.Person;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {

    //查找用户个人信息
    @Select("select * from v_cu where st_id=#{id} and customer_identify=#{identify}")
    public Customer getCustomerByStid(@Param("identify") String identfy, @Param("id") String st_id);

    @Select("select * from project1_person where id_number=#{id_number}")
    public Person getStudentPeronInformation(@Param("id_number") String id_number);
    
    //查找管理员信息
    @Select("select * from v_cu where customer_identify = 'ad'")
    public Customer getAdministrator();

    //查询学生成绩数量
    @Select("select count(*) from project1_class where st_id=#{st_id}")
    public int theNumberOfClass(@Param("st_id") String st_id);
    //

    //查询课程信息
    @Select("select * from project1_class where st_id=#{st_id}")
    public List<ClassInfo> getClassInformation(@Param("st_id") String st_id);
    
    //查询大于85分的成绩数量
    @Select("select count(*) from project1_class where class_score >= 80 and st_id = #{st_id}")
    public int youxiuNumber(@Param("st_id") String st_id);

    //插入问题反馈信息
    @Insert("insert into project1_trouble(trouble_title,trouble_text,st_id, trouble_stat) values(#{trouble_title},#{trouble_text},#{st_id},'yes') ")
    public int addTrouble(@Param("trouble_title") String title, @Param("trouble_text") String text, @Param("st_id") String st_id);

    //查询所有学籍学生
    @Select("select * from v_cu where customer_identify = 'st'")
    public List<Customer> findAllStudent();

    //按照学号查询学生
    @Select("select * from v_cu where customer_identify='st' and st_id=#{st_id}")
    public List<Customer> finStudentById(@Param("st_id") String st_id);
}