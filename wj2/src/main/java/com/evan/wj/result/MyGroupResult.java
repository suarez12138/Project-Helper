package com.evan.wj.result;

import com.evan.wj.bean.MyGroup;

import java.util.List;

public class MyGroupResult {
    int code;
    List<MyGroup> myGroups;

    public MyGroupResult(int code, List<MyGroup> myGroups) {
        this.code = code;
        this.myGroups = myGroups;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<MyGroup> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<MyGroup> myGroups) {
        this.myGroups = myGroups;
    }
}
