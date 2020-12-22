package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface A_createUserDAO extends JpaRepository<User,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into user (password, person, roles, username) values (?1,?2,?3,?4)", nativeQuery = true)
    void Insert_user (String password, int person_id,String role, String stu_id);

}
