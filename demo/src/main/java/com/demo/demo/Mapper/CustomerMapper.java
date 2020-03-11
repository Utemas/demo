package com.demo.demo.Mapper;

import java.util.List;

import com.demo.demo.po.Customer;
import com.demo.demo.po.Draft;

import org.apache.ibatis.annotations.Insert;
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

    //查找管理员信息
    @Select("select * from v_cu where customer_identify = 'ad'")
    public Customer getAdministrator();



    
    @Select("select * from v_customer where customer_identify = #{identify} and customer_id=#{id}")
    public Customer getCustomerById(@Param("identify") String customer_identify, @Param("id") String customer_id);

    @Select("select count(*) from v_customer where customer_identify = #{identify} and customer_id=#{id} and customer_password=#{password}")
    public int getCustomerToCheckLogin(@Param("identify") String customer_identify, @Param("id") String customer_id, @Param("password") String customer_password);

    @Insert("insert into project1_draft(draft_title,draft_article,customer) values(#{title},#{article},#{author})")
    public int addDraft(@Param("title") String title, @Param("article") String article, @Param("author") String author);

    //查询draft 的数量
    @Select("select count(*) from project1_draft where customer_id=#{id}")
    public int countDraft(@Param("id") String customer_id);

    //查询草稿信息
    @Select("select * from project1_draft where customer_id=#{id}")
    public List<Draft> findDraftByID(@Param("id") String customer_id);
    //等待修改
    @Update("update project1_customer where customer_id=#{id} and customer_identify=#{identify}")
    public int updateTelphone();


}