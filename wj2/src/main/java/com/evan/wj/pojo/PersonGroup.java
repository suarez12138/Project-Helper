       package com.evan.wj.pojo;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import javax.persistence.*;
        import java.sql.Timestamp;

@Entity

public class PersonGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int gro;
    int person;
    String pg_status;
    String grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGro() {
        return gro;
    }

    public void setGro(int gro) {
        this.gro = gro;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getPg_status() {
        return pg_status;
    }

    public void setPg_status(String pg_status) {
        this.pg_status = pg_status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
