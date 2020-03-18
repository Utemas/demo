package com.demo.demo.Mapper;


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




    @Select("select * from v_customer where customer_identify = #{identify} and customer_id=#{id}")
    public Customer getCustomerById(@Param("identify") String customer_identify, @Param("id") String customer_id);

    @Select("select count(*) from v_customer where customer_identify = #{identify} and customer_id=#{id} and customer_password=#{password}")
    public int getCustomerToCheckLogin(@Param("identify") String customer_identify, @Param("id") String customer_id, @Param("password") String customer_password);
    
    //等待修改
    @Update("update project1_customer where customer_id=#{id} and customer_identify=#{identify}")
    public int updateTelphone();


}