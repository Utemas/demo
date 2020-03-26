package com.demo.demo.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class ClassInfo{
    private int id;
    private String class_id;
    private String class_name;
    private int class_score;
    private int class_xuefen;
    private String class_teacher;
    private String st_id;
    private String class_year;
    private String class_xueqi;
}