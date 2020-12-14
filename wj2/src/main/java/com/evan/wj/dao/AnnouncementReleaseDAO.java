package com.evan.wj.dao;

import com.evan.wj.bean.ClassName;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnnouncementReleaseDAO extends JpaRepository<User,Integer> {

        @Query("select new com.evan.wj.bean.ClassName(c.id,c.class_name)from Project pro join Class c on pro.course = c.course where pro.id = ?1")
        List<ClassName> getAllClassName(int project_id);

        @Query("select p.stu_id from People p where p.id in(select wp.person from WantPerson wp where wp.project = ?1)")
        List<String> getStuidInProject(int project_id);



        @Transactional
        @Modifying
        @Query(value = " insert into submission (ano_name, operate_time, person, project, submission_type, text) VALUES (?1,?2,?3,?4,1,?5);", nativeQuery = true)
        void insert_submission_ann(String ano_name,String oprate_time, int teacher_id, int project_id, String text);


}
