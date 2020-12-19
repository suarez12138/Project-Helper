package com.evan.wj.bean;

public class GG_AllGroup {
    int group_id;
    int person_id;
    int class_id;
    String dormitory;
    int tag;

    public GG_AllGroup(int group_id, int person_id, int class_id, String dormitory, int tag) {
        this.group_id = group_id;
        this.person_id = person_id;
        this.class_id = class_id;
        this.dormitory = dormitory;
        this.tag = tag;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
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

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
