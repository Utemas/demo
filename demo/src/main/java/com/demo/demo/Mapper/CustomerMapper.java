package com.demo.demo.Mapper;

import java.util.List;

import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.Customer;
import com.demo.demo.po.Person;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
// import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Update;

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
    //
    @Select("select * from v_customer where customer_identify = #{identify} and customer_id=#{id}")
    public Customer getCustomerById(@Param("identify") String customer_identify, @Param("id") String customer_id);

    @Select("select count(*) from v_customer where customer_identify = #{identify} and customer_id=#{id} and customer_password=#{password}")
    public int getCustomerToCheckLogin(@Param("identify") String customer_identify, @Param("id") String customer_id, @Param("password") String customer_password);
    
    //等待修改
    @Update("update project1_customer where customer_id=#{id} and customer_identify=#{identify}")
    public int updateTelphone();


}