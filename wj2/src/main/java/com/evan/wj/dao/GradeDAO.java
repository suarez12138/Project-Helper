package com.evan.wj.dao;

import com.evan.wj.bean.Grade;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeDAO extends JpaRepository<User,Integer> {
        @Query("select pg.grade from PersonGroup pg join Gro g on g.id = pg.gro where g.project = ?1 ")
        List<Integer> getGrade(int project_id);
}
