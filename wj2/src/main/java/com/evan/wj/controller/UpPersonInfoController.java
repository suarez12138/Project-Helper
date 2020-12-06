package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;

import com.evan.wj.dao.TagDAO;

import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.pojo.User;

import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UpPersonInfoController {
    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/personal/updata_my_info")
    @ResponseBody
    public Void_return update_personal_info(@RequestBody Person_info_update Pi){
        int project_id = Pi.getProject_id();
        String stu_id = Pi.getToken();
        List<Integer> skills = Pi.getSkill();
        String hope = Pi.getExpect();

        int person_id = upPersonInfoDAO.getID(stu_id).get(0).getId();

        upPersonInfoDAO.delete_person_tag(person_id,project_id);
        upPersonInfoDAO.update_want_person(hope,person_id,project_id);

        for(int s: skills){
            upPersonInfoDAO.inseart_person_tag(person_id,s);
        }
        return new Void_return(20000);
    }
}
