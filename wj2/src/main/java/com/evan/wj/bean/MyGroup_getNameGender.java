package com.evan.wj.bean;

public class MyGroup_getNameGender {
    int id;
    String stu_id;
    String dorm;
    String name;
    String gender;


    public MyGroup_getNameGender(int id, String stu_id,String dorm ,String name, String gender) {
        this.id = id;
        this.stu_id = stu_id;
        this.dorm = dorm;
        this.name = name;
        this.gender = gender;
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
}
