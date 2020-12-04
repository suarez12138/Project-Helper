package com.evan.wj.result;


import com.evan.wj.bean.AllGroup;

import java.util.List;

public class AllGroupResult {
    private int code;
    private List<AllGroup> allGroups;

    public AllGroupResult(int code, List<AllGroup> allGroups) {
        this.code = code;
        this.allGroups = allGroups;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AllGroup> getAllGroups() {
        return allGroups;
    }

    public void setAllGroups(List<AllGroup> allGroups) {
        this.allGroups = allGroups;
    }
}
