package com.evan.wj.bean;

public class GroupStatus2 {
    int gro_id;
    String Pre_week;
    String group_status;
    String text;

    public GroupStatus2(int gro_id, String pre_week, String group_status, String text) {
        this.gro_id = gro_id;
        Pre_week = pre_week;
        this.group_status = group_status;
        this.text = text;
    }

    public int getGro_id() {
        return gro_id;
    }

    public void setGro_id(int gro_id) {
        this.gro_id = gro_id;
    }

    public String getPre_week() {
        return Pre_week;
    }

    public void setPre_week(String pre_week) {
        Pre_week = pre_week;
    }

    public String getGroup_status() {
        return group_status;
    }

    public void setGroup_status(String group_status) {
        this.group_status = group_status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
