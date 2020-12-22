package com.evan.wj.controller;

import com.evan.wj.dao.JoinGroupDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JoinGroupController {
    @Autowired
    JoinGroupDAO joinGroupDAO;

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/student/group/join_group")
    @ResponseBody
    public Message_return joinGroup(@RequestParam("token") String token, @RequestParam("gro_id") int gro_id){

        int person_id = joinGroupDAO.getPersonID_ByToken(token).get(0);
        int project_id = joinGroupDAO.getProID_ByGroID(gro_id).get(0);
        String gro_status = joinGroupDAO.getWantperosnStatus(person_id,project_id).get(0);
        if(gro_status.equals("未组队")){
            joinGroupDAO.insert_PersonGro(gro_id,person_id);
            joinGroupDAO.update_wantPerson_ToYiZuDui(person_id,project_id);
            return new Message_return(20000,"Success!");
        }
        return new Message_return(20000,"Failed! You already in a group");

    }
}
