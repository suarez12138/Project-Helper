package com.evan.wj.controller;

import com.evan.wj.bean.AllCourse;
import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.The_id;
import com.evan.wj.dao.CreateProjectDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
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

    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/project/get_course")
    @ResponseBody
    public TempleteResult<AllCourse> getAllCourse(@RequestParam("token") String token){
        List<AllCourse> sub1 = createProjectDAO.getAllCourse(token);
        TempleteResult<AllCourse> allProjectResult_t = new TempleteResult<AllCourse>(20000,sub1);
        return allProjectResult_t;
    }

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/teacher/project/new_project_insert")
    @ResponseBody
    public Void_return create_new_project(@RequestBody NewProjectReceive newProInfo){
        int course_id = newProInfo.getCourse_id();
        int min_p = newProInfo.getProject_member_limit().get(0);
        int max_p = newProInfo.getProject_member_limit().get(1);
        String token = newProInfo.getToken();
        int teacher_id = upPersonInfoDAO.getID(token).get(0).getId();
        String pro_name = newProInfo.getProject_name();
        String bool_cross = newProInfo.getGrouping_freely();
        String bool_force = newProInfo.getChoosable_proj();

        String group_ddl = newProInfo.getPro_grouping_endDay() + " " + newProInfo.getPro_grouping_endHms();

        createProjectDAO.inseart_project(bool_cross,bool_force,course_id,max_p,min_p,pro_name,teacher_id, group_ddl);


        return new Void_return(20000);
    }

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/teacher/project/new_project_insert2")
    @ResponseBody
    public Void_return create_new_project2(@RequestBody NewProjectReceive newProInfo){
        int course_id = newProInfo.getCourse_id();
        int min_p = newProInfo.getProject_member_limit().get(0);
        int max_p = newProInfo.getProject_member_limit().get(1);
        String token = newProInfo.getToken();
        int teacher_id = upPersonInfoDAO.getID(token).get(0).getId();
        String pro_name = newProInfo.getProject_name();
        String bool_cross = newProInfo.getGrouping_freely();
        String bool_force = newProInfo.getChoosable_proj();
        List<String> all_tags = newProInfo.getAll_tags();
        List<String> project_pre_week = newProInfo.getProject_pre_week();
        String group_ddl = newProInfo.getPro_grouping_endDay() + " " + newProInfo.getPro_grouping_endHms();

        int pro_id = createProjectDAO.getProject_id(bool_cross,bool_force,course_id,max_p,min_p,pro_name,teacher_id).get(0).getId();
        List<The_id> people_ids = createProjectDAO.getAllPeopleInCourse(course_id);
        if(bool_force.equals("true")){
            // 把这个课程下所有的  人 拉进 want people
            for (The_id person_id:people_ids){
                createProjectDAO.insert_wantPerson(person_id.getId(),pro_id);
            }
        }
        for(String t: all_tags){
            // 把所有 tag 插入 tags 表格中
            createProjectDAO.insert_tags(pro_id,t);
        }
        for(String week: project_pre_week){
            // 把所有 week 插入 checkPoint 表格中
            createProjectDAO.insert_tags(week,pro_id);
        }
        return new Void_return(20000);
    }




}
