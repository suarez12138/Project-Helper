package com.evan.wj.bean;

public class P_OnePreTime_title {
    String project_name;
    String start_time;
    String pretime_text;

    public P_OnePreTime_title(String project_name, String start_time,String pretime_text) {
        this.project_name = project_name;
        this.pretime_text = pretime_text;
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getPretime_text() {
        return pretime_text;
    }

    public void setPretime_text(String pretime_text) {
        this.pretime_text = pretime_text;
    }
}
