package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
    private JavaMailSender javaMailSender;

    @Autowired
    public ResetPasswordServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setText("hello_new");
        mail.setSubject("Hello");
        mail.setTo(email);

        javaMailSender.send(mail);
    }
}
