package com.evan.wj.result;

import com.evan.wj.bean.AllGroup;
import com.evan.wj.bean.AllProject;

import java.util.List;

public class AllProjectResult {
    private int code;
    private List<AllProject> allProjects;

    public AllProjectResult(int code, List<AllProject> allProjects) {
        this.code = code;
        this.allProjects = allProjects;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AllProject> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(List<AllProject> allProjects) {
        this.allProjects = allProjects;
    }
}
