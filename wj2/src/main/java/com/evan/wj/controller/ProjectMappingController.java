package com.evan.wj.controller;

import com.evan.wj.bean.AllProject;
import com.evan.wj.bean.ProjectMapping;
import com.evan.wj.dao.ProjectDAO;
import com.evan.wj.dao.ProjectMappingDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectMappingController {

    @Autowired
    ProjectMappingDAO projectMappingDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/all_project")
    @ResponseBody
    public TempleteResult<ProjectMapping> getAllGroup(){

        TempleteResult<ProjectMapping> allProjectResult_t = new TempleteResult<ProjectMapping>(20000,projectMappingDAO.getMapping());

        //AllProjectResult allProjectResult = new AllProjectResult(20000, projectDAO.getAllProject(token));
        return allProjectResult_t;

    }
}
