package com.evan.wj.dao;

import com.evan.wj.bean.ID_week;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CreateGroupDAO extends JpaRepository<User,Integer> {
        @Query("select new com.evan.wj.bean.ID_week(cp.id, cp.pre_week,cp.text) from CheckPoint cp where cp.project = ?1 ")
        List<ID_week> getAllWeek(int project_id);



        @Transactional
        @Modifying
        @Query(value = "insert into gro (check_point_id, group_name, group_status, index_in_pre_day, pre_day, project, text) values (?1,?2,'可加入',0,0,?3,?4);", nativeQuery = true)
        void insert_gro(int checkPoint, String group_name, int project_id,String text);

        @Transactional
        @Modifying
        @Query(value = "insert into person_group (grade, gro, person, pg_status, text) VALUES (0,?1,?2,'notGraded','');", nativeQuery = true)
        void insert_PersonGro(int group_id, int person_id);

        @Transactional
        @Modifying
        @Query(value = "update want_person set gro_status = '已组队' where person = ?1 and project = ?2", nativeQuery = true)
        void update_wantPerson_ToYiZuDui(int person_id, int project_id);

//        @Transactional
//        @Modifying
//        @Query(value = "update want_person set text = ?1 where person = ?2 and project = ?3", nativeQuery = true)
//        void update_want_person (String hope, int person_id, int project_id);




        @Query("select wp.gro_status from WantPerson wp where wp.person = ?1 and wp.project = ?2 ")
        List<String> getStatus(int person_id, int project_id);


        @Query("select g.group_name from Gro g where g.group_name = ?1 ")
        List<String> getUniqueName(String GroupName);

        @Query("select g.id from Gro g where g.group_name = ?1 ")
        List<Integer> getGroupId(String GroupName);







        // update want people

        // 判斷自己是否在小組裏


//        online grading;
}
