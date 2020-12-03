      package com.evan.wj.pojo;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import javax.persistence.*;

@Entity

public class PersonClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int person;
    int class1;
    String pc_status;
    String class_authority;

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

    public int getClass1() {
        return class1;
    }

    public void setClass1(int class1) {
        this.class1 = class1;
    }

    public String getPc_status() {
        return pc_status;
    }

    public void setPc_status(String pc_status) {
        this.pc_status = pc_status;
    }

    public String getClass_authority() {
        return class_authority;
    }

    public void setClass_authority(String class_authority) {
        this.class_authority = class_authority;
    }
}
