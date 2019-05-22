package com.group3.basic.netcracker.backend.service;

import org.springframework.mail.SimpleMailMessage;

public interface UsersTokenService {


    SimpleMailMessage sendMail(String subject, String body, String email);

    void createTokenForUser(final String email, final String token);

    SimpleMailMessage constructSignUpTokenEmail(final String contextPath, final String email, final String token);

    SimpleMailMessage constructPasswordResetTokenEmail(final String contextPath, final String email,
                                                       final String token);

    String getEmailByToken(String token);

}
