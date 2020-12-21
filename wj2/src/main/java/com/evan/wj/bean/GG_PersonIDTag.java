package com.evan.wj.bean;

public class GG_PersonIDTag {
    int person_id;
    int tag_id;

    public GG_PersonIDTag(int person_id, int tag_id) {
        this.person_id = person_id;
        this.tag_id = tag_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
