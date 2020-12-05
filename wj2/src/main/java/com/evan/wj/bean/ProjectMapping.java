package com.evan.wj.bean;

public class ProjectMapping {
    int project_id;
    String project;
    int course_id;
    String course;

    public ProjectMapping(int project_id, String project, int course_id, String course) {
        this.project_id = project_id;
        this.project = project;
        this.course_id = course_id;
        this.course = course;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
