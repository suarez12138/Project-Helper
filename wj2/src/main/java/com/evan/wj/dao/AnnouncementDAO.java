package com.evan.wj.dao;

import com.evan.wj.bean.AllProject;
import com.evan.wj.bean.Announcement;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//nativeQuery=true
//String ann_name, String project_name, String release_time, String teacher_name, int project_id


//select sub1.ano_name,sub1.project, p2.name,sub1.text, sub1.operate_time
public interface AnnouncementDAO extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.Announcement(s.Ano_name, p.name, p2.name, s.operate_time, s.text, p.id, s.id) from WantPerson wp join Project p on wp.project = p.id join Submission s on wp.project = s.project join People p2 on p.teacher_id = p2.id where wp.person = ?1 and s.submission_type = '1'")
    List<Announcement> getAllAnnouncement_student(int perosn_id);

    @Query("select new com.evan.wj.bean.Announcement(s.Ano_name, pro.name, p.name, s.operate_time, s.text, pro.id,s.id) from People p join Project pro on pro.teacher_id = p.id join Submission s on s.project = pro.id where p.stu_id = ?1 and s.submission_type = '1'")
    List<Announcement> getAllAnnouncement_teacher(String token);

    @Query("select new com.evan.wj.bean.Announcement(s.Ano_name, p.name, p2.name, s.operate_time, s.text, p.id, s.id) from WantPerson wp join Project p on wp.project = p.id join Submission s on wp.project = s.project join People p2 on p.teacher_id = p2.id where s.id = ?1")
    List<Announcement> getAllAnnouncement_student2(int ann_id);

    @Query("select new com.evan.wj.bean.Announcement(s.Ano_name, pro.name, p.name, s.operate_time, s.text, pro.id,s.id) from People p join Project pro on pro.teacher_id = p.id join Submission s on s.project = pro.id where s.id =?1")
    List<Announcement> getAllAnnouncement_teacher2(int ann_id);


}
