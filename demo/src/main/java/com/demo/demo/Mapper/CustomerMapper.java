package com.demo.demo.Mapper;

import com.demo.demo.po.Customer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {
    @Select("select * from v_customer where customer_identify = #{identify} and customer_id=#{id}")
    public Customer getCustomerById(@Param("identify") String customer_identify, @Param("id") String customer_id);

    @Select("select count(*) from v_customer where customer_identify = #{identify} and customer_id=#{id} and customer_password=#{password}")
    public int getCustomerToCheckLogin(@Param("identify") String customer_identify, @Param("id") String customer_id, @Param("password") String customer_password);
}