package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.GroupStatus;
import com.evan.wj.bean.GroupStatus2;
import com.evan.wj.dao.GroupStatusDao;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GroupStatusController {


    @Autowired
    GroupStatusDao groupStatusDao;

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
}
