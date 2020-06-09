package com.demo.demo.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeleteMapper {

    @Delete("delete from project1_urgent where id=#{id}")
    public int deleteUrgent(@Param("id") int id);

    @Delete("delete from project1_st where st_id=#{st_id}")
    public int deleteStudent(@Param("st_id") String st_id);

    @Delete("delete from project1_class where st_id=#{st_id}")
    public int deleteClassByStId(@Param("st_id")String st_id);
    
    @Delete("delete from project1_award where st_id=#{st_id}")
    public int deleteAwardByStId(@Param("st_id") String st_id);
    
    @Delete("delete from project1_customer where st_id= #{st_id} ")
    public int deleteCustomerByStId(@Param("st_id") String st_id);

    @Delete("delete from project1_from where id_number=#{id_number}")
    public int deleteFromByStId(@Param("id_number") String id_number);
    
    @Delete("delete from project1_login where st_id=#{st_id}")
    public int deleteLoginByStId(@Param("st_id") String st_id);
    
    //有问题
    @Delete("delete from project1_person where id_number=#{id_number}")
    public int deletePersonByStId(@Param("id_number") String id_number);
    
    @Delete("delete from project1_punish where st_id=#{st_id}")
    public int deletePunishByStId(@Param("st_id") String st_id);

    @Delete("delete from project1_urgent where st_id=#{st_id}")
    public int deleteUrgentByStId(@Param("st_id") String st_id);

    @Delete("delete from project1_award where id=#{id}")
    public int deleteAwardById(@Param("id") int id);
}