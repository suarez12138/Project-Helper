package com.evan.wj.service;

import com.evan.wj.bean.*;
import com.evan.wj.utils.StringUtils;
import com.evan.wj.dao.CreateGroupDAO;
import com.evan.wj.dao.GroupMethod2DAO;
import com.evan.wj.dao.GroupMethodDAO;
import com.evan.wj.result.Message_return;
import com.evan.wj.result.Void_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
public class GroupingMethod2 {
    @Autowired
    GroupMethod2DAO groupMethod2DAO;


    public Message_return Main_method2(int project_id){
        System.out.println(111);
        List<GG_MinMaxOfGroup> minMax = groupMethod2DAO.getMinMax(project_id);
        long Min = (long) minMax.get(0).getMin();
        int Max = minMax.get(0).getMax();

        String canCrossClass = groupMethod2DAO.get_can_cross_class(project_id).get(0);
        Person_m[] Peoples = new Person_m[500];
        ////////////////////////////
        List<GG_personGroup> pg = groupMethod2DAO.getPersonGroup(project_id,Min);
        for(GG_personGroup m: pg){
            Peoples[m.getPerson_id()] =  new Person_m(m.getGroup_id());
        }
        //////////////////////////
        List<Integer> less_groID = groupMethod2DAO.getGroupIdWithLessPeople(project_id,Min);
        for(int g_id:less_groID){
            groupMethod2DAO.update_all_weizudui(g_id,project_id);
            groupMethod2DAO.deleteFromGro(g_id);
            groupMethod2DAO.deleteFromPersonGroup(g_id);

        }
        //////////////////////////
        // candidate {person_id, class_id, dorm, gender}
        List<GG_People2> candidates = groupMethod2DAO.getPeople2(project_id);
        GG_People2 p2 = null;
        int p_id = 0;
        for(int i=0;i<candidates.size();i++){
            p2 = candidates.get(i);
            p_id = p2.getPerson_id();
            if(Peoples[p_id] == null){
                Person_m p1 = new Person_m(p2.getDormitory(),p2.getClass_id(),p_id,-1);
                Peoples[p_id] = p1;
            }else {
                Peoples[p_id].setAll(p2.getDormitory(),p2.getClass_id(),p_id);
            }
        }
        ////////////////////////// 填入tag
        List<GG_PersonIDTag> tags = groupMethod2DAO.getPersonTag(project_id);
        for(GG_PersonIDTag t: tags){
            Peoples[t.getPerson_id()].tagSet.add(t.getTag_id());
        }
        //////////////////
        List<Person_m> Peoples2 = new ArrayList<>();
        for(int i=0;i<Peoples.length;i++){
            if(Peoples[i] !=null){
                Peoples2.add(Peoples[i]);
            }
        }

        List<Integer> cpList = groupMethod2DAO.getCheckPoint(project_id);
        int cp_len = cpList.size();
        Random random = new Random();
        Person_m temp_p = null;
        int score = 0;
        int max_score = -10000000;
        int index_p = 0;
        int new_groID = 0;
        Set<Integer> tagSet = new TreeSet<>();
        Set<Integer> classSet = new TreeSet<>();
        Set<String> dormSet = new TreeSet<>();
        Set<Integer> groupSet = new TreeSet<>();

        while (Peoples2.size() >= Min){
            tagSet.clear();
            classSet.clear();
            dormSet.clear();
            index_p = 0;
            score = 0;
            max_score = -100000000;
            temp_p = Peoples2.get(0);
            Peoples2.remove(0);
            String gro_name = StringUtils.getRandomString(10);
            groupMethod2DAO.insert_gro(cpList.get(random.nextInt(cp_len)),gro_name,project_id);
            new_groID = groupMethod2DAO.getGroupId(gro_name).get(0);
            groupMethod2DAO.insert_PersonGro(new_groID,temp_p.person_id);
            groupMethod2DAO.update_single_yizudui(temp_p.person_id,project_id);
            tagSet.addAll(temp_p.tagSet);
            classSet.add(temp_p.class1);
            dormSet.add(temp_p.dormitory);
            groupSet.add(temp_p.last_group);
            for(int i=0;i<Min-1;i++){
                for (int j=0;j<Peoples2.size();j++) {
                    Set<Integer> tagSet2 = new TreeSet<>(tagSet);
                    Set<Integer> classSet2 = new TreeSet<>(classSet);
                    Set<String> dormSet2 = new TreeSet<>(dormSet);
                    Set<Integer> groupSet2 = new TreeSet<>(groupSet);
                    temp_p = Peoples2.get(j);
                    tagSet2.addAll(temp_p.tagSet);
                    classSet2.add(temp_p.class1);
                    dormSet2.add(temp_p.dormitory);
                    groupSet2.add(temp_p.last_group);
                    score = (tagSet2.size() - tagSet.size()) * 20 - (dormSet2.size()-dormSet.size()) * 5 - (classSet2.size() - classSet.size()) * 10000-(groupSet2.size()-groupSet.size())*200;
                    if (score > max_score){
                        max_score = score;
                        index_p = j;
                    }
                    tagSet = tagSet2;
                    classSet = classSet2;
                    dormSet = dormSet2;
                    groupSet = groupSet2;
                }
                if(canCrossClass.equals("false")){
                    if(max_score < -3000){
                        break;
                    }
                }
                temp_p = Peoples2.get(index_p);
                Peoples2.remove(index_p);
                groupMethod2DAO.insert_PersonGro(new_groID,temp_p.person_id);
                groupMethod2DAO.update_single_yizudui(temp_p.person_id,project_id);
            }

        }
        return new Message_return(20000, "Grouping successfully!");
    }
}

class Person_m{
    Set<Integer> tagSet = new TreeSet<>();
    String dormitory;
    int person_id;
    int class1;
    int last_group;
    Person_m(String dormitory, int class1, int person_id, int last_group){
        this.dormitory = dormitory;
        this.class1 = class1;
        this.person_id = person_id;
        this.last_group = last_group;
    }
    Person_m(int last_group){
        this.last_group = last_group;
    }
    void setAll(String dormitorym, int class1, int person_id){
        this.dormitory = dormitorym;
        this.class1 = class1;
        this.person_id = person_id;
    }


}
