package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import com.evan.wj.result.Result3;
import com.evan.wj.result.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);

    @Query("select u.roles from User u where u.username =?1")
    List<String> login_test2(String st_id);


    @Query("select new com.evan.wj.result.Token(u.username) from User u where u.username =?1")
    List<Token> login_test1(String st_id);





}
