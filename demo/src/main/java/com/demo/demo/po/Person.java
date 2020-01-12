package com.demo.demo.po;
import lombok.Getter;
import lombok.Setter;

public class Person{
    @Getter @Setter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private String id_number;
    @Getter @Setter private String addr_province;
    @Getter @Setter private String addr_city;
    @Getter @Setter private String addr_community;
    @Getter @Setter private String addr_street;
    @Getter @Setter private String addr_block;
    @Getter @Setter private String addr_unit;
    @Getter @Setter private String addr_floor;
    @Getter @Setter private String addr_room;
    @Override public String toString(){
        return String.format("(id: %d)",id);
    }
}