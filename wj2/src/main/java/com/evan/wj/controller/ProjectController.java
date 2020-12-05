package com.evan.wj.controller;

import com.evan.wj.bean.AllProject;
import com.evan.wj.dao.GroupDAO;
import com.evan.wj.dao.ProjectDAO;
import com.evan.wj.result.AllGroupResult;
import com.evan.wj.result.AllProjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class ProjectController {
    @Autowired
    ProjectDAO projectDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/dashboard/project_list")
    @ResponseBody
    public AllProjectResult getAllGroup(@RequestParam("token") String token){

        AllProjectResult allProjectResult = new AllProjectResult(20000, projectDAO.getAllProject(token));
        return allProjectResult;
    }


}
