package com.evan.wj.dao;

import com.evan.wj.bean.The_id;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UpPersonInfoDAO extends JpaRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into people_tag (person, tag) values (?1 , ?2)", nativeQuery = true)
    void inseart_person_tag(int person_id, int skill);

    @Transactional
    @Modifying
    @Query(value = "delete from people_tag where person = ?1 and tag in (select t.id from tag t where t.project = ?2)", nativeQuery = true)
    void delete_person_tag(int person_id, int project_id);

    @Transactional
    @Modifying
    @Query(value = "update want_person set text = ?1 where person = ?2 and project = ?3", nativeQuery = true)
    void update_want_person (String hope, int person_id, int project_id);



    @Query("select new com.evan.wj.bean.The_id(p.id) from People p where p.stu_id = ?1")
    List<The_id> getID(String stu_id);
}


