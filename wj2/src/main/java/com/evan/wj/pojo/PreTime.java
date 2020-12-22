package com.evan.wj.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PreTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String the_day;
    int limit_group;
    int limit_time;
    String text;
    int checkPoint_id;
    String the_time;


    public int getCheckPoint_id() {
        return checkPoint_id;
    }

    public void setCheckPoint_id(int checkPoint_id) {
        this.checkPoint_id = checkPoint_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThe_day() {
        return the_day;
    }

    public void setThe_day(String the_day) {
        this.the_day = the_day;
    }

    public String getThe_time() {
        return the_time;
    }

    public void setThe_time(String the_time) {
        this.the_time = the_time;
    }

    public int getLimit_group() {
        return limit_group;
    }

    public void setLimit_group(int limit_group) {
        this.limit_group = limit_group;
    }

    public int getLimit_time() {
        return limit_time;
    }

    public void setLimit_time(int limit_time) {
        this.limit_time = limit_time;
    }
}
