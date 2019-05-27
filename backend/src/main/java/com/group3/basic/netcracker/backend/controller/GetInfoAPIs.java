package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.service.CourseService;
import com.group3.basic.netcracker.backend.service.ScheduleService;
import com.group3.basic.netcracker.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GetInfoAPIs {

    private final UserService userService;
    private final CourseService courseService;
    private final ScheduleService scheduleService;

    @Autowired
    public GetInfoAPIs(UserService userService, CourseService courseService,
                       ScheduleService scheduleService) {
        this.userService = userService;
        this.courseService = courseService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/usersinfo/trainers")
    public List getTrainers() {
        return userService.listUsersForDisplay("Trainer");
    }

    @GetMapping("/usersinfo/managers")
    public List getManagers() {
        return userService.listUsersForDisplay("Manager");
    }

    @GetMapping("/usersinfo/students")
    public List getStudents() {
        return userService.listUsersForDisplay("Student");
    }


    @GetMapping("/manager/students-info")
    public List getStudentsOfManager(@RequestParam("username") String username) {
        return userService.getStudentsOfManager(username);
    }

    @GetMapping("/manager/profile")
    public UserForDisplay getManagersInfo(@RequestParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/manager/student-profile")
    public UserForDisplay getStudent(@RequestParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/student/course-page")
    public Course getCourse(@RequestParam("name") String name) {
        return courseService.getCourseByName(name);
    }

    @GetMapping("/student_id")
    public Integer getIdByUsername(@RequestParam("username") String username) {
        return userService.getIdByUsername(username);
    }

    @GetMapping("/scheduleinfo")
    public List listScheduleWithCourseAndTimeSlotAndUser() {
        return scheduleService.listScheduleWithCourseAndTimeSlotAndUser();
    }

    @GetMapping("/course_id")
    public Integer getIdByCourseName(@RequestParam("name") String name) {
        return courseService.getIdByCourseName(name);
    }

    @GetMapping("/is_choosen")
    public void scheduleGenerate(@RequestParam("id") Integer id) {
        scheduleService.generateSchedule(id);
    }

    @GetMapping("/student/my-schedule")
    public List listScheduleOfStudent(@RequestParam("username") String username){
        return scheduleService.getScheduleOfStudent(username);
    }
}
