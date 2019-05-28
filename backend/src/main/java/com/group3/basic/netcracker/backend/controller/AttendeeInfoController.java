package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("attendees")
@Slf4j
public class AttendeeInfoController {
    private final UserService service;

    @Autowired
    public AttendeeInfoController(UserService service) {
        this.service = service;
    }

    @GetMapping("info")
    public List getStudentsInfo(@RequestParam("username") String username) {
        log.debug("Received students of trainer - {}", username);
        return service.getStudentsOfTrainer(username);
    }

}
