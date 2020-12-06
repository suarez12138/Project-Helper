package com.evan.wj.dao;

import com.evan.wj.bean.Announcement;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AAtempDAO {
    @Query("select new com.evan.wj.bean.Announcement(s.Ano_name, pro2.name, s.operate_time, p2.name, s.text, pro2.id)  from Submission s " +
            "join People p2 on p2.id = s.person " +
            "join Project pro2 on pro2.id = s.project " +
            "where s.project in(select pro.id from People p " +
            "join PersonClass pc on p.id = pc.person " +
            "join Class c on c.id = pc.class1 " +
            "join Project pro on pro.course = c.course " +
            "where p.stu_id =?1)")
    List<Announcement> getAllAnnouncement(String token);
}
