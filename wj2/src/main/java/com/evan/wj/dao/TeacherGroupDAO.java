package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherGroupDAO extends JpaRepository<User,Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from gro where id = ?1", nativeQuery = true)
    void delete_Group_teacher(int gro_id);

    @Transactional
    @Modifying
    @Query(value = "delete from person_group where gro = ?1", nativeQuery = true)
    void delete_GroupPerson_teacher(int gro_id);

    @Transactional
    @Modifying
    @Query(value = "update want_person wp set wp.gro_status = '未组队' where wp.person in (select pg.person from person_group pg where pg.gro = ?1) and wp.project in (select g2.project from gro g2 where g2.id = ?1)", nativeQuery = true)
    void update_want_person(int gro_id);



}
