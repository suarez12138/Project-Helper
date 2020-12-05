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
    @GetMapping(value = "/vue-element-admin/student/group/my_group")
    @ResponseBody
    // MyGroupResult
    public MyGroupResult getMyGroup(@RequestParam("token") String token, @RequestParam("project_id") int project_id){
        List<MyGroup> myGroups = new ArrayList<MyGroup>();

//        MyGroup a = new MyGroup(1,"wky1","boy");
//        a.addtags("前端");
//        myGroups.add(a);

        // GetGroID {id }
        List<MyGroup_getGroId> gro_id_list = groupDAO.getAMyGroup_getGroID(token, project_id);
        int gro_id = gro_id_list.get(0).getId();
        // NameGender {int id, String name, String gender, List<String》 tag}
        List<MyGroup_getNameGender> people_id_list = groupDAO.getAMyGroup_getName(gro_id);

        // MyGroup {int id, String name, String gender, List<String》 tag}
        for(MyGroup_getNameGender ids:people_id_list){
            myGroups.add(new MyGroup(ids.getId(),ids.getName(),ids.getGender()));
        }

        List<MyGroup_idTag> tags = groupDAO.gettags(gro_id);
        // MyGroup_idTag {int id, String tag}
        for (MyGroup_idTag tg: tags ){
            int person = tg.getId();
            // MyGroup {int id, String name, String gender, List<String》 tag}
            for (MyGroup p: myGroups){
                if(person == p.getId()){
                    p.getTags().add(tg.getTag());
                }
            }
        }

        MyGroupResult myGroupResult = new MyGroupResult(20000, myGroups);
        return myGroupResult;

    }
}
