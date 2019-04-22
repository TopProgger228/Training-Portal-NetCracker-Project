package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.usertable.entity.Member;
import com.group3.basic.netcracker.backend.usertable.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("edit")
public class ManagersAndTrainersEditingController {
    private UserService userService;

    @Autowired
    public ManagersAndTrainersEditingController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("addmember")
    public ResponseEntity<?> addMember(@Valid @RequestBody Member member){
        userService.addMember(member.getUsername(), member.getRole(),
                member.getFname(), member.getLname(), member.getEmail(),
                member.getPassword(), LocalDate.now());
        return new ResponseEntity<> (new ResponseMessage("Trainer/manager added!"), HttpStatus.CREATED);
    }
}
