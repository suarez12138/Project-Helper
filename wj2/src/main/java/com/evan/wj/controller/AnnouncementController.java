package com.evan.wj.controller;

import com.evan.wj.bean.AllProject;
import com.evan.wj.bean.Announcement;
import com.evan.wj.dao.AnnouncementDAO;
import com.evan.wj.dao.ProjectDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnnouncementController {
    @Autowired
    AnnouncementDAO announcementDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/dashboard/announcement_list")
    @ResponseBody
    public TempleteResult<Announcement> getAllAnn_stu(@RequestParam("token") String token){

        TempleteResult<Announcement> allProjectResult_t = new TempleteResult<Announcement>(20000,announcementDAO.getAllAnnouncement_student(token));

        //AllProjectResult allProjectResult = new AllProjectResult(20000, projectDAO.getAllProject(token));
        return allProjectResult_t;

    }

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/dashboard/announcement_list")
    @ResponseBody
    public TempleteResult<Announcement> getAllAnn_teacher(@RequestParam("token") String token){

        TempleteResult<Announcement> allProjectResult_t = new TempleteResult<Announcement>(20000,announcementDAO.getAllAnnouncement_teacher(token));

        //AllProjectResult allProjectResult = new AllProjectResult(20000, projectDAO.getAllProject(token));
        return allProjectResult_t;

    }
}
