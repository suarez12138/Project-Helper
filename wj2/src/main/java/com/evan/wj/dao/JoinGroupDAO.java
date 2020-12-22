package com.evan.wj.dao;

import com.evan.wj.bean.OnlineGrade;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JoinGroupDAO extends JpaRepository<User,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into person_group (grade, gro, person, pg_status, text) VALUES (0,?1,?2,'notGraded','');", nativeQuery = true)
    void insert_PersonGro(int group_id, int person_id);

    @Transactional
    @Modifying
    @Query(value = "update want_person set gro_status = '已组队' where person = ?1 and project = ?2", nativeQuery = true)
    void update_wantPerson_ToYiZuDui(int person_id, int project_id);

    @Query("select g.project from Gro g where g.id =?1")
    List<Integer> getProID_ByGroID(int gro_id);

    @Query("select p.id from People p where p.stu_id =?1")
    List<Integer> getPersonID_ByToken(String token);


    @Query("select wp.gro_status from WantPerson wp where wp.person = ?1 and wp.project = ?2")
    List<String> getWantperosnStatus(int person_id,int project_id);


}
