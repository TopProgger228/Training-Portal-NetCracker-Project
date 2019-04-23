package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.course.Course;
import com.group3.basic.netcracker.backend.course.CourseDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CreateCoursesAPIs {

    private ApplicationContext context;

    public CreateCoursesAPIs(ApplicationContext context) {
        this.context = context;
    }

    @PostMapping("/create_new_course")
    public ResponseEntity<?> createNewCourse(@Valid @RequestBody Course course){
        CourseDaoImpl courseDaoImpl = context.getBean(CourseDaoImpl.class);

        courseDaoImpl.createCourse(course.getName(), course.getInfo(), course.getUserId(), course.getSkillLevel(),
                course.getLearnDirection(), LocalDate.of(2019, Month.JUNE, 11),
                LocalDate.of(2019, Month.OCTOBER, 22), 2);

        return new ResponseEntity<>(new ResponseMessage("Course created successfully!"), HttpStatus.OK);
    }
}
