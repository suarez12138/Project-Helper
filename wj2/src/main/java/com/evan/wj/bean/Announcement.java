package com.evan.wj.bean;

public class Announcement {
    String Ann_name;
    String project_name;
    String release_time;
    String teacher_name;
    String text;
    int project_id;

    public Announcement(String ann_name, String project_name, String release_time, String teacher_name, String text, int project_id) {
        Ann_name = ann_name;
        this.project_name = project_name;
        this.release_time = release_time;
        this.teacher_name = teacher_name;
        this.text = text;
        this.project_id = project_id;
    }

    public String getAnn_name() {
        return Ann_name;
    }

    public void setAnn_name(String ann_name) {
        Ann_name = ann_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}
