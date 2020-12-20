package com.evan.wj.receive;

public class AboutMeReceive {
    String token;
    String Password;
    String Gender;
    String location;
    String introduction;

    public AboutMeReceive(String token,String password, String gender, String location, String introduction) {
        this.token = token;
        this.Password = password;
        this.Gender = gender;
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
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
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
