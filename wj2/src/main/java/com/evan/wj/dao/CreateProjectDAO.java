package com.evan.wj.dao;

import com.evan.wj.bean.AllCourse;
import com.evan.wj.bean.Announcement;
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
    @Query(value = "insert into project (bool_cross_class, course, max_people, min_people, name) values (true,1,5,4,'1231212r1')", nativeQuery = true)
    void inseart_person_tag(int person_id, int skill);

}
