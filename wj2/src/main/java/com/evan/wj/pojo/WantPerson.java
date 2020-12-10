package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
public class WantPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int person;
    int project;
    String text;
    String gro_status;


    public String getGro_status() {
        return gro_status;
    }

    public void setGro_status(String gro_status) {
        this.gro_status = gro_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
