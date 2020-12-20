package com.evan.wj.dao;

import com.evan.wj.bean.Announcement;
import com.evan.wj.bean.GroupStatus;
import com.evan.wj.bean.GroupStatus2;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupStatusDao extends JpaRepository<User,Integer> {
        @Query("select new com.evan.wj.bean.GroupStatus( gro.group_status,count(gro.id),pro.min_people, pro.max_people, gro.text) from Gro gro join PersonGroup pg on pg.gro = gro.id join Project pro on pro.id = gro.project where pro.id = ?1 group by gro.id order by gro.id")
        List<GroupStatus> getStatus(int project_id);



        @Query("select new com.evan.wj.bean.GroupStatus2(g.id,g.check_point_id ,g.group_name ,cp.pre_week, g.group_status, g.text) from PersonGroup pg join People p on p.id = pg.person join Gro g on g.id = pg.gro join CheckPoint cp on g.check_point_id = cp.id where g.project = ?1 and p.stu_id = ?2")
        List<GroupStatus2> getStatus2(int project_id, String token);

        @Transactional
        @Modifying
        @Query(value = "update gro set group_name = ?1 where gro.id = ?2", nativeQuery = true)
        void update_GroupName (String group_name,int group_id);

        @Transactional
        @Modifying
        @Query(value = "update gro set group_status = ?1 where gro.id = ?2", nativeQuery = true)
        void update_GroupStatus(String group_status,int group_id);

        @Transactional
        @Modifying
        @Query(value = "update gro set text = ?1 where gro.id = ?2", nativeQuery = true)
        void update_text (String g_text,int group_id);

        @Transactional
        @Modifying
        @Query(value = "update gro set check_point_id = ?1 where gro.id = ?2", nativeQuery = true)
        void update_checkPoint (int checkPoint,int group_id);


        @Query("select count(pg.id) from PersonGroup pg where pg.gro = ?1")
        List<Long> getNumberInGroup(int gro_id);


        @Transactional
        @Modifying
        @Query(value = "delete from gro where id = ?1", nativeQuery = true)
        void delete_group (int group_id);

        @Transactional
        @Modifying
        @Query(value = "delete from person_group where person = ?1 and gro = ?2", nativeQuery = true)
        void delete_PersonGroup (int person_id,int group_id);


}

