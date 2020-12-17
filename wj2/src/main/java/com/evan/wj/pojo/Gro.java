package com.evan.wj.pojo;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import javax.persistence.*;
        import java.sql.Timestamp;

@Entity

public class Gro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int project;
    String group_name;
    String group_status;
    String pre_week;
    String pre_day;
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_status() {
        return group_status;
    }

    public void setGroup_status(String group_status) {
        this.group_status = group_status;
    }

    public String getPre_week() {
        return pre_week;
    }

    public void setPre_week(String pre_week) {
        this.pre_week = pre_week;
    }

    public String getPre_day() {
        return pre_day;
    }

    public void setPre_day(String pre_day) {
        this.pre_day = pre_day;
    }
}
