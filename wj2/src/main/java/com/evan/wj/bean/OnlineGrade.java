package com.evan.wj.bean;

public class OnlineGrade {
    int person_id;
    int group_id;
    Double score;
    String stu_id;
    String person_name;
    String group_name;
    String com_text;

    public OnlineGrade(int person_id, int group_id, double score,String stu_id, String person_name, String group_name,String com_text) {
        this.person_id = person_id;
        this.group_id = group_id;
        this.score = score;
        this.stu_id = stu_id;
        this.person_name = person_name;
        this.group_name = group_name;
        this.com_text = com_text;
    }

    public String getCom_text() {
        return com_text;
    }

    public void setCom_text(String com_text) {
        this.com_text = com_text;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
