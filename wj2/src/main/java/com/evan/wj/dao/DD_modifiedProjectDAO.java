package com.evan.wj.dao;

import com.evan.wj.bean.DD_Project_GiveBack;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DD_modifiedProjectDAO extends JpaRepository<User,Integer> {

//    int max, int min, String project_name, String project_ddl, String across_lab, String force_join

    @Query("select new com.evan.wj.bean.DD_Project_GiveBack(p.max_people,p.min_people,p.name,p.grouping_ddl,p.bool_cross_class,p.bool_force_join) from Project p where p.id = ?1")
    List<DD_Project_GiveBack> getGiveBack_main(int project_id);

    @Query("select t.tag from Tag t where t.project = ?1")
    List<String> getGiveBack_tag(int project_id);

    @Query("select cp.pre_week from CheckPoint cp where cp.project = ?1")
    List<String> getGiveBack_preWeek(int project_id);

}
