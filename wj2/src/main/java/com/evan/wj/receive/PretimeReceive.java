package com.evan.wj.receive;

import java.util.List;

public class PretimeReceive {

    int project_id;
    int time_limit;
    int group_limit;
    List<String> start_time;
    List<String> the_text;

    public PretimeReceive(int project_id, int time_limit, int group_limit, List<String> start_time, List<String> the_text) {
        this.project_id = project_id;
        this.time_limit = time_limit;
        this.group_limit = group_limit;
        this.start_time = start_time;
        this.the_text = the_text;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public int getGroup_limit() {
        return group_limit;
    }

    public void setGroup_limit(int group_limit) {
        this.group_limit = group_limit;
    }

    public List<String> getStart_time() {
        return start_time;
    }

    public void setStart_time(List<String> start_time) {
        this.start_time = start_time;
    }

    public List<String> getThe_text() {
        return the_text;
    }

    public void setThe_text(List<String> the_text) {
        this.the_text = the_text;
    }
}
