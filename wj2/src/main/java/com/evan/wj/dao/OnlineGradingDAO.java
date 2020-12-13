package com.evan.wj.dao;

import com.evan.wj.bean.Announcement;
import com.evan.wj.bean.OnlineGrade;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OnlineGradingDAO extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.OnlineGrade(p.id, g.id, p.stu_id, p.name,g.group_name) from Gro g join PersonGroup pg on g.id = pg.gro join People p on p.id = pg.person where g.project = 1")
    List<OnlineGrade> getAllPerson(int project_id);
}

//    int person_id, int group_id, String stu_id, String person_name, String group_name
