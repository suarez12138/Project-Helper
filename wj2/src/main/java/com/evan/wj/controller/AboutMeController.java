package com.evan.wj.controller;

import com.evan.wj.dao.AboutMeDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.receive.AboutMeReceive;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AboutMeController {
    @Autowired
    AboutMeDAO aboutMeDAO;
    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;
    // detail
    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/student/profile/update_profile")
    @ResponseBody
    public Void_return update_AboutMe(@RequestBody AboutMeReceive Ab){
        String token = Ab.getToken();
        int person_id = upPersonInfoDAO.getID(token).get(0).getId();

        aboutMeDAO.update_dorm(Ab.getLocation(),person_id);
        aboutMeDAO.update_gender(Ab.getGender(),person_id);
        aboutMeDAO.update_password(Ab.getPassword(),person_id);
        aboutMeDAO.update_password(Ab.getPassword(),person_id);


        return new Void_return(20000);
    }

}
