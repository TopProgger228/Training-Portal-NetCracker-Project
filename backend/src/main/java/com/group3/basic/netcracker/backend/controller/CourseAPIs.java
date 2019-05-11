package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.service.CourseService;
import com.group3.basic.netcracker.backend.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CourseAPIs {

    private final CourseService courseService;
    private final TimeSlotService timeSlotService;

    @Autowired
    public CourseAPIs(CourseService courseService, TimeSlotService timeSlotService){
        this.courseService = courseService;
        this.timeSlotService = timeSlotService;
    }

    @GetMapping("/coursesinfo/getcourses")
    public List getCourses(@RequestParam("username") String username){

        return courseService.listCoursesByUsername(username);
    }

    @GetMapping("/timeslot")
    public List getTimeslotsOfCourse(@RequestParam("name") String name){
        return timeSlotService.getTimeslotsOfCourse(name);
    }
}
