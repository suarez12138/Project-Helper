package com.evan.wj.bean;

import java.util.ArrayList;
import java.util.List;

public class MyGroup {
    int id;
    String name;
    String gender;
    List<String> tags;

    public MyGroup(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.tags = new ArrayList<String>();
    }

    public void addtags(String tag){
        tags.add(tag);
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
