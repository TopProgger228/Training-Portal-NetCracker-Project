package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.service.CourseService;
import com.group3.basic.netcracker.backend.service.ResetPasswordService;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MailSenderAPIs {

    private ApplicationContext context;

    private final UserService userService;

    private ResetPasswordService resetPasswordService;

    private JavaMailSender javaMailSender;

    @Autowired
    public MailSenderAPIs(ApplicationContext context, UserService userService, ResetPasswordService resetPasswordService, JavaMailSender javaMailSender){
        this.context = context;
        this.userService = userService;
        this.resetPasswordService = resetPasswordService;
        this.javaMailSender = javaMailSender;
    }


    @PostMapping("/signup/send_email")
    public ResponseEntity<?> sendSignUpMail(@RequestParam("email") String email) {
        String token = UUID.randomUUID().toString();
        resetPasswordService.createTokenForUser(email, token);

        javaMailSender.send(resetPasswordService.constructTokenEmail(getAppUrl(), email, token));
        return new ResponseEntity<>(new ResponseMessage("Mail sent"), HttpStatus.OK);
    }

    @PostMapping("/signup/getEmailByToken")
    public ResponseEntity<?> getEmailByToken(@RequestParam("token") String token) {
        System.out.println(resetPasswordService.getEmailByToken(token));

        return new ResponseEntity<>(new ResponseMessage("email"), HttpStatus.OK);
    }

    private String getAppUrl(){
        return "http://localhost:4200/";
    }

}
