package com.evan.wj.service;

import com.evan.wj.bean.ID_week;
import com.evan.wj.dao.CreateGroupDAO;
import com.evan.wj.dao.UpPersonInfoDAO;
import com.evan.wj.receive.NewGroupReceive;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.TempleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Create_group_v1_Impl implements Create_group_v1{
    @Autowired
    CreateGroupDAO createGroupDAO;

    @Autowired
    UpPersonInfoDAO upPersonInfoDAO;

    @Override
    public TempleteResult<ID_week> get_weeks(int project_id) {
        List<ID_week> sub1 = createGroupDAO.getAllWeek(project_id);
        TempleteResult<ID_week> allProjectResult_t = new TempleteResult<ID_week>(20000,sub1);
        return allProjectResult_t;
    }

    @Override
    public Message_return create_group(NewGroupReceive rec) {
        List<Integer> team_members = rec.getPerson_id();
        int pro_id = rec.getProject_id() ;
        int the_max = createGroupDAO.getMax(pro_id).get(0);
        if(team_members.size() + 1 > the_max){
            return new Message_return(20000,"Failed! You invite " + team_members.size() + " people, but the max volumn is " + the_max);
        }

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

        createGroupDAO.insert_gro(check_point,group_name,pro_id,rec.getText());
        int gro_id = createGroupDAO.getGroupId(group_name).get(0);

        createGroupDAO.insert_PersonGro(gro_id,my_id);
        createGroupDAO.update_wantPerson_ToYiZuDui(my_id,pro_id);

        for(int p_id: team_members){
            if (p_id!=my_id){
                createGroupDAO.insert_PersonGro(gro_id,p_id);
                createGroupDAO.update_wantPerson_ToYiZuDui(p_id,pro_id);
            }
        }
        return new Message_return(20000,"Success!");
    }
}
