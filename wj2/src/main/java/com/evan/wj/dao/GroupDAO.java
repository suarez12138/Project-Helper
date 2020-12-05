package com.evan.wj.dao;

import com.evan.wj.bean.AllGroup;
import com.evan.wj.bean.MyGroup_getGroId;
import com.evan.wj.bean.MyGroup_getNameGender;
import com.evan.wj.bean.MyGroup_idTag;
import com.evan.wj.pojo.User;
import com.evan.wj.result.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupDAO extends JpaRepository<User,Integer> {

    @Query("select new com.evan.wj.bean.AllGroup(g.id, g.group_name, g.pre_time, g.group_status) from Gro g where g.group_status <> 'Graded' and g.project =?1")
    List<AllGroup> getAllGroup(int project_id);



    @Query("select new com.evan.wj.bean.MyGroup_getGroId(g.id) from Gro g join PersonGroup pg on g.id = pg.gro join People p on p.id = pg.person where p.stu_id =?1 and g.project =?2")
    List<MyGroup_getGroId> getAMyGroup_getGroID(String token,int project_id);

    @Query("select new com.evan.wj.bean.MyGroup_getNameGender(p.id, p.name, p.gender) from PersonGroup pg join People p on pg.person = p.id where pg.gro = ?1")
    List<MyGroup_getNameGender> getAMyGroup_getName(int group_id);

    @Query("select new com.evan.wj.bean.MyGroup_idTag(p.id,t.tag) from People p join PeopleTag pg on p.id = pg.person join Tag t on t.id = pg.tag where p.id in (select pg.id from PersonGroup pg where pg.gro =?1)")
    List<MyGroup_idTag> gettags(int group_id);

}

