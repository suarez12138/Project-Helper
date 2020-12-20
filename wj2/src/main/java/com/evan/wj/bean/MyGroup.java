package com.evan.wj.bean;

import java.util.ArrayList;
import java.util.List;

public class MyGroup {
    int id;
    String stu_id;
    String name;
    String gender;
    List<String> tags;

    public MyGroup(int id,String stu_id, String name, String gender) {
        this.id = id;
        this.stu_id = stu_id;
        this.name = name;
        this.gender = gender;
        this.tags = new ArrayList<String>();
    }

    public void addtags(String tag){
        this.tags.add(tag);
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
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
