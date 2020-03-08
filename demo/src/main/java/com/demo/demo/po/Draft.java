package com.demo.demo.po;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Draft{
    private int id;
    private String draft_title;
    private String draft_article;
    private String customer_id;
    private Date draft_time;
}