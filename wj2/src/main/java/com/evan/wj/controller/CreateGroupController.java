package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.ID_week;
import com.evan.wj.dao.CreateGroupDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.receive.NewGroupReceive;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CreateGroupController {

    @Autowired
    CreateGroupDAO createGroupDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/get_weeks")
    @ResponseBody
    public TempleteResult<ID_week> getAllGroup(@RequestParam("project_id") int project_id){
        List<ID_week> sub1 = createGroupDAO.getAllWeek(project_id);
        TempleteResult<ID_week> allProjectResult_t = new TempleteResult<ID_week>(20000,sub1);
        return allProjectResult_t;
    }


//    @CrossOrigin
//    @PostMapping(value = "/vue-element-admin/student/personal/update_my_info")
//    @ResponseBody
//    public Message_return update_personal_info(@RequestBody NewGroupReceive rec){
//
//        return new Message_return(20000,"Create successlly'");
//    }
//



}
