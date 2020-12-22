package com.evan.wj.bean;

public class P_AllPreTime {
    int preTime_id;
    String start_time;

    public P_AllPreTime(int preTime_id, String start_time) {
        this.preTime_id = preTime_id;
        this.start_time = start_time;
    }

    public int getPreTime_id() {
        return preTime_id;
    }

    public void setPreTime_id(int preTime_id) {
        this.preTime_id = preTime_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}
