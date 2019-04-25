package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.authorization.security.jwt.JwtProvider;
import com.group3.basic.netcracker.backend.coursetable.courseservice.CourseService;
import com.group3.basic.netcracker.backend.coursetable.dao.daoimpl.CourseDaoImpl;
import com.group3.basic.netcracker.backend.shedule.StudySchedule;
import com.group3.basic.netcracker.backend.shedule.StudyScheduleDao;
import com.group3.basic.netcracker.backend.shedule.StudyScheduleDaoImpl;
import com.group3.basic.netcracker.backend.timeslot.TimeSlotDaoImplement;
import com.group3.basic.netcracker.backend.usertable.dao.daoimpl.UserDaoImpl;
import com.group3.basic.netcracker.backend.usertable.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GetInfoAPIs {

    private ApplicationContext context;

    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public GetInfoAPIs(ApplicationContext context, UserService userService, CourseService courseService){
        this.context = context;
        this.userService = userService;
        this.courseService = courseService;
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

    @GetMapping("/timeslot")
    public List getTimeSlots(){
        TimeSlotDaoImplement timeSlotDaoImplement = context.getBean(TimeSlotDaoImplement.class);
        return timeSlotDaoImplement.listTimeSlots();
    }

    @GetMapping("/courses-list")
    public List getCourses(){
        return courseService.listCourses();
    }

    @PostMapping("/create_new_studySchedule")
    public ResponseEntity<?> createNewStudySchedule(@Valid @RequestBody StudySchedule studySchedule){
        StudyScheduleDao studyScheduleDao = context.getBean(StudyScheduleDaoImpl.class);

        CourseDaoImpl courseDaoImpl = context.getBean(CourseDaoImpl.class);
        UserDaoImpl userDaoImpl = context.getBean(UserDaoImpl.class);
        TimeSlotDaoImplement timeSlotDaoImplement = context.getBean(TimeSlotDaoImplement.class);

        studyScheduleDao.createStudySchedule(studySchedule.getCourseId(), studySchedule.getUserId(), studySchedule.getTimeSlotId(), studySchedule.isChoosen());

        return new ResponseEntity<>(new ResponseMessage("Course created successfully!"), HttpStatus.OK);
    }

}
