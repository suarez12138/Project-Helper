package com.evan.wj.service;

import com.evan.wj.utils.StringUtils;
import com.evan.wj.bean.GG_AllGroup;
import com.evan.wj.bean.GG_AllPeopleNotGrouped;
import com.evan.wj.bean.GG_AllTag;
import com.evan.wj.bean.GG_MinMaxOfGroup;
import com.evan.wj.dao.CreateGroupDAO;
import com.evan.wj.dao.GroupMethodDAO;
import com.evan.wj.receive.Person_info_update;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupingMethodImpl {
    @Autowired
    GroupMethodDAO groupMethodDAO;

    @Autowired
    CreateGroupDAO createGroupDAO;



    public Void_return Main_method(int project_id){

        List<GG_AllTag> tags = groupMethodDAO.getAllTag(project_id);
        List<GG_MinMaxOfGroup> minMax = groupMethodDAO.getMinMax(project_id);
        int Min = minMax.get(0).getMin();
        int Max = minMax.get(0).getMax();
        List<GG_AllPeopleNotGrouped> candidates = groupMethodDAO.getAllPeopleNotGrouped(project_id);
        List<GG_AllGroup> Groups1 = groupMethodDAO.getAllGroupWithLessPeople(project_id,Min);


//        int[][] A = new int[501][501];  // 权重表
//        int last_gro_id = 0;
//        int gro_id = 0;
//        int count1 = 0;
//        int p1_id = 0;
//        int p2_id = 0;
//
//        for(int i=0;i<Groups1.size();i++){
//            GG_AllGroup g1 = Groups1.get(i);
//            gro_id = g1.getGroup_id();
//            if(gro_id != last_gro_id){
//                if(count1 > 1){
//                    for(int j = i-1;j>i-count1;j--){
//                        for(int k = j-1;k>=i-count1;k--){
//                            p1_id = Groups1.get(j).getPerson_id();
//                            p2_id = Groups1.get(k).getPerson_id();
//                            A[p1_id][p2_id] = 10000;
//                            A[p2_id][p1_id] = 10000;
//                        }
//                    }
//                }
//                // 删除小组
//                groupMethodDAO.deleteFromGro(gro_id);
//                groupMethodDAO.deleteFromPersonGroup(gro_id);
//                count1 = 0;
//            }
//            count1++;
//            if(i == Groups1.size()-1 && count1 >1){
//                for(int j = i-1;j>i-count1;j--){
//                    for(int k = j-1;k>=i-count1;k--){
//                        p1_id = Groups1.get(j).getPerson_id();
//                        p2_id = Groups1.get(k).getPerson_id();
//                        A[p1_id][p2_id] = 10000;
//                        A[p2_id][p1_id] = 10000;
//                    }
//                }
//            }
//            last_gro_id = gro_id;
//            // 已组队 =》未组队
//            groupMethodDAO.update_wantPerson(project_id,g1.getPerson_id());
//            candidates.add(new GG_AllPeopleNotGrouped(g1.getPerson_id(),g1.getClass_id(),g1.getDormitory(),g1.getTag()));
//        }
//
//        List<Integer> checkPoints = groupMethodDAO.getCheckPoint(project_id);
//        int len_check = checkPoints.size();
//        int count2 = 0;
//
//
//        while (true){
//            if(candidates.size()>=Min) {
//                String gro_name2 = StringUtils.getRandomString(10);
//                createGroupDAO.insert_gro(count2,gro_name2 , project_id);
//                gro_id = createGroupDAO.getGroupId(gro_name2).get(0);
//                count2++;
//                count2 %= len_check;
//                for(int i=0;i<Min;i++){
//                    createGroupDAO.insert_PersonGro(gro_id,candidates.get(0).getPerson_id());
//                    candidates.remove(0);
//                }
//            }else {
//                break;
//            }
//        }
//
//        List<GG_AllGroup> Groups2 = groupMethodDAO.getAllGroupWithMinPeople(project_id,Min,Max);

        ArrayList<Group_n1> group_n1 = new ArrayList<>();
        int count_n1 = 0;
        for(int i=0;i< Groups1.size();i++){
            GG_AllGroup g1 = Groups1.get(i);
            Group_mem mem1 = new Group_mem(g1.getDormitory(),g1.getClass_id());

        }

        return new Void_return(20000);
    }
}
class Group_n1{
    int gro_id;
    ArrayList<Group_mem> group_mem;
    Group_n1( ArrayList<Group_mem> group_mem){
        this.group_mem = group_mem;
    }
}
class Group_mem{
    ArrayList<Integer> tags = new ArrayList<>();
    String dormitory;
    int class1;
    Group_mem(String dormitorym, int class1){
        this.dormitory = dormitorym;
        this.class1 = class1;
    }
}
