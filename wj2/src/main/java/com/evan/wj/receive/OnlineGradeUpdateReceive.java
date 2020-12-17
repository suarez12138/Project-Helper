package com.evan.wj.receive;

import java.util.List;

public class OnlineGradeUpdateReceive {
    List<Integer> person_ids;
    List<Integer> group_ids;

    List<String> grades;
    List<String> comments;


    public OnlineGradeUpdateReceive(List<Integer> person_ids, List<Integer> group_ids, List<String> grades, List<String> comments) {
        this.person_ids = person_ids;
        this.group_ids = group_ids;
        this.grades = grades;
        this.comments = comments;
    }

    public List<Integer> getPerson_ids() {
        return person_ids;
    }

    public void setPerson_ids(List<Integer> person_ids) {
        this.person_ids = person_ids;
    }

    public List<Integer> getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(List<Integer> group_ids) {
        this.group_ids = group_ids;
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
