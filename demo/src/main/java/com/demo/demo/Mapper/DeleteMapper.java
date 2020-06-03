package com.demo.demo.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeleteMapper {

    @Delete("delete from project1_urgent where id=#{id}")
    public int deleteUrgent(@Param("id") int id);

    
    
}