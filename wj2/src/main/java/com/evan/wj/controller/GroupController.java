package com.evan.wj.controller;

import com.evan.wj.bean.*;
import com.evan.wj.dao.GroupDAO;
import com.evan.wj.dao.PersonInfoDAO;
import com.evan.wj.dao.UserDAO;
import com.evan.wj.result.AllGroupResult;
import com.evan.wj.result.MyGroupResult;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Token;
import com.evan.wj.service.GroupService;
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

    @Autowired
    GroupService groupService;

    @Autowired
    PersonInfoDAO personInfoDAO;

//    @CrossOrigin
//    @GetMapping(value = "/vue-element-admin/teacher/group/group_list")
//    @ResponseBody
//    public AllGroupResult getAllGroup(@RequestParam("project_id") int project_id){
//        AllGroupResult allGroupResult = new AllGroupResult(20000, groupDAO.getAllGroup(project_id));
//        return allGroupResult;
//    }
//


    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/group/group_list")
    @ResponseBody
    public TempleteResult<AllGroup> getAllGroup(@RequestParam("project_id") int project_id){
        List<AllGroup> sub1 = groupDAO.getAllGroup(project_id);
        TempleteResult<AllGroup> allProjectResult_t = new TempleteResult<AllGroup>(20000,sub1);
        return allProjectResult_t;
    }





    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/group_list")
    @ResponseBody
    public TempleteResult<AllGroup> getAllGroup_stu(@RequestParam("token") String token,@RequestParam("project_id") int project_id){

        String cross = personInfoDAO.get_CrossClass(project_id).get(0);
        List<AllGroup> sub1 = null;
        if(cross.equals("true")){
            sub1 = groupDAO.getAllGroup(project_id);

        }else {
            sub1 = groupDAO.getAllGroup2(project_id,token);

        }
        TempleteResult<AllGroup> allProjectResult_t = new TempleteResult<AllGroup>(20000,sub1);
        return allProjectResult_t;
    }


    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/my_group")
    @ResponseBody
    // MyGroupResult
    public MyGroupResult getMyGroup(@RequestParam("token") String token, @RequestParam("project_id") int project_id){
        return groupService.getMyGroup(token,project_id);
    }



    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/the_group")
    @ResponseBody
    // MyGroupResult
    public MyGroupResult getTheGroup(@RequestParam("group_name") String group_name){
        List<MyGroup> myGroups = new ArrayList<MyGroup>();

        // GetGroID {id }
        List<MyGroup_getGroId> gro_id_list = groupDAO.getAMyGroup_getGroID2(group_name);
        int gro_id = gro_id_list.get(0).getId();
        // NameGender {int id, String name, String gender, List<String》 tag}
        List<MyGroup_getNameGender> people_id_list = groupDAO.getAMyGroup_getName(gro_id);

        // MyGroup {int id, String name, String gender, List<String》 tag}
        for(MyGroup_getNameGender ids:people_id_list){
            myGroups.add(new MyGroup(ids.getId(),ids.getStu_id(),ids.getDorm(),ids.getName(),ids.getGender()));
        }

        int project_id = groupDAO.getProjectId_byGroId(gro_id).get(0);
        List<MyGroup_idTag> tags = groupDAO.gettags(gro_id,project_id);
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

        MyGroupResult myGroupResult = new MyGroupResult(20000, myGroups.size(), myGroups);
        return myGroupResult;
    }


    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/teacher/group/the_group")
    @ResponseBody
    // MyGroupResult
    public MyGroupResult getTheGroup_teacher(@RequestParam("group_name") String group_name){
        List<MyGroup> myGroups = new ArrayList<MyGroup>();
        List<MyGroup_getGroId> gro_id_list = groupDAO.getAMyGroup_getGroID2(group_name);
        int gro_id = gro_id_list.get(0).getId();
        List<MyGroup_getNameGender> people_id_list = groupDAO.getAMyGroup_getName(gro_id);
        // MyGroup {int id, String name, String gender, List<String》 tag}
        for(MyGroup_getNameGender ids:people_id_list){
            myGroups.add(new MyGroup(ids.getId(), ids.getStu_id(), ids.getDorm(),ids.getName(),ids.getGender()));
        }
        int project_id = groupDAO.getProjectId_byGroId(gro_id).get(0);
        List<MyGroup_idTag> tags = groupDAO.gettags(gro_id,project_id);
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

        MyGroupResult myGroupResult = new MyGroupResult(20000, myGroups.size() ,myGroups);
        return myGroupResult;
    }


}
