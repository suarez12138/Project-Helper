package com.evan.wj.dao;

import com.evan.wj.bean.AllCourse;
import com.evan.wj.bean.Announcement;
import com.evan.wj.bean.The_id;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CreateProjectDAO extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.AllCourse(cor.id,cor.course_name) from PersonClass pc join People p on p.id = pc.person join Class c on c.id = pc.class1 join Course cor on cor.id = c.course where p.stu_id = ?1")
    List<AllCourse> getAllCourse(String token);

    @Transactional
    @Modifying
    @Query(value = "insert into project (bool_cross_class, bool_force_join, course, max_people, min_people, name) VALUES (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void inseart_project(String bool_cross, String bool_force,int course_id ,int max, int min, String course_name);


    @Transactional
    @Modifying
    @Query(value = "insert into want_person (gro_status, person, project, text) values ('未组队',?1,?2,'');", nativeQuery = true)
    void insert_wantPerson(int person_id, int project_id);


    @Query("select new com.evan.wj.bean.The_id(p.id) from PersonClass pc join Class c on c.id = pc.class1 join People p on p.id = pc.person where c.course = ?1 and pc.class_authority = 'student'")
    List<The_id> getAllPeopleInCourse(int course_id);


}
