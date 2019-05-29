package com.group3.basic.netcracker.backend.controller;

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
public class AttendanceAPI {
    private AttendanceService attendanceService;
    private CheckAttendanceService checkAttendanceService;
    private ReporterServiceImpl reporterServiceImpl;

    @Autowired
    public AttendanceAPI(AttendanceService attendanceService, CheckAttendanceService checkAttendanceService,
                         ReporterServiceImpl reporterServiceImpl) {
        this.attendanceService = attendanceService;
        this.checkAttendanceService = checkAttendanceService;
        this.reporterServiceImpl = reporterServiceImpl;
    }

    @GetMapping("/attCourse")
    public ResponseEntity<?> getAllCourseAttendance() {
        List<CourseAttendanceDto> list = attendanceService.getAllCourseAttendance();

        log.debug("Got all courses attendance");

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

        log.debug("Got users of lesson");

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/fullAttCheck/{id}")
    public ResponseEntity<?> checkFullAttendance(@PathVariable int id) {
        CheckLessonAttendanceDto checkLessonAttendanceDto = checkAttendanceService.getFullCheckAttendance(id);

        return ResponseEntity.ok().body(checkLessonAttendanceDto);
    }

    @GetMapping("/getStudentsByLesson/{id}")
    public ResponseEntity<?> getStudentsByLesson(@PathVariable int id) {
        List<UserAttendanceDto> userAttendanceDtoList = checkAttendanceService.getUsersByLessonId(id);

        return ResponseEntity.ok().body(userAttendanceDtoList);
    }

    @GetMapping("/trainerLesson/{id}")
    public ResponseEntity<?> getTrainerTodayLessons(@PathVariable int id) {
        List<LessonAttendanceDto> lessonAttendanceDtoList = checkAttendanceService.getTodayLessonsByTrainer(id);

        return ResponseEntity.ok().body(lessonAttendanceDtoList);
    }

    @PostMapping("/lessonAtt")
    public ResponseEntity<?> changeAttendance(@RequestParam("userId") String userId,
                                              @RequestParam("lessonId") String lessonId,
                                              @RequestParam("status") String status) {

        checkAttendanceService.changeLessonMissing(Integer.parseInt(userId), Integer.parseInt(lessonId), status);
        return new ResponseEntity<>(new ResponseMessage("Present"), HttpStatus.OK);

    }

    @GetMapping("getAllTrainer")
    public ResponseEntity<?> getAllTrainer() {
        List<TrainerSelectorDto> list = attendanceService.getTrainerForSelector();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("filterCourseByUser/{username}")
    public ResponseEntity<?> getCourseByUser(@PathVariable String username) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByUser(username);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("filterCourseByTrainerUsername/{username}")
    public ResponseEntity<?> getCourseByTrainerUsername(@PathVariable String username) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByTrainerUsername(username);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("filterCourseBySkillLevel/{level}")
    public ResponseEntity<?> getCourseBySkillLevel(@PathVariable String level) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceBySkillLevel(level);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/getTrainerLesson/{userName}")
    public ResponseEntity<?> getTrainerTodayLessonsUsername(@PathVariable String userName) {
        List<LessonAttendanceDto> lessonAttendanceDtoList =
                checkAttendanceService.getTodayLessonsByTrainerUsername(userName);

        return ResponseEntity.ok().body(lessonAttendanceDtoList);
    }

    @GetMapping("/getManagerUserAtt/{userName}")
    public ResponseEntity<?> getStudentAttendanceForManagerByUsername(@PathVariable String userName) {
        List<StudentAttendanceForManagerDto> studentAttendanceForManagerDtoList =
                attendanceService.getStudentAttendanceForManagerDto(userName);

        return ResponseEntity.ok().body(studentAttendanceForManagerDtoList);
    }


    @GetMapping("getCourseByUserId/{userId}")
    public ResponseEntity<?> getCourseByUserId(@PathVariable int userId) {
        List<CourseAttendanceDto> list = attendanceService.getCourseAttendanceByUserId(userId);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/getCourseLessonsForOneUser/{courseId}/{userId}")
    public ResponseEntity<?> getLessonsByCourseUser(@PathVariable int courseId, @PathVariable int userId) {
        List<LessonAttendanceDto> list = attendanceService.getLessonsOfCourseAttendanceByUser(courseId, userId);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping("createReport/course")
    public ResponseEntity<?> createReportByCourse(@RequestParam("courses") int[] courses) {
        reporterServiceImpl.createReportByCourse(courses);

        return new ResponseEntity<>(new ResponseMessage("Report on selected courses saved"), HttpStatus.OK);
    }

    @PostMapping("createReport/trainer")
    public ResponseEntity<?> createReportByTrainer(@RequestParam("trainerUsername") String username) {
        reporterServiceImpl.createReportByTrainer(username);

        return new ResponseEntity<>(new ResponseMessage("Report on selected trainer saved"), HttpStatus.OK);
    }

    @PostMapping("createReport/student")
    public ResponseEntity<?> createReportByStudent(@RequestParam("username") String username) {
        reporterServiceImpl.createReportByStudent(username);

        return new ResponseEntity<>(new ResponseMessage("Report on selected user saved"), HttpStatus.OK);
    }

    @PostMapping("createReport/level")
    public ResponseEntity<?> createReportByLevel(@RequestParam("level") String level) {
        reporterServiceImpl.createReportByLevel(level);

        return new ResponseEntity<>(new ResponseMessage("Report on selected level saved"), HttpStatus.OK);

    }


}