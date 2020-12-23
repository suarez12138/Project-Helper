package com.evan.wj.dao;

import com.evan.wj.bean.DD_Int_String;
import com.evan.wj.bean.DD_Project_GiveBack;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DD_modifiedProjectDAO extends JpaRepository<User,Integer> {

/// give back

    @Query("select new com.evan.wj.bean.DD_Project_GiveBack(p.max_people,p.min_people,p.name,p.grouping_ddl,p.bool_cross_class,p.bool_force_join) from Project p where p.id = ?1")
    List<DD_Project_GiveBack> getGiveBack_main(int project_id);

    @Query("select t.tag from Tag t where t.project = ?1")
    List<String> getGiveBack_tag(int project_id);

    @Query("select cp.pre_week from CheckPoint cp where cp.project = ?1")
    List<String> getGiveBack_preWeek(int project_id);
//////

    @Transactional
    @Modifying
    @Query(value = "update project p set bool_cross_class = ?1, bool_force_join = ?2, grouping_ddl =?3,max_people =?4, min_people = ?5, name = ?6 where p.id = ?7", nativeQuery = true)
    void update_project_main (String cross_class, String force_join,String project_ddl, int max,int min,String name,int project_id);

    @Query("select new com.evan.wj.bean.DD_Int_String(t.id,t.tag) from Tag t where t.project = ?1")
    List<DD_Int_String> get_tags_withID(int project_id);

    @Query("select new com.evan.wj.bean.DD_Int_String(cp.id,cp.pre_week) from CheckPoint cp where cp.project = ?1")
    List<DD_Int_String> get_preWeek_withID(int project_id);


    @Transactional
    @Modifying
    @Query(value = "delete from people_tag pt where pt.tag = ?1", nativeQuery = true)
    void delete_personTag(int tag_id);


    @Transactional
    @Modifying
    @Query(value = "delete from tag t where t.id = ?1", nativeQuery = true)
    void delete_Tag(int tag_id);


    @Transactional
    @Modifying
    @Query(value = "delete from gro g where g.check_point_id = ?1", nativeQuery = true)
    void delete_gro(int checkPoint_id);

    @Transactional
    @Modifying
    @Query(value = "delete from check_point cp where cp.id = ?1", nativeQuery = true)
    void delete_checkPoint(int checkPoint_id);



    @Transactional
    @Modifying
    @Query(value = "insert into tag (project, tag) values (?1,?2)", nativeQuery = true)
    void insert_tag(int project_id, String tag);


    @Transactional
    @Modifying
    @Query(value = "insert into check_point (pre_week, project, text) values (?1,?2,'')", nativeQuery = true)
    void insert_check_point(String pre_week, int project_id);



//    insert into check_point (pre_week, project, text) values (?1,?2,'');
//    insert into tag (project, tag) values (?1,?2);








}
