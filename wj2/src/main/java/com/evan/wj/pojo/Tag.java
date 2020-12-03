    package com.evan.wj.pojo;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import javax.persistence.*;
        import java.sql.Timestamp;

@Entity

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String project;
    String tag;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
