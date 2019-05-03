package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;
import org.springframework.mail.SimpleMailMessage;

public interface ResetPasswordService {


    SimpleMailMessage sendMail(String subject, String body, String email);
    void createTokenForUser(final String email, final String token);
    SimpleMailMessage constructTokenEmail(final String contextPath, final String email, final String token);
    String getEmailByToken(String token);

}
