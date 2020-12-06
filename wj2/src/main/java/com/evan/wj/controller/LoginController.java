package com.evan.wj.controller;

import com.evan.wj.dao.UserDAO;
import com.evan.wj.pojo.User;
import com.evan.wj.result.Result;
import com.evan.wj.result.Result2;
import com.evan.wj.result.Result3;
import com.evan.wj.result.Token;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/user/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
//        System.out.print(username);
        User user = userService.get(username, requestUser.getPassword());
//        System.out.print(user);
//        System.out.print(requestUser.getPassword());
        if (null == user) {
            System.out.print("miao");
            return new Result(400, username);
        } else {
//            System.out.print("miaoaaaaaaaaaaaa");
            return new Result(20000, username);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/vue-element-admin/user/info")
//    @ResponseBody
//    public Result2 getInfo(@RequestBody String st_id) {
//        System.out.print("miaoaaaaaaaaaaaa");
//        System.out.print(userDAO.login_test1(st_id).get(0));
//        return new Result2(20000,userDAO.login_test1(st_id).get(0));
//
//    }
    @ResponseBody
    public Result2 getInfo(@RequestParam("token") String token) {
//        System.out.print("miaoaaaaaaaaaaaa\n");

        List<String> roles = userDAO.login_test2(token);
        List<Token> miao = userDAO.login_test1(token);
        miao.get(0).setRoles(roles);

//        System.out.print(miao);
//        System.out.print("miaoaaaaaaaaaaaa\n");
//        System.out.print(token);
//        System.out.print("miaoaaaaaaaaaaaa\n");
//        System.out.print("miaoaaaaaaaaaaaa\n");
//        System.out.println(userDAO.login_test1(token).get(0).getRole());
//        System.out.println(userDAO.login_test1(token).get(0).getName());
//        System.out.println(userDAO);
        return new Result2(20000, miao.get(0));
    }


    @CrossOrigin
    @PostMapping(value = "/vue-element-admin/user/logout")
    @ResponseBody
    public Result3 logout(@RequestParam("token") String token) {
//        System.out.print("miyyyyyyyyaaaa\n");
        return new Result3(20000);
    }


}
