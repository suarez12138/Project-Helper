package com.evan.wj.bean;

public class AllProject {
    String course;
    String project_name;
    String the_class;
    int project_id;

    public AllProject(String course, String project_name, String the_class, int project_id) {
        this.course = course;
        this.project_name = project_name;
        this.the_class = the_class;
        this.project_id = project_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getThe_class() {
        return the_class;
    }

    public void setThe_class(String the_class) {
        this.the_class = the_class;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}
