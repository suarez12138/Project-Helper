package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.YQ_grade;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.YQ_gradeDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class YQ_GradeController {

    @Autowired
    YQ_gradeDAO yq_gradeDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/profile/get_score")
    @ResponseBody
    public TempleteResult<YQ_grade> get_myGrade(@RequestParam("token") String token){
        List<YQ_grade> sub1 = yq_gradeDAO.getGrade_YQ(token);
        TempleteResult<YQ_grade> allProjectResult_t = new TempleteResult<YQ_grade>(20000,sub1);
        return allProjectResult_t;
    }

}
