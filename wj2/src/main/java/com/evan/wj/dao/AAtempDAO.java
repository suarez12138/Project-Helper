package com.evan.wj.dao;

import com.evan.wj.bean.Announcement;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface AAtempDAO extends JpaRepository<User,Integer> {
//    @Query("select new com.evan.wj.bean.Announcement() from ")
//    List<Announcement> getAllAnnouncement(String token);


}


//extends JpaRepository<User,Integer>

//    @Query("select new com.evan.wj.bean.Announcement() from ")
//    List<Announcement> getAllAnnouncement(String token);
