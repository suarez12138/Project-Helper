package com.evan.wj.controller;

import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.bean.ID_week;
import com.evan.wj.dao.CreateGroupDAO;
import com.evan.wj.dao.TagDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.receive.NewGroupReceive;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
public class CreateGroupController {

    @Autowired
    CreateGroupDAO createGroupDAO;

    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/student/group/get_weeks")
    @ResponseBody
    public TempleteResult<ID_week> getAllGroup(@RequestParam("project_id") int project_id){
        List<ID_week> sub1 = createGroupDAO.getAllWeek(project_id);
        TempleteResult<ID_week> allProjectResult_t = new TempleteResult<ID_week>(20000,sub1);
        return allProjectResult_t;
    }


    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/student/group/create_group")
    @ResponseBody
    public Message_return update_personal_info(@RequestBody NewGroupReceive rec){
        int pro_id = rec.getProject_id();
        String my_stu_id = rec.getSelf_id();
        int my_id = upPersonInfoDAO.getID(my_stu_id).get(0).getId();
        String group_name = rec.getGroup_name();
        int check_point = rec.getCheck_point_id();

        String my_status = createGroupDAO.getStatus(my_id,pro_id).get(0);
        if(my_status.equals("已组队")){
            return new Message_return(20000,"Failed, you already in one group'");
        }
        List<String> temp = createGroupDAO.getUniqueName(group_name);
        if(temp.size() >0){
            return new Message_return(20000,"The group name '"+ group_name + "' already exist");
        }
        createGroupDAO.insert_gro(check_point,group_name,pro_id);
        int gro_id = createGroupDAO.getGroupId(group_name).get(0);
        createGroupDAO.insert_PersonGro(gro_id,my_id);
        createGroupDAO.update_wantPerson(my_id,pro_id);
        List<Integer> team_members = rec.getPerson_id();
        for(int p_id: team_members){
            if (p_id!=my_id){
                createGroupDAO.insert_PersonGro(gro_id,p_id);
                createGroupDAO.update_wantPerson(p_id,pro_id);
            }
        }
        return new Message_return(20000,"Create successfully'");
    }
}
