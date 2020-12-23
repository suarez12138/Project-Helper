package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.ClassName;
import com.evan.wj.dao.AnnouncementReleaseDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.receive.OnlineGradeUpdateReceive;
import com.evan.wj.receive.ReleaseAnnouncementReceive;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import com.evan.wj.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.text.SimpleDateFormat;


import java.util.List;

@Controller
public class AnnouncementReleaseController {
    @Autowired
    AnnouncementReleaseDAO announcementReleaseDAO;
    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;

    @Autowired
    private MailServiceImpl mailService;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/dashboard/get_lab")
    @ResponseBody
    public TempleteResult<ClassName> getAllClassName(@RequestParam("project_id") int project_id){
        List<ClassName> sub1 = announcementReleaseDAO.getAllClassName(project_id);

        TempleteResult<ClassName> allProjectResult_t = new TempleteResult<ClassName>(20000,sub1);
        return allProjectResult_t;
    }

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/teacher/dashboard/update_announcement_list")
    @ResponseBody
    public Void_return releaseAnnouncement(@RequestBody ReleaseAnnouncementReceive rec){
        int pro_id = rec.getProject_id();
        String token = rec.getToken();
        String title = rec.getAnn_name();
        String content = rec.getText();

        int tea_id = upPersonInfoDAO.getID(token).get(0).getId();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式 // ：ss
        String op_time = df.format(new Date());
        announcementReleaseDAO.insert_submission_ann(title,op_time,tea_id,pro_id,content);
        List<String> SendTo = announcementReleaseDAO.getStuidInProject(pro_id);
        String tail = "@mail.sustech.edu.cn";

//        String sendTo = "";
//        for(String s:SendTo){
//            sendTo = s + tail;
//            mailService.sendSimpleMail(sendTo,title,content);
//        }
        String stu_id = "11813009";
        String send_test = stu_id + tail;
        mailService.sendSimpleMail(send_test,title,content);
        return new Void_return(20000);
    }



    @CrossOrigin
    @GetMapping(value = "/send_email_test")
    @ResponseBody
    public Void_return send_test(){


        System.out.println("12");
        String tail = "@mail.sustech.edu.cn";

//        String sendTo = "";
//        for(String s:SendTo){
//            sendTo = s + tail;
//            mailService.sendSimpleMail(sendTo,title,content);
//        }
        String stu_id = "11812917";
        String send_test = stu_id + tail;

        String title = "这是标题";
        String content = "这是内容";

        mailService.sendSimpleMail(send_test,title,content);
        return new Void_return(20000);
    }


}
