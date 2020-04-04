package com.demo.demo.Mapper;

import java.util.List;

import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.ContextInfo;
import com.demo.demo.po.Customer;
import com.demo.demo.po.Loginer;
import com.demo.demo.po.Person;
import com.demo.demo.po.Student;
import com.demo.demo.po.Urgent;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CustomerMapper {

    //查询Section
    //登录用
    @Select("select * from project1_login where st_id=#{id} and login_identify=#{identify}")
    public Loginer toLogin(@Param("identify") String identfy, @Param("id") String st_id);
    //查找用户个人信息
    @Select("select * from v_cu where st_id=#{id}")
    public Customer getCustomerByStid(@Param("id") String st_id);

    @Select("select * from project1_st where st_id = #{st_id}")
    public Student getStudentInfo(@Param("st_id")String st_id);

    @Select("select * from project1_person where id_number=#{id_number}")
    public Person getStudentPeronInformation(@Param("id_number") String id_number);
    
    //查询学生成绩数量
    @Select("select count(*) from project1_class where st_id=#{st_id}")
    public int theNumberOfClass(@Param("st_id") String st_id);
    //

    //查询课程信息
    @Select("select * from project1_class where st_id=#{st_id} and class_year=#{class_year}")
    public List<ClassInfo> getClassInformation(@Param("st_id") String st_id,@Param("class_year") String class_year);
    
    //查询大于85分的成绩数量
    @Select("select count(*) from project1_class where class_score >= 80 and st_id = #{st_id}")
    public int youxiuNumber(@Param("st_id") String st_id);
    //查询所有学籍学生
    @Select("select * from v_cu where customer_identify = 'st'")
    public List<Customer> findAllStudent();

    //按照学号查询学生
    @Select("select * from v_cu where customer_identify='st' and st_id=#{st_id}")
    public List<Customer> finStudentById(@Param("st_id") String st_id);

    //查询这个学生的紧急联系人
    @Select("select * from project1_urgent where st_id=#{st_id}")
    public List<Urgent> getUrgents(@Param("st_id") String st_id);

    //查询这个学生所有的课程信息
    @Select("select * from project1_class where st_id=#{st_id}")
    public List<ClassInfo> getALLClass(String st_id);
    
    //查询学生的个人名片
    @Select("select customer_jiguan, customer_tel,customer_email,customer_youzheng,customer_start_station,customer_end_station from project1_customer where id_number=#{id_number}")
    public ContextInfo selectContextInfo(@Param("id_number")String id_number);

    //插入Section
    //插入问题反馈信息
    @Insert("insert into project1_trouble(trouble_title,trouble_text,st_id, trouble_stat) values(#{trouble_title},#{trouble_text},#{st_id},'yes') ")
    public int addTrouble(@Param("trouble_title") String title, @Param("trouble_text") String text, @Param("st_id") String st_id);

    //删除Section
    //删除这个学生上过的课程
    @Delete("delete from project1_class where st_id=#{st_id}")
    public int deleteClassById(@Param("st_id")String st_id);

    //删除这个学生在系统中的个人信息
    @Delete("delete from project1_person where id_number=#{id_number}")
    public int deletePersonById(@Param("id_number")String id_number);

    //删除这个学生的毕业信息
    @Delete("delete from project1_from where id_number=#{id_number}")
    public int deleteFromById(@Param("id_number") String id_number);

    //删除这个学生的活动信息
    @Delete("delete from project1_from where st_id=#{st_id}")
    public int deleteActivityById(@Param("st_id") String st_id);

    //删除掉这个生的customer表
    @Delete("delete from project1_customer where id_number=#{id_number}")
    public int deleteCustomerById(@Param("id_number") String id_numbere);

    @Delete("delete from project1_st where st_id = #{st_id}")
	public int deleteStudentById(@Param("st_id") String st_id);

    @Delete("delete from project1_trouble where st_id = #{st_id}")
    public int delteeTrouble(String st_id);
    

    //@Update 部分
    //更新籍贯
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
	
    

}