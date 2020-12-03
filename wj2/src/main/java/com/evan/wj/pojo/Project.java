package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int course;
    String name;
    int min_people;
    int max_people;
    String bool_cross_class;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getMin_people() {
        return min_people;
    }

    public void setMin_people(int min_people) {
        this.min_people = min_people;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public String getBool_cross_class() {
        return bool_cross_class;
    }

    public void setBool_cross_class(String bool_cross_class) {
        this.bool_cross_class = bool_cross_class;
    }
}
