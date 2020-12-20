package com.evan.wj.service;

import com.evan.wj.bean.MyGroup;
import com.evan.wj.bean.MyGroup_getGroId;
import com.evan.wj.bean.MyGroup_getNameGender;
import com.evan.wj.bean.MyGroup_idTag;
import com.evan.wj.dao.GroupDAO;
import com.evan.wj.result.MyGroupResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public MyGroupResult getMyGroup(String token, int project_id) {
        List<MyGroup> myGroups = new ArrayList<MyGroup>();

        List<MyGroup_getGroId> gro_id_list = groupDAO.getAMyGroup_getGroID(token, project_id);
        int gro_id = gro_id_list.get(0).getId();
        List<MyGroup_getNameGender> people_id_list = groupDAO.getAMyGroup_getName(gro_id);

        for(MyGroup_getNameGender ids:people_id_list){
            myGroups.add(new MyGroup(ids.getId(),ids.getStu_id(),ids.getDorm() ,ids.getName(),ids.getGender()));
        }

        List<MyGroup_idTag> tags = groupDAO.gettags(gro_id,project_id);
        // MyGroup_idTag {int person_id, String tag}
        for (MyGroup_idTag tg: tags ){
            int person = tg.getId();

            // MyGroup {int id, String name, String gender, List<String》 tag}
            for (MyGroup p: myGroups){
                if(person == p.getId()){
                    p.addtags(tg.getTag());
                    break;
                }
            }
        }
        MyGroupResult myGroupResult = new MyGroupResult(20000, myGroups.size(),myGroups);
        return myGroupResult;
    }





}
