package com.evan.wj.dao;

import com.evan.wj.bean.AllProject;
import com.evan.wj.bean.All_tag_inProject;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public  interface TagDAO extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.All_tag_inProject(t.id ,t.tag) from Tag t where t.project =?1")
    List<All_tag_inProject> get_AllTag_in_project(int stu_id);
}
