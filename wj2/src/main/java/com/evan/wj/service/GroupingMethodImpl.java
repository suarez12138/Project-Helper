package com.evan.wj.service;

import com.evan.wj.bean.GG_AllGroup;
import com.evan.wj.bean.GG_AllPeopleNotGrouped;
import com.evan.wj.bean.GG_AllTag;
import com.evan.wj.bean.GG_MinMaxOfGroup;
import com.evan.wj.dao.GroupMethodDAO;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class GroupingMethodImpl {
    @Autowired
    GroupMethodDAO groupMethodDAO;



    public Void_return Main_method(int project_id){


        List<GG_AllTag> tags = groupMethodDAO.getAllTag(project_id);
        List<GG_MinMaxOfGroup> minMax = groupMethodDAO.getMinMax(project_id);
        int Min = minMax.get(0).getMin();
        int Max = minMax.get(0).getMax();
        List<GG_AllPeopleNotGrouped> candidates = groupMethodDAO.getAllPeopleNotGrouped(project_id);
        List<GG_AllGroup> Groups1 = groupMethodDAO.getAllGroupWithLessPeople(project_id,Min);
        List<GG_AllGroup> Groups2 = groupMethodDAO.getAllGroupWithMinPeople(project_id,Min,Max);

        int[][] A = new int[501][501];


        int last_gro_id = 0;
        int gro_id = 0;
        int count = 0;
        for(GG_AllGroup g1: Groups1){
            gro_id = g1.getGroup_id();
            if(gro_id != last_gro_id){
                // 删除小组
                groupMethodDAO.deleteFromGro(gro_id);
                groupMethodDAO.deleteFromPersonGroup(gro_id);
                count = 0;
            }
            count++;
            last_gro_id = gro_id;

            // 已组队 =》未组队
            groupMethodDAO.update_wantPerson(project_id,g1.getPerson_id());
            candidates.add(new GG_AllPeopleNotGrouped(g1.getPerson_id(),g1.getClass_id(),g1.getDormitory(),g1.getTag()));
        }


        return new Void_return(20000);
    }
}
