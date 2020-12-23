package com.evan.wj.bean;

public class P_giveBackTeacher {
    int preTime_id;
    int limit_group;
    int limit_time;
    int project_id;
    String start_time;
    String text;

    public P_giveBackTeacher(int preTime_id, int limit_group, int limit_time, int project_id, String start_time, String text) {
        this.preTime_id = preTime_id;
        this.limit_group = limit_group;
        this.limit_time = limit_time;
        this.project_id = project_id;
        this.start_time = start_time;
        this.text = text;
    }

    public int getPreTime_id() {
        return preTime_id;
    }

    public void setPreTime_id(int preTime_id) {
        this.preTime_id = preTime_id;
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

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
