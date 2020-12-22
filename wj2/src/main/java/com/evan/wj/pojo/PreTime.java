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

    String start_time;
    int limit_group;
    int limit_time;
    String text;
    int project_id;


    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
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
