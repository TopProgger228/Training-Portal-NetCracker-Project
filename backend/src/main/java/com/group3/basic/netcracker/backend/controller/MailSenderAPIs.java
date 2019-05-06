package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.service.UsersTokenService;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.service.impl.UsersTokenServiceImpl;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MailSenderAPIs {

    private ApplicationContext context;

    private final UserService userService;

    private UsersTokenService usersTokenService;

    private JavaMailSender javaMailSender;

    @Autowired
    public MailSenderAPIs(ApplicationContext context, UserService userService, UsersTokenService usersTokenService, JavaMailSender javaMailSender){
        this.context = context;
        this.userService = userService;
        this.usersTokenService = usersTokenService;
        this.javaMailSender = javaMailSender;
    }


    @PostMapping("/signup/send_sign_up_email")
    public ResponseEntity<?> sendSignUpMail(@RequestParam("email") String email) {
        String token = UUID.randomUUID().toString();
        usersTokenService.createTokenForUser(email, token);

        javaMailSender.send(usersTokenService.constructSignUpTokenEmail(getAppUrl(), email, token));
        return new ResponseEntity<>(new ResponseMessage("Mail sent"), HttpStatus.OK);
    }

    @PostMapping("/auth/signup/send_reset_password_email")
    public ResponseEntity<?> sendResetPasswordMail(@RequestParam("email") String email) {
        if (userService.existByEmail(email)) {
            String token = UUID.randomUUID().toString();
            usersTokenService.createTokenForUser(email, token);

            javaMailSender.send(usersTokenService.constructPasswordResetTokenEmail(getAppUrl(), email, token));
            return new ResponseEntity<>(new ResponseMessage("Mail sent"), HttpStatus.OK);
        }else{

            return new ResponseEntity<>(new ResponseMessage("Fail -> User does not exist"),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/auth/signup/getEmailByToken")
    public String getEmailByToken(@RequestParam("token") String token) {
        return usersTokenService.getEmailByToken(token);
    }

    private String getAppUrl(){
        return "http://localhost:4200/";
    }

}
