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
    String pre_week;
    String text;

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getPre_week() {
        return pre_week;
    }

    public void setPre_week(String pre_week) {
        this.pre_week = pre_week;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
