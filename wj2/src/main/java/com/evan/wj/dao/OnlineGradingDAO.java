package com.evan.wj.dao;

import com.evan.wj.bean.Announcement;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OnlineGradingDAO extends JpaRepository<User,Integer> {
//    @Query("select new com.evan.wj.bean.Announcement() from ")
//    List<Announcement> getAllAnnouncement(String token);
}
