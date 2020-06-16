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
    // @Insert("insert into project1_trouble(trouble_title,trouble_text,st_id, trouble_stat) values(#{trouble_title},#{trouble_text},#{st_id},'yes') ")
    // public int addTrouble(@Param("trouble_title") String title, @Param("trouble_text") String text, @Param("st_id") String st_id);

    @Insert("insert into project1_class(class_id,class_name,class_score,class_xuefen,class_teacher,st_id,class_year,class_xueqi) values(#{class_id},#{class_name},#{class_score},#{class_xuefen},#{class_teacher},#{st_id},#{class_year},#{class_xueqi})")
    public void addClassInfo(@Param("class_id") String class_id, @Param("class_name") String class_name, @Param("class_score") String class_score, @Param("class_xuefen") double class_xuefen, @Param("class_teacher") String class_teacher, @Param("st_id") String st_id, @Param("class_year") String class_year,@Param("class_xueqi") String class_xueqi);
    
    @Insert("insert into project1_award(award_name,award_time,award_condition,condition_css,st_id,award_type,picture_path) values(#{award_name},#{award_time},#{award_condition},#{condition_css},#{st_id},#{award_type},#{picture_path})")
    public int addAwardInfo(@Param("award_name") String award_name,@Param("award_time") String time,@Param("award_condition") String award_condition, @Param("condition_css") String award_css,@Param("st_id") String st_id,@Param("award_type")String award_type,@Param("picture_path") String picture_path);


    @Insert("insert into project1_punish(punish_name,punish_result,punish_time,punish_sc,st_id) values(#{punish_name},#{punish_result},#{punish_time},#{punish_sc},#{st_id})")
    public int addPunish(@Param("punish_name") String punish_name, @Param("punish_result") String punish_result, @Param("punish_time") String punish_time, @Param("punish_sc") String punish_sc, @Param("st_id") String st_id);

    @Insert("insert into project1_urgent(st_id,urgent_name,urgent_guanxi,urgent_tel) values (#{st_id},#{urgent_name},#{urgent_guanxi},#{urgent_tel})")
	public void addUgent(@Param("urgent_name") String urgent_name,@Param("urgent_guanxi") String urgent_context,@Param("urgent_tel") String urgent_tel,@Param("st_id") String st_id);

    @Insert("insert into project1_from(enter_idnumber,enter_speak,enter_luquid,enter_type,enter_score,enter_kaoqu,id_number,enter_source,enter_from) values(#{enter_idnumber},#{enter_speak},#{enter_luquid},#{enter_type},#{enter_score},#{enter_kaoqu},#{id_number},#{enter_source},#{enter_from})")
    public void addEnterInfo(@Param("enter_idnumber") String enter_idnumber,@Param("enter_speak") String enter_speak,@Param("enter_luquid") String enter_luquid,@Param("enter_type") String enter_type,@Param("enter_score") String enter_score,@Param("enter_kaoqu")String enter_kaoqu,@Param("id_number") String id_number,@Param("enter_source") String enter_source,@Param("enter_from") String enter_from);
    
    @Insert("insert into project1_login(st_id,password,login_identify) values(#{st_id},#{password},'st')")
    public int addLogin(@Param("st_id")String st_id, @Param("password")String password);

    @Insert("insert into project1_st(id_number,st_id,st_xueYuan,st_zhuanye,st_nianji,st_class,st_type,st_leave_date,st_peiyangfangshi,st_leave_to,st_entertime,st_xuejibiao_number,st_status,st_status_stiker) values(#{id_number},#{st_id},#{st_xueYuan},#{st_zhuanye},#{st_nianji},#{st_class},#{st_type},#{st_leave_date},#{st_peiyangfangshi},#{st_leave_to},#{st_entertime},#{st_xuejibiao_number},#{st_status},#{st_status_stiker})")
    public int addStInfo(@Param("id_number")String id_number,@Param("st_id")String st_id,@Param("st_xueYuan")String st_xueYuan,@Param("st_zhuanye")String st_zhuanye,@Param("st_nianji")String st_nianji,@Param("st_class")String st_class,@Param("st_type")String st_type,@Param("st_leave_date") String st_leave_date,@Param("st_peiyangfangshi")String st_peiyangfangshi,@Param("st_leave_to")String st_leave_to,@Param("st_entertime")String st_entertime,@Param("st_xuejibiao_number")String st_xuejibiao_number,@Param("st_status")String st_status,@Param("st_status_stiker")String st_status_stiker);

    @Insert("insert into project1_customer(id_number,st_id,customer_face,customer_email,customer_tel) values(#{id_number},#{st_id},#{customer_face},#{customer_email},#{customer_tel})")
	public int addCustomer(@Param("id_number")String id_number,@Param("st_id")String id,@Param("customer_face") String customer_face,@Param("customer_email") String customer_email, @Param("customer_tel") String customer_tel);
}