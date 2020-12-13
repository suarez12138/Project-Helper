package com.evan.wj.receive;

import java.util.List;

public class NewProjectReceive {
    String token;
    int course_id;
    String project_name;
    List<Integer> project_member_limit;
    List<String> project_pre_week;
    String pro_grouping_endDay;
    String pro_grouping_endHms;
    String across_lab;
    List<String> all_tags;
    String choosable_proj;

    public NewProjectReceive(String token, int course_id, String project_name, List<Integer> project_member_limit, List<String> project_pre_week, String pro_grouping_endDay, String pro_grouping_endHms, String across_lab, List<String> all_tags, String choosable_proj) {
        this.token = token;
        this.course_id = course_id;
        this.project_name = project_name;
        this.project_member_limit = project_member_limit;
        this.project_pre_week = project_pre_week;
        this.pro_grouping_endDay = pro_grouping_endDay;
        this.pro_grouping_endHms = pro_grouping_endHms;
        this.across_lab = across_lab;

        this.all_tags = all_tags;
        this.choosable_proj = choosable_proj;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public List<Integer> getProject_member_limit() {
        return project_member_limit;
    }

    public void setProject_member_limit(List<Integer> project_member_limit) {
        this.project_member_limit = project_member_limit;
    }

    public List<String> getProject_pre_week() {
        return project_pre_week;
    }

    public void setProject_pre_week(List<String> project_pre_week) {
        this.project_pre_week = project_pre_week;
    }

    public String getPro_grouping_endDay() {
        return pro_grouping_endDay;
    }

    public void setPro_grouping_endDay(String pro_grouping_endDay) {
        this.pro_grouping_endDay = pro_grouping_endDay;
    }

    public String getPro_grouping_endHms() {
        return pro_grouping_endHms;
    }

    public void setPro_grouping_endHms(String pro_grouping_endHms) {
        this.pro_grouping_endHms = pro_grouping_endHms;
    }

    public String getAcross_lab() {
        return across_lab;
    }

    public void setAcross_lab(String across_lab) {
        this.across_lab = across_lab;
    }



    public List<String> getAll_tags() {
        return all_tags;
    }

    public void setAll_tags(List<String> all_tags) {
        this.all_tags = all_tags;
    }

    public String getChoosable_proj() {
        return choosable_proj;
    }

    public void setChoosable_proj(String choosable_proj) {
        this.choosable_proj = choosable_proj;
    }
}
