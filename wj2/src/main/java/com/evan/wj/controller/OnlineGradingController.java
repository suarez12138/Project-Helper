package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.OnlineGrade;
import com.evan.wj.dao.OnlineGradingDAO;
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
public class OnlineGradingController {
    @Autowired
    OnlineGradingDAO onlineGradingDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/grade/score_list")
    @ResponseBody
    public TempleteResult<OnlineGrade> getAllGroup(@RequestParam("project_id") int project_id){
        List<OnlineGrade> sub1 = onlineGradingDAO.getAllPerson(project_id);

        TempleteResult<OnlineGrade> allProjectResult_t = new TempleteResult<OnlineGrade>(20000,sub1);
        return allProjectResult_t;
    }
}
