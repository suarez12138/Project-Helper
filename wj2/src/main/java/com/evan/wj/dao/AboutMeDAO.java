package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AboutMeDAO extends JpaRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query(value = "update user set password = ?1 where person = ?2", nativeQuery = true)
    void update_password (String password, int person_id);

    @Transactional
    @Modifying
    @Query(value = "update people set gender = ?1 where id = ?2", nativeQuery = true)
    void update_gender (String gender, int person_id);

    @Transactional
    @Modifying
    @Query(value = "update people set domitory = ?1 where id = ?2", nativeQuery = true)
    void update_dorm (String dorm, int person_id);

    @Transactional
    @Modifying
    @Query(value = "update people set self_introduction = ?1 where id = ?2", nativeQuery = true)
    void update_self_introduction (String introduction, int person_id);

}
