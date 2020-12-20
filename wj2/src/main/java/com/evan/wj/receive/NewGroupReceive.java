package com.evan.wj.receive;

import java.util.List;

public class NewGroupReceive {

    int project_id;
    String group_name;
    int check_point_id;
    String text;
    List<Integer> person_id;
    int self_id;


    public NewGroupReceive(int project_id, String group_name, int check_point_id, String text, List<Integer> person_id, int self_id) {
        this.project_id = project_id;
        this.group_name = group_name;
        this.check_point_id = check_point_id;
        this.text = text;
        this.person_id = person_id;
        this.self_id = self_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getCheck_point_id() {
        return check_point_id;
    }

    public void setCheck_point_id(int check_point_id) {
        this.check_point_id = check_point_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getPerson_id() {
        return person_id;
    }

    public void setPerson_id(List<Integer> person_id) {
        this.person_id = person_id;
    }

    public int getSelf_id() {
        return self_id;
    }

    public void setSelf_id(int self_id) {
        this.self_id = self_id;
    }
}
