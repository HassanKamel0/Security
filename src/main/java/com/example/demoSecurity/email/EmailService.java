package com.example.demoSecurity.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{
    private final static Logger LOGGER= LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    @Autowired
    private final SpringTemplateEngine thymeleafTemplateEngine;
    @Override
    @Async
    public void sendHtmlMessage(String to, String subject, String htmlBody) {
        try {
            MimeMessage mimeMessage=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("Security@gmail.com");
            helper.setText(htmlBody,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
                LOGGER.error("Failed to send email",e);
                throw new IllegalStateException("Failed to send email");
        }
    }
    @Override
    public void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel){
        Context thymeleafContext =new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody=thymeleafTemplateEngine.process("thymeleaf.html",thymeleafContext);
        sendHtmlMessage(to,subject,htmlBody);
    }
}
