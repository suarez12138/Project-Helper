package com.evan.wj.bean;

public class AboutMeDetail {
    int person_id;
    String name;
    String dorm;
    String gender;
    String self_introduction;

    public AboutMeDetail(int person_id, String name, String dorm, String gender, String self_introduction) {
        this.person_id = person_id;
        this.name = name;
        this.dorm = dorm;
        this.gender = gender;
        this.self_introduction = self_introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSelf_introduction() {
        return self_introduction;
    }

    public void setSelf_introduction(String self_introduction) {
        this.self_introduction = self_introduction;
    }
}
