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

    @Insert("insert into project1_class(class_id,class_name,class_score,class_xuefen,class_teacher,st_id,class_year,class_xueqi) values(#{class_id},#{class_name},#{class_score},#{class_xuefen},#{class_teacher},#{st_id},#{class_year},#{class_xueqi})")
    public void addClassInfo(@Param("class_id") String class_id, @Param("class_name") String class_name, @Param("class_score") String class_score, @Param("class_xuefen") double class_xuefen, @Param("class_teacher") String class_teacher, @Param("st_id") String st_id, @Param("class_year") String class_year,@Param("class_xueqi") String class_xueqi);
    
    @Insert("insert into project1_award(award_name,award_time,award_condition,condition_css,st_id,award_type) values(#{award_name},#{award_time},#{award_condition},#{condition_css},#{st_id},#{award_type})")
    public int addAwardInfo(@Param("award_name") String award_name,@Param("award_time") String time,@Param("award_condition") String award_condition, @Param("condition_css") String award_css,@Param("st_id") String st_id,@Param("award_type")String award_type);


    @Insert("insert into project1_punish(punish_name,punish_result,punish_time,punish_sc,st_id) values(#{punish_name},#{punish_result},#{punish_time},#{punish_sc},#{st_id})")
    public int addPunish(@Param("punish_name") String punish_name, @Param("punish_result") String punish_result, @Param("punish_time") String punish_time, @Param("punish_sc") String punish_sc, @Param("st_id") String st_id);
}