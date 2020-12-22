package com.evan.wj.receive;

public class PretimeReceive {
    int checkPoint_id;
    String the_day;
    String the_time;
    String time_limit;
    String group_limit;
    String the_text;

    public PretimeReceive(int checkPoint_id, String the_day, String the_time, String time_limit, String group_limit, String the_text) {
        this.checkPoint_id = checkPoint_id;
        this.the_day = the_day;
        this.the_time = the_time;
        this.time_limit = time_limit;
        this.group_limit = group_limit;
        this.the_text = the_text;
    }

    public int getCheckPoint_id() {
        return checkPoint_id;
    }

    public void setCheckPoint_id(int checkPoint_id) {
        this.checkPoint_id = checkPoint_id;
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

    public String getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(String time_limit) {
        this.time_limit = time_limit;
    }

    public String getGroup_limit() {
        return group_limit;
    }

    public void setGroup_limit(String group_limit) {
        this.group_limit = group_limit;
    }

    public String getThe_text() {
        return the_text;
    }

    public void setThe_text(String the_text) {
        this.the_text = the_text;
    }
}
