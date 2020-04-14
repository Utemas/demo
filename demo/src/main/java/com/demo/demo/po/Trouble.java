package com.demo.demo.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class Trouble {
    private int id;
    private String trouble_title;
    private String trouble_text;
    private String st_id;
    private String trouble_stat;
}