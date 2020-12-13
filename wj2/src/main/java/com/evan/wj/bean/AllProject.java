package com.evan.wj.bean;

public class AllProject {
    String course;
    String project_name;

    int project_id;

    public AllProject(String course, String project_name,int project_id) {
        this.course = course;
        this.project_name = project_name;

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



    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}
