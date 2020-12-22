package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PreTimeDAO extends JpaRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into pre_time (check_point_id, limit_group, limit_time, text, the_day, the_time) values (1,2,3,'','','')", nativeQuery = true)
    void Insert_pre_time(int check_point_id, int limit_group, int limit_time, String text, String the_day, String the_time);





}
