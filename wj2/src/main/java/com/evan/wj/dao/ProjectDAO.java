package com.evan.wj.dao;

import com.evan.wj.bean.AllGroup;
import com.evan.wj.bean.AllProject;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectDAO  extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.AllProject(g.id, g.group_name, g.pre_time, g.group_status) from PersonClass pc join People p on p.id = pc.person join Class c on c.id = pc.class1 join Course cor on cor.id = c.course join Project pro on pro.course = c.course where p.stu_id = '11812715' and pc.pc_status <> 'learnt'")
    List<AllProject> getAllProject(String token);

}
