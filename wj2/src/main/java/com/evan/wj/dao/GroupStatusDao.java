package com.evan.wj.dao;

import com.evan.wj.bean.Announcement;
import com.evan.wj.bean.GroupStatus;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupStatusDao extends JpaRepository<User,Integer> {
        @Query("select new com.evan.wj.bean.GroupStatus( gro.group_status,count(gro.id),pro.min_people, pro.max_people, gro.text) from Gro gro join PersonGroup pg on pg.gro = gro.id join Project pro on pro.id = gro.project where pro.id = ?1 group by gro.id order by gro.id")
        List<GroupStatus> getStatus(int project_id);



}
