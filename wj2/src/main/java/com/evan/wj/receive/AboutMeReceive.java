package com.evan.wj.receive;

public class AboutMeReceive {
    String token;
    String password;
    String gender;
    String location;
    String introduction;

    public AboutMeReceive(String token, String password, String gender, String location, String introduction) {
        this.token = token;
        this.password = password;
        this.gender = gender;
        this.location = location;
        this.introduction = introduction;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
