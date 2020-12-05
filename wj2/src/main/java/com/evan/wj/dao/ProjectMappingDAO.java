package com.evan.wj.dao;

import com.evan.wj.bean.AllProject;
import com.evan.wj.bean.ProjectMapping;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectMappingDAO extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.ProjectMapping(p.id, p.name, cor.id, cor.course_name) from Project p join Course cor on p.course = cor.id")
    List<ProjectMapping> getMapping();
}
