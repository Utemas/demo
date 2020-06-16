package com.demo.demo.Mapper;

import java.util.List;

import com.demo.demo.po.Award;
import com.demo.demo.po.ClassInfo;
import com.demo.demo.po.ContextInfo;
import com.demo.demo.po.Customer;
import com.demo.demo.po.Enter;
import com.demo.demo.po.Link;
import com.demo.demo.po.Loginer;
import com.demo.demo.po.Person;
import com.demo.demo.po.Punish;
import com.demo.demo.po.Static;
import com.demo.demo.po.Student;
// import com.demo.demo.po.Trouble;
import com.demo.demo.po.Urgent;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {

    //查询Section
    //登录用
    @Select("select * from project1_login where st_id=#{id} and login_identify=#{identify}")
    public Loginer toLogin(@Param("identify") String identfy, @Param("id") String st_id);
    //查找用户个人信息
    @Select("select * from project1_link where st_id=#{id}")
    public Link getCustomerByStid(@Param("id") String st_id);

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

    //查询小于60分的成绩数量
    @Select("select count(*) from project1_class where class_score < 60 or class_score = '不及格' and st_id =#{st_id}")
    public int getBujigeNumber(@Param("st_id") String st_id);

    
    //查询所有学籍学生
    @Select("select * from project1_st")
    public List<Student> findAllStudent();

    //按照学号查询学生
    @Select("select * from project1_st where st_id=#{st_id}")
    public List<Student> finStudentById(@Param("st_id") String st_id);

    @Select("select * from project1_st where st_id=#{st_id}")
    public Student findStudentById(@Param("st_id") String st_id);


    //查询这个学生的紧急联系人
    @Select("select * from project1_urgent where st_id=#{st_id}")
    public List<Urgent> getUrgents(@Param("st_id") String st_id);

    //查询这个学生所有的课程信息
    @Select("select * from project1_class where st_id=#{st_id}")
    public List<ClassInfo> getALLClass(@Param("st_id")String st_id);

    @Select("select sum(class_xuefen) from project1_class where st_id=#{st_id}")
    public Object getTotalXueFen(@Param("st_id") String st_id);
    //查询学生的个人名片
    @Select("select customer_jiguan, customer_tel,customer_email,customer_youzheng,customer_start_station,customer_end_station from project1_customer where id_number=#{id_number}")
    public ContextInfo selectContextInfo(@Param("id_number")String id_number);

    @Select("select * from project1_from where id_number = #{id_number}")
    public Enter getEnterInfo(@Param("id_number") String id_number);
    
    //查询学生的获奖信息
    @Select("select * from project1_award where st_id = #{st_id}")
    public List<Award> getAward(@Param("st_id") String st_id);
    
    //查询学生的获奖信息的数量
    @Select("select count(*) from project1_award where st_id=#{st_id}")
    public int getAwardCount(@Param("st_id") String st_id);


    @Select("select count(*) from project1_punish where st_id=#{st_id}")
    public int getPunishCount(@Param("st_id")String st_id);

    //查询学生的惩罚信息
    @Select("select * from project1_punish where st_id=#{st_id}")
    public List<Punish> getPunish(@Param("st_id") String st_id);

    @Select("select * from project1_punish where id=#{id}")
    public Punish getPunishById(@Param("id")int id);
    
    @Select("select * from project1_award")
    public List<Award> getAllAward();

    @Select("select * from project1_award where id=#{id}")
    public Award getAwardById(@Param("id") int id);

    @Select("select * from project1_class")
    public List<ClassInfo>findAllClassInfo();
    
    @Delete("delete from project1_class where id=#{id}")
    public int deleteClassByClassId(@Param("id")int id);


    @Select("select id_number from project1_customer where st_id = #{st_id}")
    public String findStudentIdnumber(@Param("st_id") String st_id);
    
    @Select("select distinct st_xueYuan as label,count(st_xueYuan) as countNumber from project1_st group by st_xueYuan")
    public List<Static> findstaticInfo();
    
    @Select("select distinct addr_province as label,count(addr_province) as countNumber from project1_person group by addr_province")
    public List<Static> findstaticPInfo();

    @Select("select count(*) from project1_st")
    public int countSt();

    @Select("select count(*) from project1_person")
    public int countPerson();
    
    @Select("select distinct sex as label,count(sex) as countNumber from project1_person group by sex")
    public List<Static> findstaticSInfo();
    
    @Select("select * from project1_customer where st_id = #{st_id}")
    public Customer getSc(@Param("st_id")String st_id);
    

}