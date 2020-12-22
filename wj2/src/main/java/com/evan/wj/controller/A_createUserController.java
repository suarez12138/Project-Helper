package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.dao.A_createUserDAO;
import com.evan.wj.dao.JoinGroupDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class A_createUserController {
    @Autowired
    A_createUserDAO a_createUserDAO;

    @Autowired
    JoinGroupDAO joinGroupDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/nnnnnnnnnnnnnnnnnnnn6")
    @ResponseBody
    public Message_return a_create_user(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("role") String role){
        List<Integer> IDS = joinGroupDAO.getPersonID_ByToken(username);
        if(IDS.size() == 0){
            return new Message_return(20000,"Failed! There is no person with this ID");
        }
        List<Integer> somthing = a_createUserDAO.getSomething(username);
        if(somthing.size() > 0){
            return new Message_return(20000,"Failed! This person already has an account");
        }

        int person_id = IDS.get(0);
        a_createUserDAO.Insert_user(password,person_id,role,username);
        return new Message_return(20000,"Success!");
    }




    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/nnnnnnnnnnnnnnnnnnnn6")
    @ResponseBody
    public Message_return update_role(@RequestParam("username") String username,@RequestParam("role") String role){
        List<Integer> IDS = joinGroupDAO.getPersonID_ByToken(username);
        if(IDS.size() == 0){
            return new Message_return(20000,"Failed! This person is not in sustech");
        }
        List<Integer> somthing = a_createUserDAO.getSomething(username);
        if(somthing.size() > 0){
            return new Message_return(20000,"Failed! This person has no account");
        }
        a_createUserDAO.Update_userRole(role,username);
        return new Message_return(20000,"Success!");
    }
}
