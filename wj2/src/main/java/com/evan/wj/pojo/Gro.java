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
    int check_point_id;
    int pre_day;
    String text;
    int index_inPreDay;

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

    public int getCheck_point_id() {
        return check_point_id;
    }

    public void setCheck_point_id(int check_point_id) {
        this.check_point_id = check_point_id;
    }

    public int getPre_day() {
        return pre_day;
    }

    public void setPre_day(int pre_day) {
        this.pre_day = pre_day;
    }

    public int getIndex_inPreDay() {
        return index_inPreDay;
    }

    public void setIndex_inPreDay(int index_inPreDay) {
        this.index_inPreDay = index_inPreDay;
    }
}
