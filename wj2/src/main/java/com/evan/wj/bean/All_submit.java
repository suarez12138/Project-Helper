package com.evan.wj.bean;

public class All_submit {
    int submit_id;
    int submit_name;
    String submit_text;

    public All_submit(int submit_id, int submit_name, String submit_text) {
        this.submit_id = submit_id;
        this.submit_name = submit_name;
        this.submit_text = submit_text;
    }

    public int getSubmit_id() {
        return submit_id;
    }

    public void setSubmit_id(int submit_id) {
        this.submit_id = submit_id;
    }

    public int getSubmit_name() {
        return submit_name;
    }

    public void setSubmit_name(int submit_name) {
        this.submit_name = submit_name;
    }

    public String getSubmit_text() {
        return submit_text;
    }

    public void setSubmit_text(String submit_text) {
        this.submit_text = submit_text;
    }
}
