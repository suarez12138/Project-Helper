package com.evan.wj.bean;

public class All_tag_inProject {
    int tag_id;
    String tag;

    public All_tag_inProject(int tag_id, String tag) {
        this.tag_id = tag_id;
        this.tag = tag;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
