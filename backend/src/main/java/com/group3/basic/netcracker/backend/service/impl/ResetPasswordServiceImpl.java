package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.UsersTokenDao;
import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.entity.UsersToken;
import com.group3.basic.netcracker.backend.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
    private UsersTokenDao usersTokenDao;

    @Autowired
    public ResetPasswordServiceImpl( UsersTokenDao usersTokenDao) {
this.usersTokenDao = usersTokenDao;
    }

    @Override
    public SimpleMailMessage sendMail(String subject, String body, String email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setText(body);
        mail.setSubject(subject);
        mail.setTo(email);
        return mail;
    }

    @Override
    public void createTokenForUser(final String email, final String token) {
        final UsersToken myToken = new UsersToken(email, token);
        usersTokenDao.createToken(myToken.getEmail(), myToken.getToken(), myToken.getExpiryDate());
    }

    @Override
    public SimpleMailMessage constructTokenEmail(final String contextPath, final String email, final String token) {
        final String url = contextPath + "/signup?token=" + token;
        final String message = "We glad to invite you to our training portal.\n For sign up click link below.";
        final String bottomMessage = "\n Keep in mind, link live only 24 hours.";
        return sendMail("Reset Password", message + " \r\n" + url + bottomMessage, email);
    }

    @Override
    public String getEmailByToken(String token){
        return usersTokenDao.getEmailByToken(token);
    }


}
