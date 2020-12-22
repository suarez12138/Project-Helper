package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.Grade;
import com.evan.wj.dao.GradeDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {

    @Autowired
    GradeDAO gradeDAO;


    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/grade/grade_stat")
    @ResponseBody
    public TempleteResult<Grade> getAllGroup(@RequestParam("project_id") int project_id){
        List<Integer> grades = gradeDAO.getGrade(project_id);
        int A =0;
        int B =0;
        int C =0;
        int D =0;
        int E =0;

        for(int gra: grades){
            if(gra <60){
                E++;
            }else if(gra <70){
                D++;
            }else if(gra <80){
                C++;
            }else if(gra <90){
                B++;
            }else{
                A++;
            }
        }

        List<Grade> sub1 = new ArrayList<>();
        sub1.add(new Grade(A,B,C,D,E));
        TempleteResult<Grade> allProjectResult_t = new TempleteResult<Grade>(20000,sub1);

        return allProjectResult_t;
    }
}
