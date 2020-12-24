package com.evan.wj.bean;

public class ALL_typeTwo_submission {
    int submit_id;
    String ann_name;
    String op_time;
    String submit_text;

    public ALL_typeTwo_submission(int submit_id, String ann_name, String op_time, String submit_text) {
        this.submit_id = submit_id;
        this.ann_name = ann_name;
        this.op_time = op_time;
        this.submit_text = submit_text;
    }

    public int getSubmit_id() {
        return submit_id;
    }

    public void setSubmit_id(int submit_id) {
        this.submit_id = submit_id;
    }

    public String getAnn_name() {
        return ann_name;
    }

    public void setAnn_name(String ann_name) {
        this.ann_name = ann_name;
    }

    public String getOp_time() {
        return op_time;
    }

    public void setOp_time(String op_time) {
        this.op_time = op_time;
    }

    public String getSubmit_text() {
        return submit_text;
    }

    public void setSubmit_text(String submit_text) {
        this.submit_text = submit_text;
    }
}
