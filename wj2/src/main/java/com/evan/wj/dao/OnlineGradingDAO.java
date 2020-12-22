package com.evan.wj.dao;

import com.evan.wj.bean.Announcement;
import com.evan.wj.bean.OnlineGrade;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OnlineGradingDAO extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.OnlineGrade(p.id, g.id, pg.grade,p.stu_id, p.name,g.group_name, pg.text) from Gro g join PersonGroup pg on g.id = pg.gro join People p on p.id = pg.person where g.project = 1")
    List<OnlineGrade> getAllPerson(int project_id);



    @Transactional
    @Modifying
    @Query(value = "update person_group set grade = ?1 where person = ?2 and gro = ?3", nativeQuery = true)
    void update_grade(double grade, int person_id, int group_id);


    @Transactional
    @Modifying
    @Query(value = "update person_group set text = ?1 where person = ?2 and gro = ?3", nativeQuery = true)
    void update_comment(String comment, int person_id, int group_id);



}

//    int person_id, int group_id, String stu_id, String person_name, String group_name
