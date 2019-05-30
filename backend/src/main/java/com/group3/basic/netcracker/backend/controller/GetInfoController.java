package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.service.CourseService;
import com.group3.basic.netcracker.backend.service.ScheduleService;
import com.group3.basic.netcracker.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class GetInfoController {
    private static final String TRAINER_ROLE = "Trainer";
    private static final String MANAGER_ROLE = "Manager";
    private static final String STUDENT_ROLE = "Student";

    private final UserService userService;
    private final CourseService courseService;
    private final ScheduleService scheduleService;

    @Autowired
    public GetInfoController(UserService userService, CourseService courseService,
                             ScheduleService scheduleService) {
        this.userService = userService;
        this.courseService = courseService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/usersinfo/trainers")
    public List getTrainers() {
        log.debug("List of trainers received!");
        return userService.listUsersForDisplay(TRAINER_ROLE);
    }

    @GetMapping("/usersinfo/managers")
    public List getManagers() {
        log.debug("List of managers received!");
        return userService.listUsersForDisplay(MANAGER_ROLE);
    }

    @GetMapping("/usersinfo/students")
    public List getStudents() {
        log.debug("List of students received!");
        return userService.listUsersForDisplay(STUDENT_ROLE);
    }


    @GetMapping("/manager/students-info")
    public List getStudentsOfManager(@RequestParam("username") String username) {
        log.info("Students of trainer - {} received", username);
        return userService.getStudentsOfManager(username);
    }

    @GetMapping("/manager/profile")
    public UserForDisplay getManagersInfo(@RequestParam("username") String username) {
        log.debug("Displayed info of manager - {}", username);
        return userService.getUserByUsername(username);
    }

    @GetMapping("/manager/student-profile")
    public UserForDisplay getStudent(@RequestParam("username") String username) {
        log.debug("Got student - {}", username);
        return userService.getUserByUsername(username);
    }

    @GetMapping("/student/course-page")
    public Course getCourse(@RequestParam("name") String name) {
        log.debug("Got course - {}", name);
        return courseService.getCourseByName(name);
    }

    @GetMapping("/student_id")
    public Integer getIdByUsername(@RequestParam("username") String username) {
        log.debug("Got id by username - {}", username);
        return userService.getIdByUsername(username);
    }

    @GetMapping("/scheduleinfo")
    public List listScheduleWithCourseAndTimeSlotAndUser() {
        log.debug("Got schedule list");
        return scheduleService.listScheduleWithCourseAndTimeSlotAndUser();
    }

    @GetMapping("/course_id")
    public Integer getIdByCourseName(@RequestParam("name") String name) {
        log.debug("Got id by course - {}", name);
        return courseService.getIdByCourseName(name);
    }

    @GetMapping("/is_choosen")
    public void scheduleGenerate(@RequestParam("id") Integer id) {
        log.debug("Schedule generated for id - {}", id);
        scheduleService.generateSchedule(id);
        scheduleService.generateLesson(id);
    }
/*
    @GetMapping("/create_lesson")
    public void scheduleLesson(@RequestParam("id") Integer id) {
        log.debug("Schedule generated for id - {}", id);
        scheduleService.generateLesson(id);
    }
*/
    @GetMapping("/student/my-schedule")
    public List listScheduleOfStudent(@RequestParam("username") String username){
        log.debug("Got schedule of student - {}", username);
        return scheduleService.getScheduleOfStudent(username);
    }
}
