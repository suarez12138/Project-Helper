package com.evan.wj.controller;

import com.evan.wj.result.Void_return;
import com.evan.wj.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
    @Autowired
    private MailServiceImpl mailService;

    @CrossOrigin
    @GetMapping(value = "/m1")
    @ResponseBody
    public Void_return mail_simple(){
        String email = "773794330@qq.com";
        String title = "hello1";
        String content = "hello1123";
        mailService.sendSimpleMail(email,title,content);
        return new Void_return(20000);
    }
    @CrossOrigin
    @GetMapping(value = "/m2")
    @ResponseBody
    public Void_return mail_html(){
        String email = "773794330@qq.com";
        String title = "hello2";
        String content = "<h1>内容：第一封html邮件</h1>";
        mailService.sendHtmlMail(email,title,content);
        return new Void_return(20000);
    }
    @CrossOrigin
    @GetMapping(value = "/m3")
    @ResponseBody
    public Void_return mail_withFile(){
        String send_to = "773794330@qq.com";
        String title = "hello4";
        String content = "hello1123";
        String filePath = "C:\\Users\\wukaiyue\\ooad_vue_workspace\\wj\\src\\main\\java\\com\\evan\\wj\\controller\\p1.png";
        mailService.sendAttachmentsMail(send_to,title,content,filePath);
        return new Void_return(20000);
    }
}
