package com.evan.wj.bean;

public class GroupStatus {
    int gro_id;
    String is_valid; // 已到人数
    String can_join; // 可否加入
    long people_number;
    int min_people;
    int max_people;
    String group_info;

    public GroupStatus( int gro_id,String can_join, long people_number, int min_people, int max_people, String group_info) {
        this.gro_id = gro_id;
        this.is_valid = "";
        this.can_join = can_join;
        this.people_number = people_number;
        this.min_people = min_people;
        this.max_people = max_people;
        this.group_info = group_info;
    }

    public int getGro_id() {
        return gro_id;
    }

    public void setGro_id(int gro_id) {
        this.gro_id = gro_id;
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

    public long getPeople_number() {
        return people_number;
    }

    public void setPeople_number(long people_number) {
        this.people_number = people_number;
    }

    public int getMin_people() {
        return min_people;
    }

    public void setMin_people(int min_people) {
        this.min_people = min_people;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public String getGroup_info() {
        return group_info;
    }

    public void setGroup_info(String group_info) {
        this.group_info = group_info;
    }
}
