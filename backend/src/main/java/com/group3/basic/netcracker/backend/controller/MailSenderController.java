package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.service.UsersTokenService;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class MailSenderController {
    private static final String APP_URL = "http://localhost:4200/";

    private final UserService userService;

    private UsersTokenService usersTokenService;

    private JavaMailSender javaMailSender;

    @Autowired
    public MailSenderController(UserService userService, UsersTokenService usersTokenService,
                                JavaMailSender javaMailSender) {
        this.userService = userService;
        this.usersTokenService = usersTokenService;
        this.javaMailSender = javaMailSender;
    }


    @PostMapping("/signup/send_sign_up_email")
    public ResponseEntity<?> sendSignUpMail(@RequestParam("email") String email) {
        String token = UUID.randomUUID().toString();
        usersTokenService.createTokenForUser(email, token);

        javaMailSender.send(usersTokenService.constructSignUpTokenEmail(getAppUrl(), email, token));

        log.info("Mail sent to - {}", email);

        return new ResponseEntity<>(new ResponseMessage("Mail sent"), HttpStatus.OK);
    }

    @PostMapping("/auth/signup/send_reset_password_email")
    public ResponseEntity<?> sendResetPasswordMail(@RequestParam("email") String email) {
        if (userService.existByEmail(email)) {
            String token = UUID.randomUUID().toString();
            usersTokenService.createTokenForUser(email, token);

            javaMailSender.send(usersTokenService.constructPasswordResetTokenEmail(getAppUrl(), email, token));

            log.info("Mail for password recovery sent to - {}", email);

            return new ResponseEntity<>(new ResponseMessage("sent"), HttpStatus.OK);
        } else {
            log.warn("Cannot send email to - {}", email);
            return new ResponseEntity<>(new ResponseMessage("not exist"),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/auth/signup/getEmailByToken")
    public String getEmailByToken(@RequestParam("token") String token) {
        log.debug("Got email by token - {}", token);
        return usersTokenService.getEmailByToken(token);
    }

    private String getAppUrl() {
        return APP_URL;
    }

}
