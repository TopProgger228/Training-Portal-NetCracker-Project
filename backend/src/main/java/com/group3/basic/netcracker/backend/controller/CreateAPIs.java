package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.dto.CourseForm;
import com.group3.basic.netcracker.backend.dto.TimeSlotForm;
import com.group3.basic.netcracker.backend.service.CourseService;
import com.group3.basic.netcracker.backend.service.TimeSlotService;
import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CreateAPIs {

    private CourseService courseService;
    private UserService userService;
    private TimeSlotService timeSlotService;

    @Autowired
    public CreateAPIs(CourseService courseService, UserService userService, TimeSlotService timeSlotService) {
        this.courseService = courseService;
        this.userService = userService;
        this.timeSlotService = timeSlotService;
    }



    @GetMapping("/gettrainers")
    public List getTrainers(){
        return userService.getTrainers();
    }

    @PostMapping("/create_new_course")
    public ResponseEntity<?> createNewCourse( @RequestBody CourseForm courseForm){

        courseService.createCourse(courseForm.getName(),LocalDate.parse(courseForm.getStart_date()),LocalDate.parse(courseForm.getEnd_date()),
                courseForm.getInfo(),courseForm.getSkill_level(),
                courseForm.getUser_id(),courseForm.getQty_per_week());

        return new ResponseEntity<>(new ResponseMessage("Course created successfully!"), HttpStatus.CREATED);
    }

    @GetMapping("/courses-list")
    public List getCourses(){
        return courseService.listCourses();
    }

    @PostMapping("/create_new_timeslot")
    public ResponseEntity<?> createNewTimeSlot(@RequestBody TimeSlotForm timeSlotForm){

        DateTimeFormatter parseFormat = new DateTimeFormatterBuilder().appendPattern("HH:mm").toFormatter();

        timeSlotService.createTimeSlot(LocalTime.parse(timeSlotForm.getStart_time(), parseFormat), LocalTime.parse(timeSlotForm.getEnd_time(), parseFormat),
                timeSlotForm.getWeek_day(), timeSlotForm.getCourse_id());

        return new ResponseEntity<>(new ResponseMessage("Time slot created successfully!"), HttpStatus.CREATED);
    }

}