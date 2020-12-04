package com.evan.wj.dao;

import com.evan.wj.bean.AllGroup;
import com.evan.wj.pojo.User;
import com.evan.wj.result.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupDAO extends JpaRepository<User,Integer> {

    @Query("select new com.evan.wj.bean.AllGroup(g.id, g.group_name, g.pre_time, g.group_status) from Gro g where g.group_status <> 'Graded' and g.project =?1")
    List<AllGroup> getAllGroup(String group_id);

}

