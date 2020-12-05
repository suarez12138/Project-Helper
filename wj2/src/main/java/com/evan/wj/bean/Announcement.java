package com.evan.wj.bean;

public class Announcement {
    String name;
    String project;
    String by;
    String time;
    String text;
    int project_id;

    public Announcement(String name, String project, String by, String time, String text, int project_id) {
        this.name = name;
        this.project = project;
        this.by = by;
        this.time = time;
        this.text = text;
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
