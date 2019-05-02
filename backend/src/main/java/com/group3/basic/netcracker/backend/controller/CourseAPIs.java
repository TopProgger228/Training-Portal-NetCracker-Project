package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CourseAPIs {

    private final CourseService courseService;

    @Autowired
    public CourseAPIs(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/coursesinfo/getcourses")
    public List getTrainers(@RequestParam("username") String username){
        return courseService.listCoursesByUsername(username);
    }
}
