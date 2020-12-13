package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.OnlineGrade;
import com.evan.wj.dao.OnlineGradingDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.receive.NewProjectReceive;
import com.evan.wj.receive.OnlineGradeUpdateReceive;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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






    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/teacher/grade/score_list2")
    @ResponseBody
    public Void_return create_new_project(@RequestBody OnlineGradeUpdateReceive rec){
        List<Integer> group_ids = rec.getGroup_ids();
        List<Integer> person_ids = rec.getPerson_ids();
        List<Double> grades =  rec.getGrades();
        List<String> comments = rec.getComments();

        for(int i=0;i<person_ids.size();i++){
            onlineGradingDAO.update_grade(grades.get(i),person_ids.get(i),group_ids.get(i));
//            onlineGradingDAO.update_comment(comments.get(i),person_ids.get(i),group_ids.get(i));
        }

        return new Void_return(20000);
    }




}
