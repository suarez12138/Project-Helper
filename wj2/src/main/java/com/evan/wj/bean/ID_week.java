package com.evan.wj.bean;

public class ID_week {
    int checkPoint_id;
    String week;
    String text;


    public ID_week(int checkPoint_id, String week, String text) {
        this.checkPoint_id = checkPoint_id;
        this.week = week;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCheckPoint_id() {
        return checkPoint_id;
    }

    public void setCheckPoint_id(int checkPoint_id) {
        this.checkPoint_id = checkPoint_id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
