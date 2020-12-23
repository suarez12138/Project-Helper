package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.dao.DdlDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DdlController {
    @Autowired
    DdlDAO ddlDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/beforeDDL")
    @ResponseBody
    public TempleteResult<String> getAllGroup(@RequestParam("project_id") int project_id){
        String project_ddl = ddlDAO.getDdl(project_id).get(0);

        int year1 = Integer.parseInt(project_ddl.substring(0,4));
        int month1 = Integer.parseInt(project_ddl.substring(5,7));
        int day1 = Integer.parseInt(project_ddl.substring(8,10));
        int hour1 = Integer.parseInt(project_ddl.substring(11,13));
        int minute1 = Integer.parseInt(project_ddl.substring(14,16));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式 // ：ss
        String now_time = df.format(new Date());

        int year2 = Integer.parseInt(now_time.substring(0,4));
        int month2 = Integer.parseInt(now_time.substring(5,7));
        int day2 = Integer.parseInt(now_time.substring(8,10));
        int hour2 = Integer.parseInt(now_time.substring(11,13));
        int minute2 = Integer.parseInt(now_time.substring(14,16));

        String judge = "true"; // 过了dll

        if(year1 > year2 ||
                (year1 == year2 && month1 > month2) ||
                (year1 == year2 && month1 == month2 && day1 > day2) ||
                (year1 == year2 && month1 == month2 && day1 == day2 && hour1 > hour2) ||
                (year1 == year2 && month1 == month2 && day1 == day2 && hour1 == hour2 && minute1 > minute2)
        ){
            judge = "false";
        }


        List<String> sub1 = new ArrayList<>();
        sub1.add(judge);
        TempleteResult<String> allProjectResult_t = new TempleteResult<String>(20000,sub1);
        return allProjectResult_t;
    }
}
