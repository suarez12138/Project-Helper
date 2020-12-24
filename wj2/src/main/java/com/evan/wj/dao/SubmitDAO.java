package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SubmitDAO extends JpaRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into submission (ano_name, operate_time, person, project, submission_type, text) VALUES (?1,'',?2,?3,2,?4)", nativeQuery = true)
    void teacher_submit_insert(String ano_name,String op_time, int person_id ,int project_id,String text);






//    select sub.id, sub.ano_name ,sub.operate_time, sub.text  from submission sub where sub.submission_type = 2 and sub.person = ?1;
}
