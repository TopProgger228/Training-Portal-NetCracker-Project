package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.authorization.security.jwt.JwtProvider;
import com.group3.basic.netcracker.backend.coursetable.dao.daoimpl.CourseDaoImpl;
import com.group3.basic.netcracker.backend.timeslot.TimeSlotDaoImplement;
import com.group3.basic.netcracker.backend.usertable.dao.daoimpl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GetInfoAPIs {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    private ApplicationContext context;

    @Autowired
    public GetInfoAPIs(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                        JwtProvider jwtProvider, ApplicationContext context){
        this.authenticationManager = authenticationManager;
        this.encoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.context = context;
    }

    @GetMapping("/usersinfo/trainers")
    public List getTrainers(){
        UserDaoImpl jdbcTemplateUserDao = context.getBean(UserDaoImpl.class);
        return jdbcTemplateUserDao.listUsersForDisplay("Trainer");
    }

    @GetMapping("/usersinfo/managers")
    public List getManagers(){
        UserDaoImpl jdbcTemplateUserDao = context.getBean(UserDaoImpl.class);
        return jdbcTemplateUserDao.listUsersForDisplay("Manager");
    }

    @GetMapping("/usersinfo/students")
    public List getStudents(){
        UserDaoImpl jdbcTemplateUserDao = context.getBean(UserDaoImpl.class);
        return jdbcTemplateUserDao.listUsersForDisplay("Student");
    }

    @GetMapping("/timeslot")
    public List getTimeSlots(){
        TimeSlotDaoImplement timeSlotDaoImplement = context.getBean(TimeSlotDaoImplement.class);
        return timeSlotDaoImplement.listTimeSlots();
    }

    @GetMapping("/courses-list")
    public List getCourses(){
        CourseDaoImpl courseDaoImpl = context.getBean(CourseDaoImpl.class);
        return courseDaoImpl.listCourses();
    }

}
