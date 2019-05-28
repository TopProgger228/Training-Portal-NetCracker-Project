package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.CourseForTrainerDto;
import com.group3.basic.netcracker.backend.dto.LessonDto;
import com.group3.basic.netcracker.backend.service.CourseService;
import com.group3.basic.netcracker.backend.service.TimeSlotService;
import com.group3.basic.netcracker.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class CourseAPIs {

    private final CourseService courseService;
    private final TimeSlotService timeSlotService;
    private final UserService userService;

    @Autowired
    public CourseAPIs(CourseService courseService, TimeSlotService timeSlotService,
                      UserService userService){
        this.courseService = courseService;
        this.timeSlotService = timeSlotService;
        this.userService = userService;
    }

    @GetMapping("/coursesinfo/getcourses")
    public List getCourses(@RequestParam("username") String username){
        log.info("Received courses of user - {}", username);
        return courseService.listCoursesByUsername(username);
    }

    @GetMapping("/timeslot")
    public List getTimeslotsOfCourse(@RequestParam("name") String name){
        log.debug("Got timeslots of course - {}", name);
        return timeSlotService.getTimeslotsOfCourse(name);
    }

    @GetMapping("/coursesinfo/getcourses/getcoursesattendee")
    public List getCourseStudents(@RequestParam("name") String course){
        log.info("Received students from course - {}", course);
        return userService.getStudentsByCourseName(course);
    }

    @GetMapping("/studentCourses")
    public List getCoursesWithTrainer(@RequestParam("username") String username){
        log.debug("Received courses with trainer of student - {}", username);
        return courseService.getCoursesWithTrainerByUsername(username);
    }

    @GetMapping("/active-courses-list")
    public List getActiveCourses() {
        log.debug("Got active courses!");
        return courseService.listActiveCourses();
    }

    @GetMapping("getCoursesByTrainer/{username}")
    public ResponseEntity<?> getCoursesForTrainerByTrainerUsername(@PathVariable String username) {
        List<CourseForTrainerDto> courseForTrainerDtoList = courseService.getCoursesForTrainerByTrainerUsername(username);

        return ResponseEntity.ok().body(courseForTrainerDtoList);

    }

}
