package com.group3.basic.netcracker.backend.controller;

import java.time.LocalDate;
import java.util.List;

import com.group3.basic.netcracker.backend.dto.*;
import com.group3.basic.netcracker.backend.service.impl.ReporterServiceImpl;
import com.group3.basic.netcracker.backend.service.CheckAttendanceService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.group3.basic.netcracker.backend.service.AttendanceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class AttendanceController {
    private AttendanceService attendanceService;
    private CheckAttendanceService checkAttendanceService;
    private ReporterServiceImpl reporterServiceImpl;

    @Autowired
    public AttendanceController(AttendanceService attendanceService, CheckAttendanceService checkAttendanceService,
                                ReporterServiceImpl reporterServiceImpl) {
        this.attendanceService = attendanceService;
        this.checkAttendanceService = checkAttendanceService;
        this.reporterServiceImpl = reporterServiceImpl;
    }

    @GetMapping("/attCourse")
    public ResponseEntity<?> getAllCourseAttendance() {
        List<CourseAttendanceDto> list = attendanceService.getAllCourseAttendance();

        log.debug("Got all courses for show attendance");

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/attLesson/{id}")
    public ResponseEntity<?> getLessonsByCourse(@PathVariable int id) {
        List<LessonAttendanceDto> list = attendanceService.getLessonsOfCourseAttendance(id);

        log.debug("Got lessons of course with id - {}", id);

        return ResponseEntity.ok().body(list);
    }


    @GetMapping("/attUser/{id}")
    public ResponseEntity<?> getUsersByLesson(@PathVariable int id) {
        List<UserAttendanceDto> list = attendanceService.getUsersOfCourseAttendance(id);

        log.debug("Got users of lesson with lesson id - {}", id);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/fullAttCheck/{id}")
    public ResponseEntity<?> checkFullAttendance(@PathVariable int id) {
        CheckLessonAttendanceDto checkLessonAttendanceDto = checkAttendanceService.getFullCheckAttendance(id);

        log.debug("Got lesson with id - {} for trainer attendance check", id);

        return ResponseEntity.ok().body(checkLessonAttendanceDto);
    }

    @GetMapping("/getStudentsByLesson/{id}")
    public ResponseEntity<?> getStudentsByLesson(@PathVariable int id) {
        List<UserAttendanceDto> userAttendanceDtoList = checkAttendanceService.getUsersByLessonId(id);

        log.debug("Got users of lesson with id - {} for checking attendance by trainer", id);

        return ResponseEntity.ok().body(userAttendanceDtoList);
    }

    @PostMapping("/lessonAtt")
    public ResponseEntity<?> changeAttendance(@RequestParam(value = "userId") String userId,
                                              @RequestParam("lessonId") String lessonId,
                                              @RequestParam("status") String status) {


        log.debug("User with id = {} is checked as \"{}\" on lesson with lessonId = {}", userId, status, lessonId);

        checkAttendanceService.changeLessonMissing(Integer.parseInt(userId), Integer.parseInt(lessonId), status);
        return new ResponseEntity<>(new ResponseMessage("Present"), HttpStatus.OK);

    }

    @GetMapping("filterCourseByUser/{username}")
    public ResponseEntity<?> getCourseByUser(@PathVariable String username) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByUser(username);

        log.debug("Got courses that include user with username - {}", username);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("filterCourseByTrainerUsername/{username}")
    public ResponseEntity<?> getCourseByTrainerUsername(@PathVariable String username) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByTrainerUsername(username);

        log.debug("Got courses that conduct by trainer with username - {}", username);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("filterCourseBySkillLevel/{level}")
    public ResponseEntity<?> getCourseBySkillLevel(@PathVariable String level) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceBySkillLevel(level);

        log.debug("Got courses with level - {}", level);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/getTrainerLesson/{userName}")
    public ResponseEntity<?> getTrainerTodayLessonsUsername(@PathVariable String userName) {
        List<LessonAttendanceDto> lessonAttendanceDtoList =
                checkAttendanceService.getTodayLessonsByTrainerUsername(userName);

        log.debug("Got lessons for trainer with username - {} for {}", userName, LocalDate.now());

        return ResponseEntity.ok().body(lessonAttendanceDtoList);
    }

    @GetMapping("/getManagerUserAtt/{userName}")
    public ResponseEntity<?> getStudentAttendanceForManagerByUsername(@PathVariable String userName) {
        List<StudentAttendanceForManagerDto> studentAttendanceForManagerDtoList =
                attendanceService.getStudentAttendanceForManagerDto(userName);

        log.debug("Got users with attendance status of manager with username - {}", userName);

        return ResponseEntity.ok().body(studentAttendanceForManagerDtoList);
    }


    @GetMapping("getCourseByUserId/{userId}")
    public ResponseEntity<?> getCourseByUserId(@PathVariable int userId) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByUserId(userId);

        log.debug("Got course that include user with user id - {}", userId);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/getCourseLessonsForOneUser/{courseId}/{userId}")
    public ResponseEntity<?> getLessonsByCourseUser(@PathVariable int courseId, @PathVariable int userId) {
        List<LessonAttendanceDto> list = attendanceService.getLessonsOfCourseAttendanceByUser(courseId, userId);

        log.debug("Got lessons of course with id - {}, that include user with user id - {}");

        return ResponseEntity.ok().body(list);
    }

    @PostMapping("createReport/course")
    public ResponseEntity<?> createReportByCourse(@RequestParam("courses") int[] courses) {
        reporterServiceImpl.createReportByCourse(courses);

        log.debug("Create report with course id = {}", courses);

        return new ResponseEntity<>(new ResponseMessage("Report on selected courses saved"), HttpStatus.OK);
    }

    @PostMapping("createReport/trainer")
    public ResponseEntity<?> createReportByTrainer(@RequestParam("trainerUsername") String username) {
        reporterServiceImpl.createReportByTrainer(username);

        log.debug("Create report by trainer with username = {}", username);

        return new ResponseEntity<>(new ResponseMessage("Report on selected trainer saved"), HttpStatus.OK);
    }

    @PostMapping("createReport/student")
    public ResponseEntity<?> createReportByStudent(@RequestParam("username") String username) {
        reporterServiceImpl.createReportByStudent(username);

        log.debug("Create report by student with username = {}", username);

        return new ResponseEntity<>(new ResponseMessage("Report on selected user saved"), HttpStatus.OK);
    }

    @PostMapping("createReport/level")
    public ResponseEntity<?> createReportByLevel(@RequestParam("level") String level) {
        reporterServiceImpl.createReportByLevel(level);

        log.debug("Create report with level = {}", level);

        return new ResponseEntity<>(new ResponseMessage("Report on selected level saved"), HttpStatus.OK);

    }


}