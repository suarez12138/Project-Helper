package com.evan.wj.receive;

import java.util.List;

public class OnlineGradeUpdateReceive2 {
    List<String> stu_id;
    List<String> group_name;
    List<String> grades;
    List<String> comments;

    public OnlineGradeUpdateReceive2(List<String> stu_id, List<String> group_name, List<String> grades, List<String> comments) {
        this.stu_id = stu_id;
        this.group_name = group_name;
        this.grades = grades;
        this.comments = comments;
    }

    public List<String> getStu_id() {
        return stu_id;
    }

    public void setStu_id(List<String> stu_id) {
        this.stu_id = stu_id;
    }

    public List<String> getGroup_name() {
        return group_name;
    }

    public void setGroup_name(List<String> group_name) {
        this.group_name = group_name;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
