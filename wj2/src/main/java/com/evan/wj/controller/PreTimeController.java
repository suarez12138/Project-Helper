package com.evan.wj.controller;

import com.evan.wj.bean.*;
import com.evan.wj.dao.PreTimeDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.receive.PretimeReceive;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PreTimeController {
    @Autowired
    PreTimeDAO preTimeDAO;


    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/pre/get_time_suite")
    @ResponseBody
    public TempleteResult<P_AllPreTime> getAllPretime(@RequestParam("project_id") int project_id){
        List<P_AllPreTime> sub1 = preTimeDAO.get_allPreTime(project_id);
        TempleteResult<P_AllPreTime> allProjectResult_t = new TempleteResult<P_AllPreTime>(20000,sub1);

        return allProjectResult_t;
    }


    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/pre/get_time_title")
    @ResponseBody
    public TempleteResult<P_OnePreTime_title> get_OnePretime_title(@RequestParam("project_id") int project_id,@RequestParam("preTime_id") int preTime_id){
        List<P_OnePreTime_title> sub1 = preTimeDAO.get_OnePreTime_title(project_id,preTime_id);
        TempleteResult<P_OnePreTime_title> allProjectResult_t = new TempleteResult<P_OnePreTime_title>(20000,sub1);
        return allProjectResult_t;
    }

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/pre/get_time_detail")
    @ResponseBody
    public TempleteResult<Pretime_student_select> get_OnePretime_content(@RequestParam("project_id") int project_id, @RequestParam("preTime_id") int preTime_id){
        List<P_OnePreTime_content> sub1 = preTimeDAO.get_OnePreTime_content(project_id,preTime_id);
        int t_limit = sub1.get(0).getTime_limit();
        int g_limit = sub1.get(0).getGroup_limit();
        String start_time = sub1.get(0).getStart_time();
        List<Pretime_student_select> sub2 = new ArrayList<>();

        String hour = start_time.substring(11,13);
        String minute = start_time.substring(14);

        int h = Integer.parseInt(hour);
        int m = Integer.parseInt(minute);

        int m_mod = 0;
        int m_div = 0;

        String h_s = "";
        String m_s = "";

        String time_left ="";
        String time_right = "";
        String time_range;
        int index = 0;
        String group_name = "";

        for(int i=0;i<g_limit;i++){
            m_mod = (m + i * t_limit) % 60;
            m_div = (m + i * t_limit) / 60;
            m_s = m_mod + "";
            h_s = h + m_div + "";
            if (m_mod <10){
               m_s = "0" + m_s;
            }
            if (h + m_div < 10){
                h_s = "0" + h_s;
            }
            time_left = h_s + ":" + m_s;

            m_mod = (m + (i+1) * t_limit) % 60;
            m_div = (m + (i+1) * t_limit) / 60;
            m_s = m_mod + "";
            h_s = h + m_div + "";
            if (m_mod <10){
                m_s = "0" + m_s;
            }
            if (h + m_div < 10){
                h_s = "0" + h_s;
            }
            time_right = h_s + ":" + m_s;
            time_range = time_left + time_right;
            List<P_groIDName> one_group = preTimeDAO.get_groupByindex(preTime_id,i+1,project_id);
            if(one_group.size() >0){
                index = one_group.get(0).getGro_id();
                group_name = one_group.get(0).getGro_name();
            }else {
                index = one_group.get(0).getGro_id();
                group_name = "";
            }
            sub2.add(new Pretime_student_select(time_range,i+1,group_name));
        }
        TempleteResult<Pretime_student_select> allProjectResult_t = new TempleteResult<Pretime_student_select>(20000,sub2);
        return allProjectResult_t;
    }


    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/student/pre/update_preTime")
    @ResponseBody
    public Message_return getAllPretime(@RequestParam("token") String token,@RequestParam("project_id") int project_id,@RequestParam("preTime_id") int preTime_id, @RequestParam("time_id") int time_id){
        int gro_id = preTimeDAO.get_MyGroup_id(token,project_id).get(0);
        preTimeDAO.update_gro_index(time_id,gro_id);
        preTimeDAO.update_gro_preday(preTime_id,gro_id);
        return new Message_return(20000,"Success!");
    }


    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/teacher/pre/post_pre")
    @ResponseBody
    public Message_return insert_Pretime(@RequestBody PretimeReceive rec){
        int project_id = rec.getProject_id();
        preTimeDAO.Delete_pretime(project_id);
        int t_limit = rec.getTime_limit();
        int g_limit = rec.getGroup_limit();
        List<String> s_times = rec.getStart_time();
        List<String> texts = rec.getThe_text();
        String year = "";
        String hm = "";
        for (int i=0;i<s_times.size();i++) {
            year = s_times.get(i).substring(0,10);
            hm = s_times.get(i).substring(11,16);
            preTimeDAO.Insert_pre_time(project_id,g_limit,t_limit,texts.get(i),year + " " + hm);
        }
        return new Message_return(20000,"Success!");
    }



    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/teacher/pre/get_pre_list")
    @ResponseBody

    public TempleteResult<P_giveBackTeacher> getAllGroup(@RequestParam("project_id") int project_id){
        List<P_giveBackTeacher> sub1 = preTimeDAO.get_Selectback(project_id);


        TempleteResult<P_giveBackTeacher> allProjectResult_t = new TempleteResult<P_giveBackTeacher>(20000,sub1);

        return allProjectResult_t;
    }

}
