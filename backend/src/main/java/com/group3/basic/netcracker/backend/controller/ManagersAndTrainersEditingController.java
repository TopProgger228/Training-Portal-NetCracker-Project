package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.Member;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ManagersAndTrainersEditingController {
    private final UserService userService;

    private final PasswordEncoder encoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagersAndTrainersEditingController.class);

    @Autowired
    public ManagersAndTrainersEditingController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("addmember")
    public ResponseEntity<?> addMember(@Valid @RequestBody Member member) {
        LOGGER.debug("Controller get user - {}", member.getUsername());

        if (userService.isUserExists(member.getUsername(), member.getEmail())) {
            LOGGER.debug("User exists in system - {}", member.getUsername());

            return new ResponseEntity<>(new ResponseMessage("User exists!"), HttpStatus.BAD_REQUEST);
        } else {
            userService.addMember(member.getUsername(), member.getRole(),
                    member.getFname(), member.getLname(), member.getEmail(),
                    encoder.encode(member.getPassword()), LocalDate.now());

            if (member.getRole().equals("Trainer")) {
                int id = userService.getId(member.getUsername());
                userService.insertTrainerInfo(id, member.getInfo());
            }

            LOGGER.debug("User created - {}", member.getUsername());

            return new ResponseEntity<>(new ResponseMessage("Trainer/manager added!"), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam int id) {
        userService.removeUser(id);

        LOGGER.debug("User with id: " + id + " is removed from system!");

        return new ResponseEntity<>(new ResponseMessage("Deleted!"), HttpStatus.OK);
    }

    @PostMapping("updateUsername")
    public ResponseEntity<?> updateUserName(@RequestParam int id, @RequestParam String newUserName) {
        userService.updateUserName(id, newUserName);

        LOGGER.debug("User with id: " + id + " got new username: " + newUserName);

        return new ResponseEntity<>(new ResponseMessage("Username updated!"), HttpStatus.OK);
    }

    @PostMapping("updateFirstName")
    public ResponseEntity<?> updateFirstName(@RequestParam int id, @RequestParam String newFirstName) {
        userService.updateUserFirstName(id, newFirstName);

        LOGGER.debug("User with id: " + id + " has changed first name for: " + newFirstName);

        return new ResponseEntity<>(new ResponseMessage("First name updated!"), HttpStatus.OK);
    }
}
