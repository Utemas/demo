package com.demo.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdateMapper {

    @Update("update project1_customer set customer_jiguan=#{customer_jiguan} where id_number=#{id_number}")
    public int updateContextInfo(@Param("customer_jiguan") String customer_jiguan,@Param("id_number")String id_number);

    //更新电话
    @Update("update project1_customer set customer_tel=#{customer_tel} where id_number=#{id_number}")
	public int updateTel(@Param("customer_tel")String customer_tel,@Param("id_number") String id_number);

    //更新Email
    @Update("update project1_customer set customer_email=#{customer_email} where id_number=#{id_number}")
	public int updateEmail(String customer_email, String id_number);

    //更新邮政编码
    @Update("update project1_customer set customer_youzheng=#{cyb} where id_number=#{id_number}")
    public int updateYouBian(@Param("cyb")String customer_youzheng, @Param("id_number")String id_number);

    //更新起始地
    @Update("update project1_customer set customer_start_station=#{css} where id_number=#{id_number}")
    public int updateStartStation(@Param("css")String customer_start_station, @Param("id_number") String id_number);

    @Update("update project1_customer set customer_end_station = #{end} where id_number=#{id_number}")
    public int updateEndStation(@Param("end")String customer_end_station, @Param("id_number")String id_number);
    
    @Update("update project1_login set password = #{password} where st_id = #{st_id}")
    public int updatePassword(@Param("password") String newPassword, @Param("st_id") String st_id);

    @Update("update project1_award set award_condition =#{condition}, condition_css=#{css} where id=#{id}")
    public int updateAwardStatus(@Param("condition") String award_condition, @Param("css") String condition_css, @Param("id")int id);

    @Update("update project1_st set st_xueYuan = #{st_xueYuan}, st_zhuanye = #{st_zhuanye}, st_nianji = #{st_nianji}, st_class = #{st_class}, st_leave_to = #{st_leave_to}, st_status = #{st_status}, st_status_stiker = #{st_status_stiker} where st_id = #{st_id}")
    public int updteSinfo(@Param("st_xueYuan")String new_st_xueYuan, @Param("st_zhuanye")String new_st_zhuanye, @Param("st_nianji")String new_st_nianji, @Param("st_class")String new_st_class, @Param("st_leave_to")String new_leave_to, @Param("st_status")String new_status,@Param("st_status_stiker")String st_status_stiker, @Param("st_id")String st_id);

    @Update("update project1_customer set customer_face=#{customer_face} where st_id=#{st_id}")
	public int updateFace(@Param("customer_face") String new_face, @Param("st_id")String st_id);

    @Update("update project1_login set login_identify=#{status} where st_id=#{st_id}")
	public int updateLoginStatus(@Param("status") String status, @Param("st_id") String st_id);
    
}