package com.evan.wj.dao;

import com.evan.wj.bean.wky_String_String;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface wky_uploadDAO  extends JpaRepository<User,Integer> {
    //    @Query("select new com.evan.wj.bean.Announcement() from ")
//    List<Announcement> getAllAnnouncement(String token);

    @Transactional
    @Modifying
    @Query(value = "insert into files (file_name, file_path) VALUES (?1,?2)", nativeQuery = true)
    void insert_file(String file_name, String file_path);

//
//    @Query("select new com.evan.wj.bean.wky_String_String() from ")
//    List<wky_String_String> getAllAnnouncement(String token);


}
