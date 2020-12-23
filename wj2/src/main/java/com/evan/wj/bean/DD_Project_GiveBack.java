package com.evan.wj.bean;

import java.util.ArrayList;
import java.util.List;

public class DD_Project_GiveBack {
    int max;
    int min;
    String project_name;
    String project_ddl;
    String across_lab;
    String force_join;
    List<String> all_tags;
    List<String> project_pre_week;

    public DD_Project_GiveBack(int max, int min, String project_name, String project_ddl, String across_lab, String force_join) {
        this.max = max;
        this.min = min;
        this.project_name = project_name;
        this.project_ddl = project_ddl;
        this.across_lab = across_lab;
        this.force_join = force_join;
        this.all_tags = new ArrayList<>();
        this.project_pre_week = new ArrayList<>();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_ddl() {
        return project_ddl;
    }

    public void setProject_ddl(String project_ddl) {
        this.project_ddl = project_ddl;
    }

    public String getAcross_lab() {
        return across_lab;
    }

    public void setAcross_lab(String across_lab) {
        this.across_lab = across_lab;
    }

    public String getForce_join() {
        return force_join;
    }

    public void setForce_join(String force_join) {
        this.force_join = force_join;
    }

    public List<String> getAll_tags() {
        return all_tags;
    }

    public void setAll_tags(List<String> all_tags) {
        this.all_tags = all_tags;
    }

    public List<String> getProject_pre_week() {
        return project_pre_week;
    }

    public void setProject_pre_week(List<String> project_pre_week) {
        this.project_pre_week = project_pre_week;
    }
}
