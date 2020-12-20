package com.evan.wj.dao;

import com.evan.wj.bean.*;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//  String name;
//   String gender;
//    String lab;
//   List<String> skill;
//    String hope;
//    String status;

//select p.id, p.name,p.stu_id ,p.gender,c.class_name, wp.text, wp.gro_status

public interface PersonInfoDAO extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.PersonalInfo_pro(p.id, p.stu_id,p.domitory, p.name,p.gender,c.class_name,wp.text,wp.gro_status)from People p join PersonClass pc on p.id = pc.person join Class c on c.id = pc.class1 join Project pro on pro.course = c.course join WantPerson wp on wp.person = p.id where pro.id = ?1 and wp.project = ?1")
    List<PersonalInfo_pro> getperson(int project_id);


    @Query("select new com.evan.wj.bean.ID_tag(p.id,t.tag) " +
            "from PeopleTag pt " +
            "join People p on pt.person = p.id " +
            "join Tag t on pt.tag = t.id " +
            "where t.project = ?1")
    List<ID_tag> get_idTag2(int project_id);

////////////////////////////////////
    @Query("select p.bool_cross_class from Project p where p.id = ?1")
    List<String> get_CrossClass(int project_id);


    @Query("select c.id from PersonClass pc join People p on pc.person = p.id join Class c on c.id = pc.class1 join Project pro on c.course = pro.course where pro.id = ?1 and p.stu_id = ?2" )
    List<Integer> get_classID(int project_id, String stu_id);


    @Query("select new com.evan.wj.bean.PersonalInfo_pro(p.id, p.stu_id,p.domitory, p.name,p.gender,c.class_name,wp.text,wp.gro_status)from People p join PersonClass pc on p.id = pc.person join Class c on c.id = pc.class1 join Project pro on pro.course = c.course join WantPerson wp on wp.person = p.id where pro.id = ?1 and wp.project = ?1 and p.id in (select pc.person from PersonClass pc where pc.class1 = ?2)")
    List<PersonalInfo_pro> getperson_classCross(int project_id, int classID);


    @Query("select new com.evan.wj.bean.ID_tag(p.id,t.tag) " +
            "from PeopleTag pt " +
            "join People p on pt.person = p.id " +
            "join Tag t on pt.tag = t.id " +
            "where t.project = ?1 and p.id in (select pc.person from PersonClass pc where pc.class1 = ?2)")
    List<ID_tag> get_idTag2_classCross(int project_id, int classID);

/////////////////////////////////////





    @Query("select new com.evan.wj.bean.MyGroup_idTag(p.id,t.tag) from People p join PeopleTag pg on p.id = pg.person join Tag t on t.id = pg.tag where t.project =?1")
    List<MyGroup_idTag> gettags3(int project_id);

}

//    select p.id, p.name,p.stu_id ,p.gender,c.class_name, wp.text, wp.gro_status from people p join person_class pc on p.id = pc.person join class c on c.id = pc.class1 join project pro on pro.course = c.course join want_person wp on wp.person = p.id where pro.id = 1 and wp.project = 1;
