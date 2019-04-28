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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MailSenderAPIs {

    private ApplicationContext context;

    private final UserService userService;

    private ResetPasswordService resetPasswordService;

    @Autowired
    public MailSenderAPIs(ApplicationContext context, UserService userService, ResetPasswordService resetPasswordService){
        this.context = context;
        this.userService = userService;
        this.resetPasswordService = resetPasswordService;
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<?> sendResetPasswordMail(@RequestParam("email") String email) {

            resetPasswordService.sendMail(email);
            return new ResponseEntity<>(new ResponseMessage("Mail sent"), HttpStatus.OK);
    }
}
