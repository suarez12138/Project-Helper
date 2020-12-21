package com.evan.wj.controller;


import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.service.GroupService;
import com.evan.wj.service.GroupingMethod2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupingMethod2Controller {
    @Autowired
    GroupingMethod2 groupingMethod2;

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/teacher/group/group_autoform")
    @ResponseBody
    public Message_return getAllGroup(@RequestParam("project_id") int project_id){
        System.out.println(123);
        return groupingMethod2.Main_method2(project_id);
    }
}
