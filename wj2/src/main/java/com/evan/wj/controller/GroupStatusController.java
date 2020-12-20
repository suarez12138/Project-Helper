package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.GroupStatus;
import com.evan.wj.bean.GroupStatus2;
import com.evan.wj.dao.GroupStatusDao;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupStatusController {


    @Autowired
    GroupStatusDao groupStatusDao;

    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/group_list_state")
    @ResponseBody
    public TempleteResult<GroupStatus> getAllGroup(@RequestParam("project_id") int project_id){

        List<GroupStatus> sub1 = groupStatusDao.getStatus(project_id);
        int min = 0;
        int max = 0;
        long num = 0;
        for(GroupStatus g: sub1){
            min = g.getMin_people();
            max = g.getMax_people();
            num = g.getPeople_number();
            if(max >= num && min <=num){
                g.setIs_valid("T");
            }else {
                g.setIs_valid("F");
            }
        }
        TempleteResult<GroupStatus> allProjectResult_t = new TempleteResult<GroupStatus>(20000,sub1);
        return allProjectResult_t;
    }


    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/my_group_state")
    @ResponseBody
    public TempleteResult<GroupStatus2> getMyGroupStatus(@RequestParam("token") String token,@RequestParam("project_id") int project_id){

        List<GroupStatus2> sub1 = groupStatusDao.getStatus2(project_id,token);

        TempleteResult<GroupStatus2> allProjectResult_t = new TempleteResult<GroupStatus2>(20000,sub1);
        return allProjectResult_t;
    }



    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/student/group/update_group_state")
    @ResponseBody
    public Void_return UpdateMyGroupStatus2(@RequestParam("gro_id") int gro_id,
                                            @RequestParam("gro_status") String status,
                                            @RequestParam("text") String text,
                                            @RequestParam("check_point_id") int cp,
                                            @RequestParam("gro_name") String gro_name)
    {
        groupStatusDao.update_checkPoint(cp,gro_id);
        groupStatusDao.update_GroupName(gro_name,gro_id);
        groupStatusDao.update_GroupStatus(status,gro_id);
        groupStatusDao.update_text(text,gro_id);
        return new Void_return(20000);
    }


    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/student/group/drop_group")
    @ResponseBody
    public Void_return quitTheGroup(@RequestParam("token") String token,@RequestParam("gro_id") int gro_id) {

        long people_number = groupStatusDao.getNumberInGroup(gro_id).get(0);
        int person_id = upPersonInfoDAO.getID(token).get(0).getId();
        if(people_number == 1){
            groupStatusDao.delete_PersonGroup(person_id,gro_id);
            groupStatusDao.delete_group(gro_id);
        }else {
            groupStatusDao.delete_PersonGroup(person_id,gro_id);
        }
        return new Void_return(20000);
    }



}
