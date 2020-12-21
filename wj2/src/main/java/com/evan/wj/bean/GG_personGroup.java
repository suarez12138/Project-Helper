package com.evan.wj.bean;

public class GG_personGroup {
    int person_id;
    int Group_id;

    public GG_personGroup(int person_id, int group_id) {
        this.person_id = person_id;
        Group_id = group_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getGroup_id() {
        return Group_id;
    }

    public void setGroup_id(int group_id) {
        Group_id = group_id;
    }
}
