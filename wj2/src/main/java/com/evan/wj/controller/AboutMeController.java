package com.evan.wj.controller;

import com.evan.wj.bean.AboutMeDetail;
import com.evan.wj.bean.Announcement;
import com.evan.wj.dao.AboutMeDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.receive.AboutMeReceive;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AboutMeController {
    @Autowired
    AboutMeDAO aboutMeDAO;
    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/student/profile/update_profile")
    @ResponseBody
    public Void_return update_AboutMe(@RequestBody AboutMeReceive Ab){
        String token = Ab.getToken();
        int person_id = upPersonInfoDAO.getID(token).get(0).getId();

        // in User
        if(Ab.getPassword() != null){
            aboutMeDAO.update_password(Ab.getPassword(),person_id);
        }
//        System.out.println(Ab.getPassword());


        //
        aboutMeDAO.update_dorm(Ab.getLocation(),person_id);
//        System.out.println(Ab.getLocation());
        aboutMeDAO.update_gender(Ab.getGender(),person_id);
//        System.out.println(Ab.getGender());
        aboutMeDAO.update_self_introduction(Ab.getIntroduction(),person_id);
//        System.out.println(Ab.getIntroduction());


        return new Void_return(20000);
    }

//String person_id, String dorm, String gender, String self_introduction
    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/profile/detail_profile")
    @ResponseBody
    public TempleteResult<AboutMeDetail> getAllAnn_stu(@RequestParam("token") String token){
        int person_id = upPersonInfoDAO.getID(token).get(0).getId();
        TempleteResult<AboutMeDetail> allProjectResult_t = new TempleteResult<AboutMeDetail>(20000,aboutMeDAO.getAboutMeDetail(token));
        //AllProjectResult allProjectResult = new AllProjectResult(20000, projectDAO.getAllProject(token));
        return allProjectResult_t;
    }
}
