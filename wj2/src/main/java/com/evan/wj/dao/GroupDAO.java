package com.evan.wj.dao;

import com.evan.wj.bean.*;
import com.evan.wj.pojo.User;
import com.evan.wj.result.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupDAO extends JpaRepository<User,Integer> {

    @Query("select new com.evan.wj.bean.AllGroup(g.id, g.group_name, cp.pre_week, g.group_status) from Gro g join CheckPoint cp on cp.id = g.check_point_id where g.project =?1 order by g.id")
    List<AllGroup> getAllGroup(int project_id);


    @Query("select new com.evan.wj.bean.AllGroup(g.id, g.group_name, cp.pre_week, g.group_status) from Gro g join CheckPoint cp on cp.id = g.check_point_id join Project pro on pro.id = g.project join Class c on pro.course = c.course where g.project =?1 and pro.id = ?1 and c.id in(select c2.id from PersonClass pc2 join People p2 on p2.id = pc2.person join Class c2 on c2.id = pc2.class1 where p2.stu_id = ?2)order by g.id")
    List<AllGroup> getAllGroup2(int project_id,String token);





    @Query("select new com.evan.wj.bean.MyGroup_getGroId(g.id) from Gro g join PersonGroup pg on g.id = pg.gro join People p on p.id = pg.person where p.stu_id =?1 and g.project =?2 order by g.id")
    List<MyGroup_getGroId> getAMyGroup_getGroID(String token,int project_id);

    @Query("select new com.evan.wj.bean.MyGroup_getNameGender(p.id,p.stu_id,p.domitory ,p.name, p.gender) from PersonGroup pg join People p on pg.person = p.id where pg.gro = ?1")
    List<MyGroup_getNameGender> getAMyGroup_getName(int group_id);

    @Query("select new com.evan.wj.bean.MyGroup_idTag(p.id,t.tag) from People p join PeopleTag pg on p.id = pg.person join Tag t on t.id = pg.tag where p.id in (select pg.person from PersonGroup pg where pg.gro =?1) and t.id in (select t2.id from Tag t2 where t2.project = ?2)")
    List<MyGroup_idTag> gettags(int group_id, int project_id);

    @Query("select new com.evan.wj.bean.MyGroup_getGroId(g.id) from Gro g where g.group_name =?1 order by g.id")
    List<MyGroup_getGroId> getAMyGroup_getGroID2(String group_name);

    @Query("select g.project from Gro g where g.id = ?1")
    List<Integer> getProjectId_byGroId(int group_id);






}

