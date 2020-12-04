package com.evan.wj.bean;

public class AllGroup {
    int id;
    String name;
    String pre_time;
    String status;

    public AllGroup(int id, String name, String pre_time, String status) {
        this.id = id;
        this.name = name;
        this.pre_time = pre_time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
