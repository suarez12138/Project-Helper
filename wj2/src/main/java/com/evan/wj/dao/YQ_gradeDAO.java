package com.evan.wj.dao;

import com.evan.wj.bean.YQ_grade;
import com.evan.wj.bean.YQ_groupID_cnt;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YQ_gradeDAO extends JpaRepository<User,Integer> {

    @Query("select new com.evan.wj.bean.YQ_grade(pro.name,pg.grade,pg.text) from PersonGroup pg join People p on p.id = pg.person join Gro g on g.id = pg.gro join Project pro on pro.id = g.project where p.stu_id = ?1")
    List<YQ_grade> getGrade_YQ(String token);

}

