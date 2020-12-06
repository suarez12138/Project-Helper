package com.evan.wj.controller;


import com.evan.wj.bean.AllProject;
import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.PersonalInfo_pro;
import com.evan.wj.dao.PersonInfoDAO;
import com.evan.wj.dao.ProjectDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AAAController_temp {
    @Autowired
    TagDAO tagDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/personal/temp")
    @ResponseBody
    public TempleteResult<All_tag_inProject> getAllGroup(@RequestParam("project_id") int project_id){
        List<All_tag_inProject> sub1 = tagDAO.get_AllTag_in_project(project_id);

        TempleteResult<All_tag_inProject> allProjectResult_t = new TempleteResult<All_tag_inProject>(20000,sub1);

        return allProjectResult_t;
    }
}
