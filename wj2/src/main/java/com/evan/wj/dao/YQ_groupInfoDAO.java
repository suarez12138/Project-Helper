package com.evan.wj.dao;

import com.evan.wj.bean.YQ_GroupInfo;
import com.evan.wj.bean.YQ_groupID_cnt;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YQ_groupInfoDAO extends JpaRepository<User,Integer> {

    @Query("select p.course from Project p where p.id = ?1")
    List<Integer> getCourseID(int project_id);

    @Query("select new com.evan.wj.bean.YQ_groupID_cnt(g.id, count(g.id)) from Gro g join PersonGroup pg on g.id = pg.gro where g.project = 1 group by g.id")
    List<YQ_groupID_cnt> getGroupID_cnt(int project_id);

//    String stu_id, String person_name, String lab_name, String group_name, String gro_text, String group_status

    @Query("select new com.evan.wj.bean.YQ_GroupInfo(g.id,p.stu_id,p.name,c.class_name,p.domitory,g.group_name,g.text,g.group_status) from Gro g join PersonGroup pg on pg.gro = g.id join People p on pg.person = p.id join PersonClass pc on pc.person = p.id join Class c on pc.class1 = c.id where g.project = ?1 and c.course = ?1")
    List<YQ_GroupInfo> get_mostData(int project_id, int course_id);

}
