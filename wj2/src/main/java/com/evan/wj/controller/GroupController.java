package com.evan.wj.controller;

import com.evan.wj.bean.AllGroup;
import com.evan.wj.dao.GroupDAO;
import com.evan.wj.dao.UserDAO;
import com.evan.wj.result.AllGroupResult;
import com.evan.wj.result.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupDAO groupDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/group_list")
    @ResponseBody
    public AllGroupResult getAllGroup(@RequestParam("group_id") int group_id){
        AllGroupResult allGroupResult = new AllGroupResult(20000, groupDAO.getAllGroup(group_id));
        return allGroupResult;
    }
}
