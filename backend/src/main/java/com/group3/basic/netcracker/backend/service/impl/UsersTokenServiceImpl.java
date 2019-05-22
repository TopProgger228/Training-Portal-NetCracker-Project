package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.UsersTokenDao;
import com.group3.basic.netcracker.backend.dao.impl.UsersTokenDaoImpl;
import com.group3.basic.netcracker.backend.entity.UsersToken;
import com.group3.basic.netcracker.backend.service.UsersTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class UsersTokenServiceImpl implements UsersTokenService {
    private UsersTokenDao usersTokenDao;

    @Autowired
    public UsersTokenServiceImpl(UsersTokenDao usersTokenDao) {
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


    public void createTokenForUser(final String email, final String token) {
        final UsersToken myToken = new UsersToken(email, token);
        int existTokenId = usersTokenDao.getIdByEmail(email);
        if (existTokenId > 0) {
            usersTokenDao.replaceTokenById(existTokenId, myToken.getToken(), myToken.getExpiryDate());
        } else {
            usersTokenDao.createToken(myToken.getEmail(), myToken.getToken(), myToken.getExpiryDate());
        }
    }

    @Override
    public SimpleMailMessage constructSignUpTokenEmail(final String contextPath, final String email,
                                                       final String token) {
        final String url = contextPath + "/signup?token=" + token;
        final String message = "We glad to invite you to our training portal.\n For sign up click link below.";
        final String bottomMessage = "\n Keep in mind, link live only 24 hours.";
        return sendMail("Sign up", message + " \r\n" + url + bottomMessage, email);
    }

    @Override
    public SimpleMailMessage constructPasswordResetTokenEmail(final String contextPath, final String email,
                                                              final String token) {
        final String url = contextPath + "/password_reset?token=" + token;
        final String signInUrl = contextPath + "auth/login";
        final String message = "This is link for reset your password:";
        final String bottomMessage = "\n Keep in mind, link live only 24 hours.\n If you remember your " +
                "password try to sign in:";
        return sendMail("Reset Password", message + " \r\n" + url + bottomMessage + " \r\n" +
                signInUrl, email);
    }

    @Override
    public String getEmailByToken(String token) {
        return usersTokenDao.getEmailByToken(token);
    }


}
