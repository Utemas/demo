package com.demo.demo.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Award {
    private int id;
    private String award_name;
    private String award_type;
    private String award_time;
    private String award_condition;
    private String condition_css;
    private String st_id;
}


