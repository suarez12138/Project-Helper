package com.evan.wj.dao;

import com.evan.wj.bean.*;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PreTimeDAO extends JpaRepository<User,Integer> {

    // teacher
    @Transactional
    @Modifying
    @Query(value = "insert into pre_time (project_id, limit_group, limit_time, text, start_time) values (?1,?2,?3,?4,?5)", nativeQuery = true)
    void Insert_pre_time(int project_id, int limit_group, int limit_time, String text, String start_time);


//    // student
    @Query("select new com.evan.wj.bean.P_AllPreTime(pt.id,pt.start_time) from PreTime pt where pt.project_id= ?1")
    List<P_AllPreTime> get_allPreTime(int project_id);

    @Query("select new com.evan.wj.bean.P_OnePreTime_title( p.name,pt.start_time,pt.text) from PreTime pt join Project p on pt.project_id = p.id where pt.project_id = ?1 and pt.id = ?2")
    List<P_OnePreTime_title> get_OnePreTime_title(int project_id,int preTime_id);


    @Query("select new com.evan.wj.bean.P_groIDName(g.id, g.group_name) from Gro g where g.pre_day = ?1 and g.index_inPreDay= ?2 and g.project = ?3")
    List<P_groIDName> get_groupByindex(int PreTime_id, int index_in_preday,int project_id);

    @Query("select new com.evan.wj.bean.P_OnePreTime_content(pt.limit_group,pt.limit_time,pt.start_time) from PreTime pt where pt.project_id = ?1 and pt.id = ?2")
    List<P_OnePreTime_content> get_OnePreTime_content(int project_id, int preTime_id);

//
//    @Transactional
//    @Modifying
//    @Query(value = "update ", nativeQuery = true)
//    void Insert_pre_time22(int project_id, int limit_group, int limit_time, String text, String start_time);








}
