package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YQ_groupInfoDAO extends JpaRepository<User,Integer> {

    //    @Query("select new com.evan.wj.bean.Announcement() from ")
//    List<Announcement> getAllAnnouncement(String token);
}
