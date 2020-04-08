package com.demo.demo.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddMapper {

    //插入个人身份证件上的信息
    @Insert("insert into  project1_person(id_number,name,addr_province,addr_city,addr_community,addr_street,addr_block,addr_unit,addr_floor,addr_room,sex,country,nation,card_type) values(#{id_number},#{name},#{addr_province},#{addr_city},#{addr_community},#{addr_street},#{addr_block},#{addr_unit},#{addr_floor},#{addr_room},#{sex},#{country},#{nation},#{card_type})")
    public int addPersonInfo(@Param("id_number")String id_number, @Param("name") String name, @Param("addr_province") String addr_province, @Param("addr_city") String addr_city,@Param("addr_community") String addr_community,@Param("addr_street") String addr_street,@Param("addr_block") String addr_block,@Param("addr_unit") String addr_unit,@Param("addr_floor") String addr_floor,@Param("addr_room") String addr_room,@Param("sex") String sex,@Param("country") String country,@Param("nation") String nation,@Param("card_type") String card_type);
    
    //提交问题
    @Insert("insert into project1_trouble(trouble_title,trouble_text,st_id, trouble_stat) values(#{trouble_title},#{trouble_text},#{st_id},'yes') ")
    public int addTrouble(@Param("trouble_title") String title, @Param("trouble_text") String text, @Param("st_id") String st_id);
}