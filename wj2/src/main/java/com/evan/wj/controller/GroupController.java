package com.evan.wj.controller;

import com.evan.wj.bean.*;
import com.evan.wj.dao.GroupDAO;
import com.evan.wj.dao.UserDAO;
import com.evan.wj.result.AllGroupResult;
import com.evan.wj.result.MyGroupResult;
import com.evan.wj.result.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupDAO groupDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/group_list")
    @ResponseBody
    public AllGroupResult getAllGroup(@RequestParam("project_id") int project_id){
        AllGroupResult allGroupResult = new AllGroupResult(20000, groupDAO.getAllGroup(project_id));
        return allGroupResult;
    }



    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/group_list")
    @ResponseBody
    // MyGroupResult
    public MyGroupResult getMyGroup(@RequestParam("project_id") int project_id, @RequestParam("person_id") int person_id){
        List<MyGroup_getGroId> gro_id_list = groupDAO.getAMyGroup_getGroID(project_id,person_id);
        int gro_id = gro_id_list.get(0).getId();
        List<MyGroup_getNameGender> people_id_list = groupDAO.getAMyGroup_getName(gro_id);
        List<MyGroup> myGroups = new ArrayList<MyGroup>();

        for(MyGroup_getNameGender ids:people_id_list){
            myGroups.add(new MyGroup(ids.getId(),ids.getName(),ids.getGender()));
        }

        List<MyGroup_idTag> tags = groupDAO.gettags(gro_id);
        for (MyGroup_idTag tg: tags ){
            int person = tg.getId();
            for (MyGroup p: myGroups){
                if(person == p.getId()){
                    p.addtags(tg.getTag());
                }

            }
        }
        
        MyGroupResult myGroupResult = new MyGroupResult(20000, myGroups);
        return myGroupResult;
    }
}
