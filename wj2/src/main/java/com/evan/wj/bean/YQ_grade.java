package com.evan.wj.bean;

public class YQ_grade {
    String project_name;
    double score;
    String the_comment;

    public YQ_grade(String project_name, double score, String the_comment) {
        this.project_name = project_name;
        this.score = score;
        this.the_comment = the_comment;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getThe_comment() {
        return the_comment;
    }

    public void setThe_comment(String the_comment) {
        this.the_comment = the_comment;
    }
}
