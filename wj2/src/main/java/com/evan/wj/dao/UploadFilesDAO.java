package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UploadFilesDAO extends JpaRepository<User,Integer> {
    @Transactional
    @Modifying
    @Query(value = " insert into submission (ano_name, operate_time, person, project, submission_type, text) VALUES (?1,?2,?3,?4,2,?5);", nativeQuery = true)
    void insert_submission_Assign_tea(String ano_name,String oprate_time, int teacher_id, int project_id, String text);





}
