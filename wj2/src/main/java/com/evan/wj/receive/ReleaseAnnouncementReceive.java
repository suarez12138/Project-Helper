package com.evan.wj.receive;

public class ReleaseAnnouncementReceive {
    int project_id;
    String token;
    String ann_name;
    String text;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getAnn_name() {
        return ann_name;
    }

    public void setAnn_name(String ann_name) {
        this.ann_name = ann_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
