package com.evan.wj.bean;

public class P_OnePreTime_content {
    int group_limit;
    int time_limit;
    String start_time;

    public P_OnePreTime_content(int group_limit, int time_limit, String start_time) {
        this.group_limit = group_limit;
        this.time_limit = time_limit;
        this.start_time = start_time;
    }

    public int getGroup_limit() {
        return group_limit;
    }

    public void setGroup_limit(int group_limit) {
        this.group_limit = group_limit;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}
