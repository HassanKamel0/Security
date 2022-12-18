package com.example.demoSecurity.email;

import java.util.Map;

public interface EmailSender {
    void sendHtmlMessage(String to, String email, String htmlBody);
    void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String,Object> templateModel);
}
