package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DdlDAO extends JpaRepository<User,Integer> {
        @Query("select p.grouping_ddl from Project p where p.id = ?1")
        List<String> getDdl(int project_id);
}
