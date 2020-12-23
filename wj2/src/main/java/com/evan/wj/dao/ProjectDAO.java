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
    @Query("select new com.evan.wj.bean.AllProject(cor.course_name, pro.name,  pro.id) from PersonClass pc join People p on p.id = pc.person join Class c on c.id = pc.class1 join Course cor on cor.id = c.course join Project pro on pro.course = c.course and pro.teacher_id = p.id where p.stu_id =?1 ")
    List<AllProject> getAllProject_teacher(String token);

    @Query("select new com.evan.wj.bean.AllProject(cor.course_name, pro.name,  pro.id) from WantPerson wp join People p on p.id = wp.person join Project pro on pro.id = wp.project join Course cor on cor.id = pro.course where p.stu_id = ?1")
    List<AllProject> getAllProject_student(String token);



    @Query("select new com.evan.wj.bean.AllProject(cor.course_name, pro.name,  pro.id) from People p join PersonClass pc on pc.person = p.id join Class c on pc.class1 = c.id join Project pro on pro.course = c.course join Course cor on cor.id = pro.course where p.stu_id = ?1 and pro.id not in (select wp.project from WantPerson wp join People p2 on p2.id = wp.person where p2.stu_id = ?1)")
    List<AllProject> getAllProject_student_canjoin(String token);

    @Transactional
    @Modifying
    @Query(value = "insert into want_person (gro_status, person, project, text) values ('未组队',?1,?2,'');", nativeQuery = true)
    void InsertWantPerson(int person_id, int project_id);





}
