package com.evan.wj.result;

public class Result {
    //响应码
    private int code;
    private String stu_id;

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public Result(int code, String stu_id) {
        this.code = code;
        this.stu_id = stu_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}

