package com.evan.wj.controller;

import com.evan.wj.bean.AllCourse;
import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.dao.CreateProjectDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.receive.NewProjectReceive;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CreateProController {
    @Autowired
    CreateProjectDAO createProjectDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/personal/temp232")
    @ResponseBody
    public TempleteResult<AllCourse> getAllCourse(@RequestParam("token") String token){
        List<AllCourse> sub1 = createProjectDAO.getAllCourse(token);
        TempleteResult<AllCourse> allProjectResult_t = new TempleteResult<AllCourse>(20000,sub1);
        return allProjectResult_t;
    }

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/personal/temp232123124")
    @ResponseBody
    public Void_return create_new_project(@RequestBody NewProjectReceive newProInfo){
        int course_id = newProInfo.getCourse_id();
        int max_p = newProInfo.getProject_member_limit().get(1);
        int min_p = newProInfo.getProject_member_limit().get(1);
        String bool_cross = newProInfo.getGrouping_freely();
        String gro_name = newProInfo.getProject_name();



        return new Void_return(20000);
    }


}
