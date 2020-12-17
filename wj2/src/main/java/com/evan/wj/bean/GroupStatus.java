package com.evan.wj.bean;

public class GroupStatus {
    String is_valid; // 已到人数
    String can_join; // 可否加入
    int people_number;
    int min_people;
    int max_people;
    String group_info;


    public GroupStatus(String group_info, int people_number, String is_valid, String can_join) {
        this.group_info = group_info;
        this.people_number = people_number;
        this.is_valid = is_valid;
        this.can_join = can_join;
    }

    public String getGroup_info() {
        return group_info;
    }

    public void setGroup_info(String group_info) {
        this.group_info = group_info;
    }

    public int getPeople_number() {
        return people_number;
    }

    public void setPeople_number(int people_number) {
        this.people_number = people_number;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getCan_join() {
        return can_join;
    }

    public void setCan_join(String can_join) {
        this.can_join = can_join;
    }
}
