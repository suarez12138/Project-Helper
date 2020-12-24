package com.evan.wj.dao;

import com.evan.wj.bean.AllGroup;
import com.evan.wj.bean.AllProject;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
// String course, String project_name, String the_class, int project_id
public interface ProjectDAO  extends JpaRepository<User,Integer> {

    // this time
    @Query("select new com.evan.wj.bean.AllProject(cor.course_name, pro.name,  pro.id) from Project pro join Course cor on cor.id = pro.course join People p on pro.teacher_id = p.id where p.stu_id = ?1 ")
    List<AllProject> getAllProject_teacher(String token);




    @Query("select new com.evan.wj.bean.AllProject(cor.course_name, pro.name,  pro.id) from WantPerson wp join People p on p.id = wp.person join Project pro on pro.id = wp.project join Course cor on cor.id = pro.course where p.stu_id = ?1")
    List<AllProject> getAllProject_student(String token);



    @Query("select new com.evan.wj.bean.AllProject(cor.course_name, pro.name,  pro.id) from People p join PersonClass pc on pc.person = p.id join Class c on pc.class1 = c.id join Project pro on pro.course = c.course join Course cor on cor.id = pro.course where p.stu_id = ?1 and pro.id not in (select wp.project from WantPerson wp join People p2 on p2.id = wp.person where p2.stu_id = ?1)")
    List<AllProject> getAllProject_student_canjoin(String token);

    @Transactional
    @Modifying
    @Query(value = "insert into want_person (gro_status, person, project, text) values ('未组队',?1,?2,'')", nativeQuery = true)
    void InsertWantPerson(int person_id, int project_id);




    @Transactional
    @Modifying
    @Query(value = "delete from project p where p.id = ?1", nativeQuery = true)
    void delete_project(int project_id);


    @Transactional
    @Modifying
    @Query(value = "delete from want_person wp where wp.project = ?1", nativeQuery = true)
    void delete_want_person(int project_id);

    @Transactional
    @Modifying
    @Query(value = "delete from person_group pg where pg.gro in (select g2.id from gro g2 where g2.project = ?1)", nativeQuery = true)
    void delete_person_group_tea(int project_id);

    @Transactional
    @Modifying
    @Query(value = "delete from gro g where g.project = ?1", nativeQuery = true)
    void delete_Project_gro(int project_id);








}
