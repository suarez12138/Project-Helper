package com.evan.wj.bean;

public class Pretime_student_select {
    String time_range;
    int time_id;
    String group_name;

    public Pretime_student_select(String time_range, int time_id, String group_name) {
        this.time_range = time_range;
        this.time_id = time_id;
        this.group_name = group_name;
    }

    public String getTime_range() {
        return time_range;
    }

    public void setTime_range(String time_range) {
        this.time_range = time_range;
    }

    public int getTime_id() {
        return time_id;
    }

    public void setTime_id(int time_id) {
        this.time_id = time_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
