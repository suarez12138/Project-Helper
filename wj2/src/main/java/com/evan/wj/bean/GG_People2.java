package com.evan.wj.bean;

public class GG_People2 {
    int person_id;
    int class_id;
    String dormitory;
    String gender;

    public GG_People2(int person_id, int class_id, String dormitory, String gender) {
        this.person_id = person_id;
        this.class_id = class_id;
        this.dormitory = dormitory;
        this.gender = gender;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
