package com.evan.wj.result;

import com.evan.wj.bean.MyGroup;

import java.util.List;

public class MyGroupResult {
    int code;
    int gro_len;
    List<MyGroup> myGroups;

    public MyGroupResult(int code, int gro_len, List<MyGroup> myGroups) {
        this.code = code;
        this.gro_len = gro_len;
        this.myGroups = myGroups;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getGro_len() {
        return gro_len;
    }

    public void setGro_len(int gro_len) {
        this.gro_len = gro_len;
    }

    public List<MyGroup> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<MyGroup> myGroups) {
        this.myGroups = myGroups;
    }
}
