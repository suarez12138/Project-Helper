package com.evan.wj.service;

public interface MailService {
    public void sendSimpleMail(String title, String content, String email);
    public void sendHtmlMail(String to, String subject, String content);
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

}
