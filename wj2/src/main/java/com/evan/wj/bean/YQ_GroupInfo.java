package com.evan.wj.bean;

public class YQ_GroupInfo {
    int gro_id;
    String stu_id;
    String person_name;
    String lab_name;
    String dorm;
    String group_name;
    String gro_text;
    long group_number;
    String group_status;

    public YQ_GroupInfo(int gro_id, String stu_id, String person_name, String lab_name, String dorm ,String group_name, String gro_text, String group_status) {
        this.gro_id = gro_id;
        this.stu_id = stu_id;
        this.person_name = person_name;
        this.lab_name = lab_name;
        this.group_name = group_name;
        this.gro_text = gro_text;
        this.dorm = dorm;
        this.group_status = group_status;
        this.group_number = 0;
    }

    public int getGro_id() {
        return gro_id;
    }

    public void setGro_id(int gro_id) {
        this.gro_id = gro_id;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGro_text() {
        return gro_text;
    }

    public void setGro_text(String gro_text) {
        this.gro_text = gro_text;
    }

    public long getGroup_number() {
        return group_number;
    }

    public void setGroup_number(long group_number) {
        this.group_number = group_number;
    }

    public String getGroup_status() {
        return group_status;
    }

    public void setGroup_status(String group_status) {
        this.group_status = group_status;
    }
}
