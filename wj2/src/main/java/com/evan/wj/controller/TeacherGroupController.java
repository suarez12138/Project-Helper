package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.dao.TeacherGroupDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherGroupController {
    @Autowired
    TeacherGroupDAO teacherGroupDAO;

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/teacher/group/drop_group")
    @ResponseBody
    public Message_return getAllGroup(@RequestParam("gro_id") int gro_id){
        teacherGroupDAO.update_want_person(gro_id);
        teacherGroupDAO.delete_Group_teacher(gro_id);
        teacherGroupDAO.delete_GroupPerson_teacher(gro_id);
        return new Message_return(20000,"Success!");
    }

}
