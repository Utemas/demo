package com.demo.demo.Mapper;

import com.demo.demo.po.Customer;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
// import org.apache.ibatis.annotations.Update;

@Mapper
public interface CustomerMapper {
    @Select("select * from v_customer where customer_identify = #{identify} and customer_id=#{id}")
    public Customer getCustomerById(@Param("identify") String customer_identify, @Param("id") String customer_id);

    @Select("select count(*) from v_customer where customer_identify = #{identify} and customer_id=#{id} and customer_password=#{password}")
    public int getCustomerToCheckLogin(@Param("identify") String customer_identify, @Param("id") String customer_id, @Param("password") String customer_password);

    @Insert("insert into project1_draft(draft_title,draft_article,customer) values(#{title},#{article},#{author})")
    public int addDraft(@Param("title") String title, @Param("article") String article, @Param("author") String author);
}