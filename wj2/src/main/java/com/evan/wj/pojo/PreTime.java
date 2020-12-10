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

    int project;
    String pre_day;
    String text;

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
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

    public String getPre_day() {
        return pre_day;
    }

    public void setPre_day(String pre_day) {
        this.pre_day = pre_day;
    }
}