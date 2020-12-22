package com.evan.wj.bean;

import java.util.ArrayList;
import java.util.List;

public class PersonalInfo_pro_tea {
    int id;
    int gro_id;
    String stu_id;
    String dorm;
    String name;
    String gender;
    String lab;
    List<String> skill;
    String hope;
    String status;

    public PersonalInfo_pro_tea(int id, int gro_id,String stu_id, String dorm ,String name, String gender, String lab, String hope, String status) {
        this.id = id;
        this.gro_id = gro_id;
        this.dorm = dorm;
        this.name = name;
        this.stu_id = stu_id;
        this.gender = gender;
        this.lab = lab;
        this.skill = new ArrayList<String>();
        this.hope = hope;
        this.status = status;
    }

    public PersonalInfo_pro_tea(int id, String stu_id, String dorm ,String name, String gender, String lab, String hope, String status) {
        this.id = id;
        this.gro_id = 0;
        this.dorm = dorm;
        this.name = name;
        this.stu_id = stu_id;
        this.gender = gender;
        this.lab = lab;
        this.skill = new ArrayList<String>();
        this.hope = hope;
        this.status = status;
    }

    public int getGro_id() {
        return gro_id;
    }

    public void setGro_id(int gro_id) {
        this.gro_id = gro_id;
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

    public void addSkill(String sk){
        this.skill.add(sk);
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

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }

    public String getHope() {
        return hope;
    }

    public void setHope(String hope) {
        this.hope = hope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
