package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dao.ScheduleDao;
import com.group3.basic.netcracker.backend.dao.impl.ScheduleDaoImpl;
import com.group3.basic.netcracker.backend.dao.impl.TimeSlotDaoImpl;
import com.group3.basic.netcracker.backend.entity.Schedule;
import com.group3.basic.netcracker.backend.service.CourseService;
import com.group3.basic.netcracker.backend.service.TimeSlotService;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GetInfoAPIs {

    private ApplicationContext context;

    private final UserService userService;

    @Autowired
    public GetInfoAPIs(ApplicationContext context, UserService userService){
        this.context = context;
        this.userService = userService;
    }

    @GetMapping("/usersinfo/trainers")
    public List getTrainers(){
        return userService.listUsersForDisplay("Trainer");
    }

    @GetMapping("/usersinfo/managers")
    public List getManagers(){
        return userService.listUsersForDisplay("Manager");
    }

    @GetMapping("/usersinfo/students")
    public List getStudents(){
        return userService.listUsersForDisplay("Student");
    }


    @GetMapping("/manager/students-info")
    public List getStudentsOfManager(@RequestParam("username") String username){
        return userService.getStudentsOfManager(username);
    }
}
