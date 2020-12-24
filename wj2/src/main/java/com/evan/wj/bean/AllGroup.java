package com.evan.wj.bean;

public class AllGroup {
    int gro_id;
    String name;
    String pre_time;
    String status;

    public AllGroup(int gro_id, String name, String pre_time, String status) {
        this.gro_id = gro_id;
        this.name = name;
        this.pre_time = pre_time;
        this.status = status;
    }

    public int getGro_id() {
        return gro_id;
    }

    public void setGro_id(int gro_id) {
        this.gro_id = gro_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPre_time() {
        return pre_time;
    }

    public void setPre_time(String pre_time) {
        this.pre_time = pre_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
