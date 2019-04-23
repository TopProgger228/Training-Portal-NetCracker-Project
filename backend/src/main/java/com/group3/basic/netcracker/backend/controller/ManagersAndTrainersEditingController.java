package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.usertable.entity.Member;
import com.group3.basic.netcracker.backend.usertable.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        userService.removeUser(id);
        return new ResponseEntity<>(new ResponseMessage("Deleted!"), HttpStatus.OK);
    }

    @PostMapping("updateUsername")
    public ResponseEntity<?> updateUserName(@RequestParam int id, @RequestParam String newUserName){
        userService.updateUserName(id, newUserName);
        return new ResponseEntity<>(new ResponseMessage("Username updated!"), HttpStatus.OK);
    }

    @PostMapping("updateFirstName")
    public ResponseEntity<?> updateFirstName(@RequestParam int id, @RequestParam String newFirstName){
        userService.updateUserFirstName(id, newFirstName);
        return new ResponseEntity<>(new ResponseMessage("First name updated!"), HttpStatus.OK);
    }
}
