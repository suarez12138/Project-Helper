package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.DD_Project_GiveBack;
import com.evan.wj.dao.DD_modifiedProjectDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DD_modifiedProjectController {
    @Autowired
    DD_modifiedProjectDAO dd_modifiedProjectDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/personal/tempasfafsaf")
    @ResponseBody
    public TempleteResult<DD_Project_GiveBack> getAllGroup(@RequestParam("project_id") int project_id){
        List<DD_Project_GiveBack> sub1 = dd_modifiedProjectDAO.getGiveBack_main(project_id);
        List<String> tags = dd_modifiedProjectDAO.getGiveBack_tag(project_id);
        List<String> preWeeks = dd_modifiedProjectDAO.getGiveBack_preWeek(project_id);


        TempleteResult<DD_Project_GiveBack> allProjectResult_t = new TempleteResult<DD_Project_GiveBack>(20000,sub1);

        return allProjectResult_t;
    }
}
