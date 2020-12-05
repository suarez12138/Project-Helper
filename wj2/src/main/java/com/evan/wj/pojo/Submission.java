       package com.evan.wj.pojo;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import javax.persistence.*;

@Entity

public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int person;
    int project;
    String operate_time;
    String submission_type;
    String text;
    String Ano_name;

    public String getAno_name() {
        return Ano_name;
    }

    public void setAno_name(String ano_name) {
        Ano_name = ano_name;
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

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getSubmission_type() {
        return submission_type;
    }

    public void setSubmission_type(String submission_type) {
        this.submission_type = submission_type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
