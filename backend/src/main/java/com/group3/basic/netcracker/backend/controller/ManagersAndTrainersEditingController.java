package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.Member;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("edit")
@Slf4j
public class ManagersAndTrainersEditingController {
    private final UserService userService;

    private final PasswordEncoder encoder;

    @Autowired
    public ManagersAndTrainersEditingController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("addmember")
    public ResponseEntity<?> addMember(@Valid @RequestBody Member member) {
        log.info("Controller get user - {}", member.getUsername(), member.getFname(), member.getFname());

        if (userService.isUserExists(member.getUsername(), member.getEmail())) {
            log.warn("User with username exists in system - {}", member.getUsername());

            return new ResponseEntity<>(new ResponseMessage("User exists!"), HttpStatus.BAD_REQUEST);
        } else {
            userService.addMember(member.getUsername(), member.getRole(),
                    member.getFname(), member.getLname(), member.getEmail(),
                    encoder.encode(member.getPassword()), LocalDate.now());

            if (member.getRole().equals("Trainer")) {
                int id = userService.getId(member.getUsername());
                userService.insertTrainerInfo(id, member.getInfo());
            }

            log.info("User created with username - {}", member.getUsername());

            return new ResponseEntity<>(new ResponseMessage("Trainer/manager added!"), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam int id) {
        userService.removeUser(id);

        log.info("User wit id deleted - {}", id);

        return new ResponseEntity<>(new ResponseMessage("Deleted!"), HttpStatus.OK);
    }

    @PostMapping("updateUsername")
    public ResponseEntity<?> updateUserName(@RequestParam int id, @RequestParam String newUserName) {
        userService.updateUserName(id, newUserName);

        log.info("User with id got new username - {}", id, newUserName);

        return new ResponseEntity<>(new ResponseMessage("Username updated!"), HttpStatus.OK);
    }

    @PostMapping("updateFirstName")
    public ResponseEntity<?> updateFirstName(@RequestParam int id, @RequestParam String newFirstName) {
        userService.updateUserFirstName(id, newFirstName);

        log.info("User with id has updated first name - {}", id, newFirstName);

        return new ResponseEntity<>(new ResponseMessage("First name updated!"), HttpStatus.OK);
    }
}
